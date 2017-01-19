package com.huiyee.esite.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.Tag;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.nodes.TextNode;
import org.htmlparser.tags.ImageTag;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.tags.ObjectTag;
import org.htmlparser.tags.TextareaTag;
import org.htmlparser.tags.TitleTag;
import org.htmlparser.util.NodeList;

public class GProcessHtml {
	
	private static Map<String, VarDef> dict = new HashMap<String, VarDef>();
	
	private static Pattern pattern = Pattern.compile("([a-zA-Z]+)(\\d*)");
	
	public GProcessHtml(){
		init();
	}
	
	private void init() {
		createDict();
	}
	
	/**
	 * define 定义的json
	 * value  值的json
	 * jsp    解析后的String
	 * @param strhtml
	 * @return
	 * @throws Exception
	 */
	public Map<String,String> process(String strhtml) throws Exception{
		Parser parser = Parser.createParser(strhtml, "UTF-8");
		NodeList head= parser.parse (null);
		StringBuffer sb =new StringBuffer("<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>\n<%@ taglib uri='/struts-tags' prefix='s'%>\n");
		for (int ii=0; ii<head.size(); ii++) {
			Node x = head.elementAt(ii);
			HasAttributeFilter varFilter = new HasAttributeFilter("hyvar");
			NodeList nl = new NodeList();
			x.collectInto(nl, varFilter);
			List<GVar> vars = new ArrayList<GVar>();

			for (int j=0; j<nl.size(); j++) {
				Node m = nl.elementAt(j);
				
				vars.add( parseVar(m));
				
			}
			sb.append(x.toHtml());
			if(vars.size()!=0){
				Map<String,String> map =new HashMap<String, String>();
				String[] jsons = convertToJson(vars);
				map.put("jsp", sb.toString());
				map.put("define", jsons[0]);
				map.put("value", jsons[1]);
				return map;
			}
		}
		return null;
		
	    
	}
	
	
	
	private static GVar parseVar(Node n) {//  0  0  abc  S
		Tag tn = (Tag)n	;
		String var = tn.getAttribute("hyvar");      // hyvar="img1"
		String desc = tn.getAttribute("hydesc");    
		String display = tn.getAttribute("hyname");
		String ivar = "";
		if(desc ==null ){
			desc="";
		}
		
		if (display == null) {
			display = getDisplay(var);
		}
		
		ivar = "${page.param." + var+"}";   // ${block[0].img1}
		if (tn instanceof ImageTag) {
			ImageTag it = (ImageTag)tn;
			String orig = it.getImageURL();
			it.setImageURL(ivar);
			return new GVar(var, orig,  desc, display);
		}
		
		if (tn instanceof LinkTag) {
			LinkTag lt = (LinkTag)tn;
			String orig = lt.getLink();
			lt.setLink(ivar);
			return new GVar(var, orig,desc,display);
		}
		
		if (tn instanceof ObjectTag) {
			ObjectTag obj = (ObjectTag)tn;
			String orig = obj.getText();
			obj.setText(ivar);
			return new GVar(var, orig,  desc, display);
		}
		
		if (tn instanceof TitleTag) {
			TitleTag tt = (TitleTag)tn;
			String orig = tt.getText();
			tt.setText(ivar);
			return new GVar(var, orig,  desc, display);
		}
		
		if (tn instanceof TextareaTag) {
			TextareaTag ta = (TextareaTag)tn;
			String orig = ta.getText();
			ta.setText(ivar);
			return new GVar(var, orig, desc,display);
		}
		
		
		TextNode txt = new TextNode(ivar);
		NodeList nl = new NodeList();
		nl.add(txt);
		String orig ="";
		if(tn.getChildren() != null){
			orig = tn.getChildren().toHtml(true);			
		}else{
			orig = tn.toHtml();
		}
		tn.setChildren(nl);
		return new GVar(var, orig,  desc, display);
		
	}
	
