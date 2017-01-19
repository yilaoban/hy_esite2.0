package com.huiyee.esite.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import com.huiyee.esite.util.HyConfig;

public class QuanJingAction extends AbstractAction {

	private static final long serialVersionUID = -6403464901564406531L;
	private String name;
	private String p;

	public String execute() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		response.addHeader("Access-Control-Allow-Origin", HyConfig.getPageyuming(this.getOwner()));
		response.addHeader("Access-Control-Allow-Methods", "HEAD,GET,POST,PUT,DELETE,OPTIONS");
		response.addHeader("Access-Control-Allow-Headers", "Content-Type,Origin,Accept");
		response.addHeader("Access-Control-Max-Age", "120");
		HttpServletRequest request = ServletActionContext.getRequest();
		PrintWriter out = response.getWriter();
		if ("OPTIONS".equals(request.getMethod())) {
			return null;
		}
		if (name.endsWith(".zip") == false) {
			return null;
		}

		String path = "/" + this.getOwner() + "/content/panorama/" + System.currentTimeMillis() + "/";// 路径
		String xml_name = unzip(request.getInputStream(), path);

		out.print("{\"xmlPath\":\"" + path + xml_name + "\"}");
		out.flush();
		out.close();
		return null;
	}
	
	public String show360(){
		return SUCCESS;
	}

	private String unzip(InputStream is, String path) throws Exception {
		String xml_name = "";
		try {
			ZipInputStream zis = new ZipInputStream(is);
			BufferedInputStream bis = new BufferedInputStream(zis);

			ZipEntry entry;
			while ((entry = zis.getNextEntry()) != null) {
				if(entry.isDirectory()){
					continue;
				}
				File file = new File(HyConfig.getRootPath() + path, entry.getName());
				if (!file.exists()) {
					(new File(file.getParent())).mkdirs();
				}

				FileOutputStream fos = new FileOutputStream(file);
				BufferedOutputStream bos = new BufferedOutputStream(fos);
				int b;
				while ((b = bis.read()) != -1) {
					bos.write(b);
				}
				bos.close();
				fos.close();

				if (entry.getName().endsWith("xml")) {
					xml_name = entry.getName();
					String img_path = "/site" + path;
					int index = xml_name.lastIndexOf("/");
					if (index > -1) {
						img_path = img_path + xml_name.substring(0, index + 1);
					}
					modifyInput(file, img_path);
				}
			}
			bis.close();
			zis.close();

		} catch (Exception x) {
			x.printStackTrace();
		} finally {
			is.close();
		}
		return xml_name;
	}

	@SuppressWarnings("unchecked")
	private void modifyInput(File file, String path) throws Exception {
		try {
			SAXReader reader = new SAXReader();
			InputStream is = new FileInputStream(file);
			Document doc_in = reader.read(is);
			is.close();

			// 复制根元素
			Document doc_out = DocumentHelper.createDocument();
			Element root_in = doc_in.getRootElement();
			Element root_out = doc_out.addElement(root_in.getName());
			List<Attribute> root_attrs = root_in.attributes();
			for (Attribute root_attr : root_attrs) {
				root_out.addAttribute(root_attr.getName(), root_attr.getValue());
			}

			// 复制子节点
			List<Element> elementList = root_in.elements();
			for (Element element : elementList) {
				if ("input".equals(element.getName())) {
					Element input = root_out.addElement(element.getName());
					List<Attribute> input_attrs = element.attributes();
					for (Attribute input_attr : input_attrs) {
						String name = input_attr.getName();
						String value = input_attr.getValue();
						if (name.endsWith("url")) {
							value = path + input_attr.getValue();
						}
						input.addAttribute(name, value);
					}
					// 子节点
					List<Element> sub_inputs = element.elements();
					for (Element sub_input : sub_inputs) {
						copyElement(sub_input, input);
					}
				} else {
					copyElement(element, root_out);
				}
			}

			// 写入原文件
			OutputStream os = new FileOutputStream(file);
			OutputStreamWriter osw = new OutputStreamWriter(os);
			OutputFormat format = OutputFormat.createPrettyPrint();
			format.setEncoding("UTF-8");
			XMLWriter writer = new XMLWriter(osw, format);
			writer.write(doc_out);
			writer.flush();
			writer.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	private void copyElement(Element element, Element parent) {
		String name = element.getName();
		List<Attribute> attrs = element.attributes();

		Element element_new = parent.addElement(name);
		for (Attribute attr : attrs) {
			element_new.addAttribute(attr.getName(), attr.getValue());
		}

		// 递归复制子元素
		List<Element> sub_elements = element.elements();
		if (sub_elements != null && sub_elements.size() > 0) {
			for (Element sub_element : sub_elements) {
				copyElement(sub_element, element_new);
			}
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getP() {
		return p;
	}
	
	public void setP(String p) {
		this.p = p;
	}

}
