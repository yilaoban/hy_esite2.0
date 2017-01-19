package com.huiyee.esite.action;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.Buffer;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.huiyee.esite.compose.IFeatureCompose;
import com.huiyee.esite.constants.IPageConstants;
import com.huiyee.esite.dto.DynamicActionDto;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.model.ContentPicture;
import com.huiyee.esite.model.UserUpload;
import com.huiyee.esite.model.UserUploadRecord;
import com.huiyee.esite.service.FileUploadService;
import com.huiyee.esite.util.ClientUserIp;
import com.huiyee.esite.util.HttpRequestDeviceUtils;
import com.huiyee.esite.util.HyConfig;
import com.opensymphony.xwork2.ActionContext;

import sun.misc.BASE64Decoder;

public class UserUploadAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1518177104254048441L;

	private File img;
	private String imgFileName;
	private String imgContentType;
	private String name;


	public void setName(String name) {
		this.name = name;
	}
	private IFeatureCompose featureCompose;
	private long featureid;
	private long fid;
	private long pageid;
	private String feature;
	private String source;
	
	private int swidth = 48;
	private int sheight = 48;
	
	private String result;
	
	private File pic;
	private String picFileName;
	private String picContentType;
	
	private File pic1;
	private String pic1FileName;
	private String pic1ContentType;
	
	private File pic2;
	private String pic2FileName;
	private String pic2ContentType;
	
	private File pic3;
	private String pic3FileName;
	private String pic3ContentType;
	
	private Map<String,String> suffixMap = new HashMap<String, String>();
	
	public void init() throws Exception{
		suffixMap.put("JPG", "");
		suffixMap.put("JPEG", "");
		suffixMap.put("BMP", "");
		suffixMap.put("PNG", "");
		suffixMap.put("GIF", "");
	}
	public long getFid() {
		return fid;
	}

	public void setFid(long fid) {
		this.fid = fid;
	}
	
	private String createFileName(String fileName,String type) {
		int index = fileName.lastIndexOf('.');
		// 判断上传文件名是否有扩展名
		if (index != -1) {
			return FileUploadService.getNow()+type+ fileName.substring(index);
		}
		return FileUploadService.getNow()+type;
	}
	
	@Override
	public String execute() throws Exception {
		String fileSuffix = FileUploadService.getFileSuffix(imgFileName);
		if(img.length() > (2*1024*1024)){
			result= "文件大小超过2M!" ;
			return SUCCESS;
		}
		if(suffixMap.get(fileSuffix.toUpperCase()) == null){
			result= "文件格式不支持!" ;
			return SUCCESS;
		}
		Long uid = (Long) ServletActionContext.getRequest().getSession().getAttribute("uid");
		if(uid == null){
			result= "Y" ;
			return SUCCESS;
		}
		String name1 = createFileName(imgFileName,"_b");//大图名字
		String name2 = createFileName(imgFileName,"_m");//中图名字
		String name3 = createFileName(imgFileName,"_s");//小图名字
		long ownerid = featureCompose.findOwneridByPageidAndFid(pageid, fid);//ownerid
		String path = FileUploadService.getFilePath(IPageConstants.TYPE_FEATURE, ownerid, featureid+"");//路径
		
		UserUploadRecord record = new UserUploadRecord();
		record.setUid(uid);
		record.setUploadid(fid);
		
		String saveResult = FileUploadService.saveFile(img,path,name1);
		if(saveResult == null){
			//大图保存失败
			result= "图片保存失败!" ;
			return SUCCESS;
		}
		record.setBigimg(path+"/"+name1);
		
		UserUpload uu = pageCompose.composeUserUpload(fid);
		InputStream  is = null;
		if(uu.getMheight() > 0 && uu.getMwidth() > 0 ){
			is = FileUploadService.reduceImg(img, uu.getMwidth(), uu.getMheight());	
			saveResult = FileUploadService.saveFile(is, path, name2);
			if(saveResult == null){
				//中图保存失败
				result= "图片保存失败!";
				removePicture(path+"/"+name1, path+"/"+name2, path+"/"+name3);
				return SUCCESS;
			}
			record.setMidimg(path+"/"+name2);
		}else{
			record.setMidimg(null);
		}
		if(uu.getSheight() > 0 && uu.getSwidth() > 0 ){
			is = FileUploadService.reduceImg(img, uu.getMwidth(), uu.getMheight());
			saveResult = FileUploadService.saveFile(is, path, name3);
			if(saveResult == null){
				//小图保存失败
				result= "图片保存失败!" ;
				removePicture(path+"/"+name1, path+"/"+name2, path+"/"+name3);
				return SUCCESS;
			}
		}else{
			is =FileUploadService.reduceImg(img,swidth, sheight);
			saveResult = FileUploadService.saveFile(is, path, name3);
			if(saveResult == null){
				//小图保存失败
				result= "图片保存失败!" ;
				removePicture(path+"/"+name1, path+"/"+name2, path+"/"+name3);
				return SUCCESS;
			}
		}
		record.setSmallimg(path+"/"+name3);
		DynamicActionDto dto = new DynamicActionDto();
		dto.setUserUploadRecord(record);
		
		HttpServletRequest request = ServletActionContext.getRequest();
		String ip = ClientUserIp.getIpAddr(request);  
        dto.setIp(ip);
		String userA = request.getHeader("user-agent");
		String userAgent = HttpRequestDeviceUtils.getMediaDevice(userA);
		dto.setTerminal(userAgent);//终端
		dto.setPageid(pageid);
		dto.setSource(source);
		String rs = featureCompose.dynamicAction(featureid, uid, dto);//保存record
		if (IPageConstants.FEATURE_MGR_RESULT_SUCCESS.equalsIgnoreCase(rs)) {
			result= "Y" ;
		} else {
			result= "系统发生错误!" ;
			removePicture(path+"/"+name1, path+"/"+name2, path+"/"+name3);
		}
		return SUCCESS;

	}
	
	private void removePicture(String name1,String name2,String name3){
		File f = new File(HyConfig.getRootPath()+name1);
		if(f.exists()){
			f.delete();
		}
		f = new File(HyConfig.getRootPath()+name2);
		if(f.exists()){
			f.delete();
		}
		f = new File(HyConfig.getRootPath()+name3);
		if(f.exists()){
			f.delete();
		}
	}
	
	public String picUpload() throws Exception{
		String fileSuffix = FileUploadService.getFileSuffix(imgFileName);
		if(img.length() > (5*1024*1024)){
			result= "The file size is larger than 5M!" ;
		}
		else if(suffixMap.get(fileSuffix.toUpperCase()) == null){
			result= "The file format is not supported!" ;
		}else{
			Account account = (Account) ActionContext.getContext().getSession().get("account");
			String imgpath = FileUploadService.getFilePath(IPageConstants.TYPE_CONTENT, account.getOwner().getId(), IPageConstants.CONTENT_PICTURE_FILEFATH);
			if (img != null) {
				String simgfileName = FileUploadService.createFileName(imgFileName);
				String result1 = FileUploadService.saveFile(img, imgpath, simgfileName);
				result = result1;
			}
		}
		HttpServletResponse response = ServletActionContext.getResponse();
        response.setHeader("pragma", "no-cache");
        response.setHeader("cache-control", "no-cache");
        response.setContentType("application/json;charset=utf-8");
        PrintWriter pw = response.getWriter();
        Gson gs = new Gson();
        pw.print(gs.toJson(result));
        pw.flush();
        pw.close();
        return null;
	}

	public void setFeatureCompose(IFeatureCompose featureCompose) {
		this.featureCompose = featureCompose;
	}

	public long getFeatureid() {
		return featureid;
	}

	public void setFeatureid(long featureid) {
		this.featureid = featureid;
	}

	public long getPageid() {
		return pageid;
	}

	public void setPageid(long pageid) {
		this.pageid = pageid;
	}

	public File getImg() {
		return img;
	}

	public void setImg(File img) {
		this.img = img;
	}

	public String getImgFileName() {
		return imgFileName;
	}

	public void setImgFileName(String imgFileName) {
		this.imgFileName = imgFileName;
	}

	public String getImgContentType() {
		return imgContentType;
	}

	public void setImgContentType(String imgContentType) {
		this.imgContentType = imgContentType;
	}

	public String getResult() {
		return result;
	}
	
	public String shareUploadPic() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		long ownerid = this.getOwner();
		if(ownerid==0)
		{
		if(fid == 0){
			ownerid = pageCompose.findOwnerByPageid(pageid);
		}else{
			ownerid = featureCompose.findOwneridByPageidAndFid(pageid, fid);//ownerid			
		}
		}
		String path = FileUploadService.getFilePath(IPageConstants.TYPE_FEATURE, ownerid, featureid+"");//路径
		String name = FileUploadService.createFileName(imgFileName);
		FileUploadService.saveFile(img, path, name);
		String picUrl = path+"/"+name;
		out.print("{\"status\":1,\"picUrl\":\""+picUrl+"\"}");
		out.flush();
		out.close();
		return null;
	}
	
	public String h5UploadPic() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		response.addHeader("Access-Control-Allow-Origin", HyConfig.getPageyuming(this.getOwner()));
		response.addHeader("Access-Control-Allow-Methods", "HEAD,GET,POST,PUT,DELETE,OPTIONS");
        response.addHeader("Access-Control-Allow-Headers", "Content-Type,Origin,Accept");
        response.addHeader("Access-Control-Max-Age", "120");
		PrintWriter out = response.getWriter();
		HttpServletRequest request = ServletActionContext.getRequest();
		if("OPTIONS".equals(request.getMethod())){
			return null;
		}
		long ownerid = this.getOwner();
		if(ownerid==0)
		{
		if(fid == 0){
			ownerid = pageCompose.findOwnerByPageid(pageid);
		}else{
			ownerid = featureCompose.findOwneridByPageidAndFid(pageid, fid);//ownerid			
		}
		}
		
		String path = FileUploadService.getFilePath(IPageConstants.TYPE_FEATURE, ownerid, featureid+"");//路径
		String fname = FileUploadService.createFileName(name);
		String filepath=HyConfig.getRootPath()+path;
		File dir = new File(filepath);
		if (!dir.exists())
		{
			dir.mkdirs();
		}
		File file = new File(filepath, fname);
        FileOutputStream fout = new FileOutputStream(file);
        IOUtils.copy(request.getInputStream(), fout);
        fout.flush();
		fout.close();
		String picUrl = path+"/"+fname;
		out.print("{\"status\":1,\"picUrl\":\""+picUrl+"\",\"picName\":\""+name+"\"}");
		out.flush();
		out.close();
		return null;
	}
	
	public String getFeature() {
		return feature;
	}
	public void setFeature(String feature) {
		this.feature = feature;
	}
	public File getPic() {
		return pic;
	}
	public void setPic(File pic) {
		this.pic = pic;
	}
	public String getPicFileName() {
		return picFileName;
	}
	public void setPicFileName(String picFileName) {
		this.picFileName = picFileName;
	}
	public String getPicContentType() {
		return picContentType;
	}
	public void setPicContentType(String picContentType) {
		this.picContentType = picContentType;
	}
	public File getPic1() {
		return pic1;
	}
	public void setPic1(File pic1) {
		this.pic1 = pic1;
	}
	public String getPic1FileName() {
		return pic1FileName;
	}
	public void setPic1FileName(String pic1FileName) {
		this.pic1FileName = pic1FileName;
	}
	public String getPic1ContentType() {
		return pic1ContentType;
	}
	public void setPic1ContentType(String pic1ContentType) {
		this.pic1ContentType = pic1ContentType;
	}
	public File getPic2() {
		return pic2;
	}
	public void setPic2(File pic2) {
		this.pic2 = pic2;
	}
	public String getPic2FileName() {
		return pic2FileName;
	}
	public void setPic2FileName(String pic2FileName) {
		this.pic2FileName = pic2FileName;
	}
	public String getPic2ContentType() {
		return pic2ContentType;
	}
	public void setPic2ContentType(String pic2ContentType) {
		this.pic2ContentType = pic2ContentType;
	}
	public File getPic3() {
		return pic3;
	}
	public void setPic3(File pic3) {
		this.pic3 = pic3;
	}
	public String getPic3FileName() {
		return pic3FileName;
	}
	public void setPic3FileName(String pic3FileName) {
		this.pic3FileName = pic3FileName;
	}
	public String getPic3ContentType() {
		return pic3ContentType;
	}
	public void setPic3ContentType(String pic3ContentType) {
		this.pic3ContentType = pic3ContentType;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}

	public static void main(String[] args) throws Exception
	{
		String data = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAASwAAACWCAYAAABkW7XSAAAAAXNSR0IArs4c6QAAAylJREFUeAHt0DEBAAAAwqD1T20IX4hAYcCAAQMGDBgwYMCAAQMGDBgwYMCAAQMGDBgwYMCAAQMGDBgwYMCAAQMGDBgwYMCAAQMGDBgwYMCAAQMGDBgwYMCAAQMGDBgwYMCAAQMGDBgwYMCAAQMGDBgwYMCAAQMGDBgwYMCAAQMGDBgwYMCAAQMGDBgwYMCAAQMGDBgwYMCAAQMGDBgwYMCAAQMGDBgwYMCAAQMGDBgwYMCAAQMGDBgwYMCAAQMGDBgwYMCAAQMGDBgwYMCAAQMGDBgwYMCAAQMGDBgwYMCAAQMGDBgwYMCAAQMGDBgwYMCAAQMGDBgwYMCAAQMGDBgwYMCAAQMGDBgwYMCAAQMGDBgwYMCAAQMGDBgwYMCAAQMGDBgwYMCAAQMGDBgwYMCAAQMGDBgwYMCAAQMGDBgwYMCAAQMGDBgwYMCAAQMGDBgwYMCAAQMGDBgwYMCAAQMGDBgwYMCAAQMGDBgwYMCAAQMGDBgwYMCAAQMGDBgwYMCAAQMGDBgwYMCAAQMGDBgwYMCAAQMGDBgwYMCAAQMGDBgwYMCAAQMGDBgwYMCAAQMGDBgwYMCAAQMGDBgwYMCAAQMGDBgwYMCAAQMGDBgwYMCAAQMGDBgwYMCAAQMGDBgwYMCAAQMGDBgwYMCAAQMGDBgwYMCAAQMGDBgwYMCAAQMGDBgwYMCAAQMGDBgwYMCAAQMGDBgwYMCAAQMGDBgwYMCAAQMGDBgwYMCAAQMGDBgwYMCAAQMGDBgwYMCAAQMGDBgwYMCAAQMGDBgwYMCAAQMGDBgwYMCAAQMGDBgwYMCAAQMGDBgwYMCAAQMGDBgwYMCAAQMGDBgwYMCAAQMGDBgwYMCAAQMGDBgwYMCAAQMGDBgwYMCAAQMGDBgwYMCAAQMGDBgwYMCAAQMGDBgwYMCAAQMGDBgwYMCAAQMGDBgwYMCAAQMGDBgwYMCAAQMGDBgwYMCAAQMGDBgwYMCAAQMGDBgwYMCAAQMGDBgwYMCAAQMGDBgwYMCAAQMGDBgwYMCAAQMGDBgwYMCAAQMGDBgwYMCAAQMGDBgwYMCAAQMGDBgwYOAdGL/UAAEPpnR6AAAAAElFTkSuQmCC";
		decode(data, "D:/1.jpg");
	}
	
	private String imageData;
	
	public String getImageData()
	{
		return imageData;
	}
	
	public void setImageData(String imageData)
	{
		this.imageData = imageData;
	}
	public void imgData() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		long ownerid = this.getOwner();
		String path = FileUploadService.getFilePath(IPageConstants.TYPE_FEATURE, ownerid, featureid+"");//路径
		String name = FileUploadService.createFileName("xx.jpg");
		String picUrl = path+"/"+name;
		out.print("{\"status\":1,\"picUrl\":\""+picUrl+"\"}");
		out.flush();
		out.close();
		decode(imageData, HyConfig.getRootPath()+path+"/"+name);
	}
	
	 public static void decode(String s,String path){  
	        BASE64Decoder decoder=new BASE64Decoder();  
	        try {  
	            byte[] bytes=decoder.decodeBuffer(s);  
	            saveImage(bytes, path);
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        }  
	    }
	 public static void saveImage(byte[] imageBytes,String path){  
		    File file=new File(path);  
		    FileOutputStream outputStream=null;  
		    try {  
		        outputStream=new FileOutputStream(file);  
		        outputStream.write(imageBytes);  
		    } catch (FileNotFoundException e) {  
		        e.printStackTrace();  
		    } catch (IOException e) {  
		        e.printStackTrace();  
		    } finally{  
		        closeSteam(outputStream);  
		    }  
		}  
	 public static void closeSteam(FileOutputStream outputStream){  
		    if(outputStream!=null){  
		        try {  
		            outputStream.close();  
		        } catch (IOException e) {  
		            e.printStackTrace();  
		        }  
		    }  
		}  
}
