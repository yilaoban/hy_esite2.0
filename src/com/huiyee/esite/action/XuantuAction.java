package com.huiyee.esite.action;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.swing.ImageIcon;

import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.huiyee.esite.action.PageAction.HTMLFileNameFilter;
import com.huiyee.esite.constants.IPageConstants;
import com.huiyee.esite.dto.ContentDto;
import com.huiyee.esite.mgr.IXuantuManager;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.model.ContentPicture;
import com.huiyee.esite.model.ContentProduct;
import com.huiyee.esite.model.VisitUser;
import com.huiyee.esite.service.FileUploadService;
import com.huiyee.esite.util.GProcessHtml;
import com.huiyee.esite.util.HyConfig;
import com.huiyee.esite.util.PageFileUtil;
import com.huiyee.esite.util.PageReader;
import com.huiyee.weixin.model.ShoppingCartRecord;
import com.opensymphony.xwork2.ActionContext;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import net.sf.json.JSONObject;

public class XuantuAction extends AbstractAction{
	
	private IXuantuManager xuantuMgr;
	
	private long productid;
	private int pageId = 1;
	private long cartid;
	
	private String name;
	private String phone;
	private String something;
	private long ccid;
	private long typeid;
	private File file;
	private String fileFileName;
	private String fileContentType;
	private String result;
	private String errmsg;//错误信息
	private JSONObject tagJson;

