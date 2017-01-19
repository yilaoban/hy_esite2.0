package com.huiyee.esite.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import sun.misc.BASE64Decoder;

import com.huiyee.esite.constants.IPageConstants;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.model.ContentPicture;
import com.huiyee.esite.service.FileUploadService;
import com.huiyee.esite.util.HyConfig;
import com.opensymphony.xwork2.ActionContext;

public class ImgFlashAction extends AbstractAction {
	
	private String key;
	private double width;
	private double height;
	private double cutwidth;
	private double cutheight;
	
	private String pic;
	private String pic1;
	private String pic2;
	private String pic3;
	private String petname;
	private String uploadSrc;

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getPic1() {
		return pic1;
	}

	public void setPic1(String pic1) {
		this.pic1 = pic1;
	}

	public String getPic2() {
		return pic2;
	}

	public void setPic2(String pic2) {
		this.pic2 = pic2;
	}

	public String getPic3() {
		return pic3;
	}

	public void setPic3(String pic3) {
		this.pic3 = pic3;
	}

	@Override
	public String execute() throws Exception {
		int size;
		if(((width>height)?width:height) >100){
			size = 180;
		}else{
			size= 50;
		}
		if(width >= height){
			cutwidth = size;
			cutheight = (height/width)*size;
		}else{
			cutheight = size;
			cutwidth = (width/height)*size;
		}
		if(width == 0){
			cutwidth = 100;
		}
		if(height == 0){
			cutheight = 100;
		}
		return SUCCESS;
	}
	
	public String img_flash() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		ContentPicture picture = new ContentPicture();
		String imgpath = HyConfig.getRootPath()+FileUploadService.getFilePath(IPageConstants.TYPE_CONTENT, account.getOwner().getId(), IPageConstants.CONTENT_PICTURE_FILEFATH);
		File d = new File(imgpath);
		if(!d.exists()){
			d.mkdirs();
		}

		long savePicName = new Date().getTime();
		String file_src = imgpath + "/" + savePicName + "_src.jpg"; // 保存原图
		String filename_b = imgpath + "/"+ savePicName + "_b.jpg"; // 保存162
		String filename_m = imgpath + "/"+ savePicName + "_m.jpg"; // 保存48
		String filename_s = imgpath + "/"+ savePicName + "_s.jpg"; // 保存20
		
		if(pic!=null&&!pic.equals("")){
			//原图
			File file = new File(file_src);
			FileOutputStream fout = null;
			fout = new FileOutputStream(file);
			fout.write(new BASE64Decoder().decodeBuffer(pic));
			fout.close();
		}
		if(pic1!=null && !pic1.equals("")){
			//图1
			File file1 = new File(filename_b);
			FileOutputStream fout1 = null;
			fout1 = new FileOutputStream(file1);
			fout1.write(new BASE64Decoder().decodeBuffer(pic1));
			fout1.close();
//			picture.setCatid(2);
//			picture.setTitle(savePicName + "_b.jpg");
//			picture.setImgurl(FileUploadService.getFilePath(IPageConstants.TYPE_CONTENT, account.getOwner().getId(), IPageConstants.CONTENT_PICTURE_FILEFATH)+"/"+savePicName + "_b.jpg");
//			picture.setOwnerid(account.getOwner().getId());
//			pageCompose.savePicture(picture);
		}
		if(pic2!=null && !pic2.equals("")){
			//图2
			File file2 = new File(filename_m);
			FileOutputStream fout2 = null;
			fout2 = new FileOutputStream(file2);
			fout2.write(new BASE64Decoder().decodeBuffer(pic2));
			fout2.close();
//			picture.setCatid(2);
//			picture.setTitle(savePicName + "_m.jpg");
//			picture.setImgurl(FileUploadService.getFilePath(IPageConstants.TYPE_CONTENT, account.getOwner().getId(), IPageConstants.CONTENT_PICTURE_FILEFATH)+"/"+savePicName + "_m.jpg");
//			picture.setOwnerid(account.getOwner().getId());
//			pageCompose.savePicture(picture);
		}

		if(pic3!=null && !pic3.equals("")){
			//图3
			File file3 = new File(filename_s);
			FileOutputStream fout3 = null;
			fout3 = new FileOutputStream(file3);
			fout3.write(new BASE64Decoder().decodeBuffer(pic3));
			fout3.close();
//			picture.setCatid(2);
//			picture.setTitle(savePicName + "_s.jpg");
//			picture.setImgurl(FileUploadService.getFilePath(IPageConstants.TYPE_CONTENT, account.getOwner().getId(), IPageConstants.CONTENT_PICTURE_FILEFATH)+"/"+savePicName + "_s.jpg");
//			picture.setOwnerid(account.getOwner().getId());
//			pageCompose.savePicture(picture);
		}
		
		String picUrl = HyConfig.getImgDomain()+FileUploadService.getFilePath(IPageConstants.TYPE_CONTENT, account.getOwner().getId(), IPageConstants.CONTENT_PICTURE_FILEFATH)+"/"+ savePicName + "_b.jpg";
		out.print("{\"status\":1,\"picUrl\":\""+picUrl+"\"}");
		out.flush();
		out.close();
		return null;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public double getCutwidth() {
		return cutwidth;
	}

	public double getCutheight() {
		return cutheight;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getUploadSrc() {
		return uploadSrc;
	}

	public void setUploadSrc(String uploadSrc) {
		this.uploadSrc = uploadSrc;
	}

	public String getPetname() {
		return petname;
	}

	public void setPetname(String petname) {
		this.petname = petname;
	}

}
