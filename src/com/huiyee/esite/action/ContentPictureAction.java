package com.huiyee.esite.action;

import java.io.File;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.huiyee.esite.constants.IPageConstants;
import com.huiyee.esite.dto.ContentDto;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.model.ContentPicture;
import com.huiyee.esite.model.ContentProduct;
import com.huiyee.esite.service.FileUploadService;
import com.opensymphony.xwork2.ActionContext;

public class ContentPictureAction extends AbstractAction {

	private ContentDto dto;
	private long contentId;
	private long ccid;
	private String title;
	private File img;
	private String imgFileName;
	private String imgContentType;
	private String status;
	private String imgurl;
	private String linkurl;
	private String tag;
	private String content;
	private JSONObject tagJson;

	public String addPicturePre() throws Exception {
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		dto = (ContentDto) pageCompose.addContentPre(account, IPageConstants.CONTENT_PICTURE, ccid);
		return SUCCESS;
	}

	public String addPictureSub() throws Exception {
		Account account = (Account) ActionContext.getContext().getSession().get("account");

		if(StringUtils.isEmpty(title)){
			this.addFieldError("error", "标题不能为空");
			dto = (ContentDto) pageCompose.addContentPre(account, IPageConstants.CONTENT_PICTURE, ccid);
			return "fail";
		}
		if(img==null){
			this.addFieldError("error", "图片不能为空");
			dto = (ContentDto) pageCompose.addContentPre(account, IPageConstants.CONTENT_PICTURE, ccid);
			return "fail";
		}
		
		ContentPicture picture = new ContentPicture();
		picture.setOwnerid(account.getOwner().getId());
		picture.setCatid(ccid);
		picture.setStatus(status);
		picture.setTitle(title);
		picture.setLinkurl(linkurl);
		picture.setTag(tag);

		String imgpath = FileUploadService.getFilePath(IPageConstants.TYPE_CONTENT, account.getOwner().getId(), IPageConstants.CONTENT_PICTURE_FILEFATH);
		if (img != null) {
			String simgfileName = FileUploadService.createFileName(imgFileName);
			String result1 = FileUploadService.saveFile(img, imgpath, simgfileName);
			picture.setImgurl(result1);
		}

		long productid = pageCompose.savePicture(picture,tagJson);
		return SUCCESS;
	}

	public String showPicture() throws Exception {
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		dto = (ContentDto) pageCompose.findPictureById(contentId, account);
		return SUCCESS;
	}

	public String editPicturePre() throws Exception {
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		dto = (ContentDto) pageCompose.findPictureById(contentId, account);
		return SUCCESS;
	}

	public String editPictureSub() throws Exception {
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		
		if(StringUtils.isEmpty(title)){
			this.addFieldError("error", "标题不能为空");
			dto = (ContentDto) pageCompose.findPictureById(contentId, account);
			return "fail";
		}
		
		ContentPicture picture = new ContentPicture();
		picture.setOwnerid(account.getOwner().getId());
		picture.setId(contentId);
		picture.setCatid(ccid);
		picture.setStatus(status);
		picture.setTitle(title);
		picture.setLinkurl(linkurl);
		picture.setTag(tag);
		if (img != null) {
			String imgpath = FileUploadService.getFilePath(IPageConstants.TYPE_CONTENT, account.getOwner().getId(), IPageConstants.CONTENT_PICTURE_FILEFATH);
			String simgfileName = FileUploadService.createFileName(imgFileName);
			String result1 = FileUploadService.saveFile(img, imgpath, simgfileName);
			picture.setImgurl(result1);
		} else {
			picture.setImgurl(imgurl);
		}
		int reuslt = pageCompose.editPicture(picture,tagJson);
		return SUCCESS;
	}

	public String delPicture() throws Exception {
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		int result = pageCompose.delPicture(contentId, account.getOwner().getId());
		Gson gson = new Gson();
		out.print(gson.toJson(result));
		out.flush();
		out.close();
		return null;
	}

	public String shenhePre() throws Exception {
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		dto = (ContentDto) pageCompose.findPictureById(contentId, account);
		return SUCCESS;
	}

	public String shenheSub() throws Exception {
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		int result = pageCompose.checkPicture(contentId, status, account);
		return SUCCESS;
	}

	public String publicPicture() throws Exception {
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		int result = pageCompose.publicPicture(contentId, account.getOwner().getId());
		Gson gson = new Gson();
		out.print(gson.toJson(result));
		out.flush();
		out.close();
		return null;
	}
	
	public String offPicture() throws Exception {
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		int result = pageCompose.offPicture(contentId, account.getOwner().getId());
		Gson gson = new Gson();
		out.print(gson.toJson(result));
		out.flush();
		out.close();
		return null;
	}
	
	
	public void setCcid(long ccid) {
		this.ccid = ccid;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setImg(File img) {
		this.img = img;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public ContentDto getDto() {
		return dto;
	}

	public void setDto(ContentDto dto) {
		this.dto = dto;
	}

	public long getCcid() {
		return ccid;
	}

	public String getTitle() {
		return title;
	}

	public File getImg() {
		return img;
	}

	public String getStatus() {
		return status;
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

	public long getContentId() {
		return contentId;
	}

	public void setContentId(long contentId) {
		this.contentId = contentId;
	}

	public String getImgurl() {
		return imgurl;
	}

	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}

	public String getLinkurl()
	{
		return linkurl;
	}

	public void setLinkurl(String linkurl)
	{
		this.linkurl = linkurl;
	}

	public String getTag()
	{
		return tag;
	}

	public void setTag(String tag)
	{
		this.tag = tag;
	}

	public String getContent()
	{
		return content;
	}

	public void setContent(String content)
	{
		this.content = content;
	}

	
	public JSONObject getTagJson()
	{
		return tagJson;
	}

	
	public void setTagJson(JSONObject tagJson)
	{
		this.tagJson = tagJson;
	}

}