	private static String[] convertToJson(List<GVar> vars) {
			
		StringBuffer sb = new StringBuffer("{");
		StringBuffer sb1 = new StringBuffer("{");
		for (int i = 0; i < vars.size(); i++) {
			GVar v = (GVar) vars.get(i);
			if(!sb.toString().contains(("\""+v.getVar()+"\":{"))){
				sb.append("\"").append(v.getVar()).append("\":").append(v.toKJson()).append(",");
				sb1.append(v.toCJson()).append(",");				
			}

		}
		sb.deleteCharAt(sb.length() - 1);
		sb1.deleteCharAt(sb1.length() - 1);
		sb.append("}");
		sb1.append("}");
		return new String[] { sb.toString(), sb1.toString() };
	}
	
	
	private  String openFile(String fileName, String encode) throws Exception {
		
		try {
			BufferedReader bir = new BufferedReader(new InputStreamReader(new FileInputStream(new File(fileName)), encode));
			StringBuffer content = new StringBuffer();
			String line = "";
			while ((line = bir.readLine())!= null) {
				content = content.append(line).append("\n");
			}
			bir.close();
			return content.toString();
		}catch (Exception x) {
			x.printStackTrace();
			throw x;
		}
	}
	

	public boolean fileCopy(String src, String des) {
		File srcFile = new File(src);
		File desFile = new File(des);
		byte[] b = new byte[1024];
		try {
			FileInputStream fis = new FileInputStream(srcFile);
			FileOutputStream fos = new FileOutputStream(desFile, false);
			while (true) {
				int i = fis.read(b);
				if (i == -1)
					break;
				fos.write(b, 0, i);
			}
			fos.close();
			fis.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	

	public void createDict() {
		
		dict.put("img", new VarDef("I", "tupian"));
		dict.put("title", new VarDef("S", "biaoti"));
		dict.put("link", new VarDef("L", "link"));
		dict.put("video", new VarDef("I", "video"));
		dict.put("introduction", new VarDef("S", "introduction"));
		dict.put("desc", new VarDef("A", "desc"));
		dict.put("content", new VarDef("S", "content"));
		dict.put("time", new VarDef("S", "time"));
		dict.put("name", new VarDef("S", "name"));
		dict.put("tel", new VarDef("S", "tel"));
		dict.put("email", new VarDef("S", "email"));
		dict.put("button", new VarDef("S", "button"));
		dict.put("footer", new VarDef("S", "button"));
		dict.put("prompt", new VarDef("S", "prompt"));
		dict.put("price", new VarDef("S", "price"));
		dict.put("text", new VarDef("S", "text"));
		
	}
		
	
	public static VarDef getVarDef(String var) {
		Matcher m = pattern.matcher(var);
		if (m.matches()) {
			String v = m.group(1);
			return dict.get(v);
		}
		return null;
	}
	
	
	public static String getType(String var) {
		
		VarDef vd = getVarDef(var);
		if (vd != null) {
			return vd.getType();
		}
		
		return "S";
	}
		
	
	
	public static String getDisplay(String var) {
		Matcher m = pattern.matcher(var);
		if (m.matches()) {
			String v = m.group(1);
			String d = m.group(2);
			VarDef vd = getVarDef(v);
			if (vd != null) {
				return vd.getName()+ d	;
			} else {
				return "neirongd" + d;
			}
		} else {
			return "neirongx";
		}
	}

	public static String processGenernalCode(String html,Map<String,String> map){
//		html="q12121212<!-- begin-aa  -->1<!-- end-aa -->dasdasd<!-- begin-bb -->2<!-- end-bb -->345345<!-- begin-cc -->3<!-- end-cc -->123<!-- asdsadasd -->123";
		try{
			Pattern pattern = Pattern.compile("<!-- +(begin|end)-[^<]+ -->");
			Pattern pattern2 = Pattern.compile("begin-\\S+");
			Matcher m = pattern.matcher(html);
			List<String> idx = new ArrayList<String>();
			while(m.find()) {
				idx.add(m.group());
			}
			if(idx.size() % 2 == 0 ){
				for(int i = 0 ; i < idx.size()/2 ; i++){
					String name=idx.get(i*2);
					Matcher m2 = pattern2.matcher(name);
					if(m2.find()){
						name=m2.group().replaceAll("begin-", "");
					}
					int start = html.indexOf(idx.get(i*2))+idx.get(i*2).length();
					int end = html.indexOf(idx.get(i*2+1));
					String value = html.substring(start,end);
					if(map.get(name) == null){
						map.put(name, value);					
					}
					html = html.replace(value, "\n<s:include value='public/"+name+".jsp' />\n");
				}
			}
		} catch (Exception e){
			e.printStackTrace();
		}
		return html;
	}

	public static void main(String[] args)
	{
		Map<String,String> map = new HashMap<String, String>();
		processGenernalCode("",map);
		System.out.println(map.size());
	}
}