	@Override
	public String execute() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setHeader("pragma", "no-cache");
		response.setHeader("cache-control", "no-cache");
		response.setContentType("application/json; charset=utf-8");
		VisitUser vu = (VisitUser) ActionContext.getContext().getSession().get("visitUser");
		int result = 0;
		if(vu != null  && vu.getHyUserId() > 0){
			result = xuantuMgr.addCollection(productid, this.getOwner(), vu.getHyUserId());
		}
		PrintWriter pw = response.getWriter();
		pw.print(new Gson().toJson(result));
		pw.flush();
		pw.close();
		return null;
	}
	
	public String check() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setHeader("pragma", "no-cache");
		response.setHeader("cache-control", "no-cache");
		response.setContentType("application/json; charset=utf-8");
		VisitUser vu = (VisitUser) ActionContext.getContext().getSession().get("visitUser");
		int result = 0;
		if(vu != null  && vu.getHyUserId() > 0){
			result = xuantuMgr.checkCollection(productid, this.getOwner(), vu.getHyUserId());
		}
		PrintWriter pw = response.getWriter();
		pw.print(new Gson().toJson(result));
		pw.flush();
		pw.close();
		return null;
	}
	
	public String mine() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setHeader("pragma", "no-cache");
		response.setHeader("cache-control", "no-cache");
		response.setContentType("application/json; charset=utf-8");
		VisitUser vu = (VisitUser) ActionContext.getContext().getSession().get("visitUser");
		int size = 9;
		int start = (pageId-1) * size; 
		List list = null;
		if(vu != null  && vu.getHyUserId() > 0){
			list = xuantuMgr.myCollection(this.getOwner(), vu.getHyUserId(), start, size);
		}
		PrintWriter pw = response.getWriter();
		pw.print(new Gson().toJson(list));
		pw.flush();
		pw.close();
		return null;
	}
	
	public String total() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setHeader("pragma", "no-cache");
		response.setHeader("cache-control", "no-cache");
		response.setContentType("application/json; charset=utf-8");
		VisitUser vu = (VisitUser) ActionContext.getContext().getSession().get("visitUser");
		int result = 0;
		if(vu != null  && vu.getHyUserId() > 0){
			result = xuantuMgr.mytotal(this.getOwner(), vu.getHyUserId());
		}
		PrintWriter pw = response.getWriter();
		pw.print(new Gson().toJson(result));
		pw.flush();
		pw.close();
		return null;
	}
	
	public String remove() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setHeader("pragma", "no-cache");
		response.setHeader("cache-control", "no-cache");
		response.setContentType("application/json; charset=utf-8");
		VisitUser vu = (VisitUser) ActionContext.getContext().getSession().get("visitUser");
		int result = 0;
		if(vu != null  && vu.getHyUserId() > 0){
			result = xuantuMgr.removeCollection(this.getOwner(), vu.getHyUserId(),productid);
		}
		PrintWriter pw = response.getWriter();
		pw.print(new Gson().toJson(result));
		pw.flush();
		pw.close();
		return null;
	}
	
	/**
	 * 添加商品至购物车 1
	 */
	public String want() throws Exception
	{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setHeader("pragma", "no-cache");
		response.setHeader("cache-control", "no-cache");
		response.setContentType("application/json; charset=utf-8");
		VisitUser vu = (VisitUser) ActionContext.getContext().getSession().get("visitUser");
		long res = 0;
		if (vu != null  && vu.getHyUserId() > 0)
		{
			ShoppingCartRecord record = xuantuMgr.addShoppingCart(productid, vu.getHyUserId(),"EDT",this.getOwner());
			if(record != null){
				res = record.getId();
			}
		}
		PrintWriter pw = response.getWriter();
		pw.print(new Gson().toJson(res));
		pw.flush();
		pw.close();
		return null;
	}
	
	
	public String mywant() throws Exception
	{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setHeader("pragma", "no-cache");
		response.setHeader("cache-control", "no-cache");
		response.setContentType("application/json; charset=utf-8");
		VisitUser vu = (VisitUser) ActionContext.getContext().getSession().get("visitUser");
		List list = null;
		if (vu != null  && vu.getHyUserId() > 0)
		{
			int size = 9;
			int start = (pageId-1) * size; 
			list = xuantuMgr.findShoppingCartProductByHyuid(vu.getHyUserId(), this.getOwner(), "EDT", start, size);
		}
		PrintWriter pw = response.getWriter();
		pw.print(new Gson().toJson(list));
		pw.flush();
		pw.close();
		return null;
	}
	
	
	public String nowant() throws Exception
	{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setHeader("pragma", "no-cache");
		response.setHeader("cache-control", "no-cache");
		response.setContentType("application/json; charset=utf-8");
		VisitUser vu = (VisitUser) ActionContext.getContext().getSession().get("visitUser");
		int result = 0;
		if (vu != null  && vu.getHyUserId() > 0)
		{
			result = xuantuMgr.delShopCartProduct(cartid,vu.getHyUserId());
		}
		PrintWriter pw = response.getWriter();
		pw.print(new Gson().toJson(result));
		pw.flush();
		pw.close();
		return null;
	}
	
	public String submit() throws Exception
	{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setHeader("pragma", "no-cache");
		response.setHeader("cache-control", "no-cache");
		response.setContentType("application/json; charset=utf-8");
		VisitUser vu = (VisitUser) ActionContext.getContext().getSession().get("visitUser");
		int result = 0;
		if (vu != null && something != null && vu.getHyUserId() > 0)
		{
			String[] sm = something.split(";");
			result = xuantuMgr.savesubmit(vu.getHyUserId(),vu.getWxuid(),this.getOwner(),name,phone,sm);
		}
		PrintWriter pw = response.getWriter();
		pw.print(new Gson().toJson(result));
		pw.flush();
		pw.close();
		return null;
	}
	
	public String uploadimg() throws Exception{
		return SUCCESS;
	}
	
	public String imgSave() throws Exception{
		Account account = (Account) ActionContext.getContext().getSession().get("account");
	    InputStream is = null;
	    try{
			String filename = fileFileName;
			is = new FileInputStream(file);	
			if (filename.endsWith(".zip") == false) {
				errmsg="无法解析此文件,请确认文件格式!";
				result = "N";
				return SUCCESS;
			}
			long ctime = System.currentTimeMillis();
			String outdir = HyConfig.getTmp()+"/content/"+ctime+"/upload";
			String imgpath2 = HyConfig.getRootPath()+"/" + account.getOwner().getId() + "/content/"+ctime+"/upload/";
			try {
				unzip(is, outdir);
				//PageFileUtil.copyFolder(outdir,imgpath2);//复制image
			}catch (Exception x) {
				errmsg="无法解析此ZIP 文件,请确认文件格式!";
				result = "N";
				return INPUT;
			}
			try {
				//开始遍历图片
				File dir = new File(outdir);
		        File[] files = dir.listFiles();
		        int len = files.length > 100?100:files.length;
		        for (int i = 0; i < len; i++) {
		        	String fileName = files[i].getName();
		    		ContentProduct product = new ContentProduct();
		    		product.setOwner(account.getOwner().getId());
		    		product.setCatid(ccid);
		    		product.setName(fileName.substring(0,fileName.indexOf(".")));
		    		product.setLinkurl("");
		    		product.setStatus("CMP");
		    		product.setSubtype(IPageConstants.PRODUCT_SUBTYPE_SHIWU);
		    		String imgfilePath = FileUploadService.getFilePath(IPageConstants.TYPE_CONTENT, account.getOwner().getId(), IPageConstants.CONTENT_PRODUCT_FILEFATH);
	    			String simgfileName = FileUploadService.createFileName(fileName);
	    			String result1 = FileUploadService.saveFile(files[i], imgfilePath, simgfileName);
	    			resize(files[i], files[i],1,(float)0.3);
	    			String simgfileName2 = FileUploadService.createFileName(fileName);
	    			String result2 = FileUploadService.saveFile(files[i], imgfilePath, simgfileName2);
	    			product.setSimgurl(result2);
	    			product.setBimgurl(result1);
		    		long productid = pageCompose.saveProduct(product, tagJson);
		        }
			}catch (Exception x) {
				x.printStackTrace();
				errmsg="无法处理文件,请确认文件格式!";
				result = "N";
				return INPUT;
			}
	    } catch (Exception e) {
			errmsg ="errors.upload.size.exceeded";
			result = "N";
			return INPUT;

		}finally{
			try{
				if(is != null){
					is.close();
				}				
			}catch(Exception ex){
			}
		}
		result = "Y";
		return SUCCESS;
	}
	
	public static void resize(File originalFile, File resizedFile,double scale, float quality) throws IOException {  
        ImageIcon ii = new ImageIcon(originalFile.getCanonicalPath());  
        Image i = ii.getImage();  
        int iWidth = (int) (i.getWidth(null)*scale);  
        int iHeight = (int) (i.getHeight(null)*scale); 
        //在这你可以自定义 返回图片的大小 iWidth iHeight
        Image resizedImage = i.getScaledInstance(iWidth,iHeight, Image.SCALE_SMOOTH);  
        // 获取图片中的所有像素
        Image temp = new ImageIcon(resizedImage).getImage();  
        // 创建缓冲
        BufferedImage bufferedImage = new BufferedImage(temp.getWidth(null),  
                temp.getHeight(null), BufferedImage.TYPE_INT_RGB);  
        // 复制图片到缓冲流中
        Graphics g = bufferedImage.createGraphics();  
        // 清除背景并开始画图
        g.setColor(Color.white);  
        g.fillRect(0, 0, temp.getWidth(null), temp.getHeight(null));  
        g.drawImage(temp, 0, 0, null);  
        g.dispose();
        // 柔和图片.  
        float softenFactor =0.05f;  
        float[] softenArray = { 0, softenFactor, 0, softenFactor,  
                1 - (softenFactor * 4), softenFactor, 0, softenFactor, 0 };  
        Kernel kernel = new Kernel(3, 3, softenArray);  
        ConvolveOp cOp = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);  
        bufferedImage = cOp.filter(bufferedImage, null);  
        FileOutputStream out = new FileOutputStream(resizedFile);  
        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);  
        JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(bufferedImage);  
        param.setQuality(quality, true);  
        encoder.setJPEGEncodeParam(param);  
        encoder.encode(bufferedImage);
        bufferedImage.flush();
        out.close();
    } 
	
	private void unzip(InputStream is, String outdir) throws Exception {
		ZipArchiveInputStream in = new ZipArchiveInputStream(new BufferedInputStream(is), "GBK", true);
		ZipArchiveEntry z;
		int size = 0;
		
		while ((z=in.getNextZipEntry() )!= null)
		{
			if (z.isDirectory())
				continue;
			else{
				File f = getParsedFileName(outdir, z.getName());
				FileOutputStream out=new FileOutputStream(f);
				byte[] buffer = new byte[1024];
				BufferedOutputStream bos = new BufferedOutputStream(out,buffer.length);
				
				while((size=in.read(buffer, 0, buffer.length)) != -1)
					bos.write(buffer, 0, size);
				bos.flush();
				bos.close();
				try {
					out.flush();
					out.close();
				}catch(Exception x) {
					x.printStackTrace();
				}
			}	
		}
		
		try {
			in.close();
		}catch (Exception x) {
			x.printStackTrace();
		};
		try {
			is.close();
		}catch (Exception x) {
			x.printStackTrace();
		};
		
	}
	
	private File getParsedFileName(String outdir, String name) throws Exception {
		
		int cutoff = 0;
		if (name.endsWith(".htm") || name.endsWith(".html"))
			cutoff = 1;
		else
			cutoff = 2;
		
		String[] x = org.springframework.util.StringUtils.tokenizeToStringArray(name, "/\\");
		int idx = 0;
		if (x.length > cutoff) {
			idx = 1;
		}
		String dir = outdir;
		String fileName = x[x.length - 1];
		int dirlen = x.length -1;
		
		for (int i=idx; i<dirlen; i++) 
			dir = dir + "/" + x[i];
		
		File dirs = new File(dir);
		dirs.mkdirs();
		File  file = new File(dir + "/" + fileName);
		if(file.exists()) {
			file.delete();
		}
		file.createNewFile();
		return file;
	}
	
	
	public int getPageId()
	{
		return pageId;
	}
	
	public void setPageId(int pageId)
	{
		this.pageId = pageId;
	}

	public long getProductid()
	{
		return productid;
	}

	public void setProductid(long productid)
	{
		this.productid = productid;
	}

	public void setXuantuMgr(IXuantuManager xuantuMgr)
	{
		this.xuantuMgr = xuantuMgr;
	}
	
	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getPhone()
	{
		return phone;
	}


	public void setPhone(String phone)
	{
		this.phone = phone;
	}
	
	public String getSomething()
	{
		return something;
	}

	public void setSomething(String something)
	{
		this.something = something;
	}

	public long getCartid()
	{
		return cartid;
	}
	
	public void setCartid(long cartid)
	{
		this.cartid = cartid;
	}

	
	public long getCcid()
	{
		return ccid;
	}

	
	public void setCcid(long ccid)
	{
		this.ccid = ccid;
	}

	
	public long getTypeid()
	{
		return typeid;
	}

	
	public void setTypeid(long typeid)
	{
		this.typeid = typeid;
	}

	
	public File getFile()
	{
		return file;
	}

	
	public void setFile(File file)
	{
		this.file = file;
	}

	
	public String getFileFileName()
	{
		return fileFileName;
	}

	
	public void setFileFileName(String fileFileName)
	{
		this.fileFileName = fileFileName;
	}

	
	public String getFileContentType()
	{
		return fileContentType;
	}

	
	public void setFileContentType(String fileContentType)
	{
		this.fileContentType = fileContentType;
	}

	
	public String getResult()
	{
		return result;
	}

	
	public void setResult(String result)
	{
		this.result = result;
	}

	
	public String getErrmsg()
	{
		return errmsg;
	}

	
	public void setErrmsg(String errmsg)
	{
		this.errmsg = errmsg;
	}
	

}
