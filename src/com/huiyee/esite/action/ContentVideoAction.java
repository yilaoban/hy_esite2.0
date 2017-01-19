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
import com.huiyee.esite.model.ContentVideo;
import com.huiyee.esite.service.FileUploadService;
import com.opensymphony.xwork2.ActionContext;

public class ContentVideoAction extends AbstractAction {
	private long ccid;
	private long contentId;
	private String title;
	private File img;
	private String imgFileName;
	private String imgContentType;
	private String imgurl;
	private String videourl;
	private String html;
	private String status;
	private ContentDto dto;
	private String linkurl;
	
	private JSONObject tagJson;

	public String addVideoPre() throws Exception {
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		dto = (ContentDto) pageCompose.addContentPre(account, IPageConstants.CONTENT_VIDEO, ccid);
		return SUCCESS;
	}

	public String addVideoSub() throws Exception {
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		
		if(StringUtils.isEmpty(title)){
			this.addFieldError("error1", "标题不能为空");
			dto = (ContentDto) pageCompose.addContentPre(account, IPageConstants.CONTENT_VIDEO, ccid);
			return "fail";
		}
		if(StringUtils.isEmpty(videourl)&&StringUtils.isEmpty(html)){
			this.addFieldError("error2", "链接不能为空");
			dto = (ContentDto) pageCompose.addContentPre(account, IPageConstants.CONTENT_VIDEO, ccid);
			return "fail";
		}
		ContentVideo video = new ContentVideo();
		video.setOwnerid(account.getOwner().getId());
		video.setCatid(ccid);
		video.setTitle(title);
		video.setVideourl(videourl);
		video.setStatus(status);

		String imgpath = FileUploadService.getFilePath(IPageConstants.TYPE_CONTENT, account.getOwner().getId(), IPageConstants.CONTENT_VIDEO_FILEFATH);
		if (img != null) {
			String simgfileName = FileUploadService.createFileName(imgFileName);
			String result1 = FileUploadService.saveFile(img, imgpath, simgfileName);
			video.setPicurl(result1);
		}
		long productid = pageCompose.saveVideo(video,tagJson);
		return SUCCESS;
	}

	public String showVideo() throws Exception {
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		dto = (ContentDto) pageCompose.findVideoById(contentId, account);
		return SUCCESS;
	}

	public String editVideoPre() throws Exception {
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		dto = (ContentDto) pageCompose.findVideoById(contentId, account);
		return SUCCESS;
	}

	public String editVideoSub() throws Exception {
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		if(StringUtils.isEmpty(title)){
			this.addFieldError("error1", "标题不能为空");
			dto = (ContentDto) pageCompose.findVideoById(contentId, account);
			return "fail";
		}
		if(StringUtils.isEmpty(videourl)&&StringUtils.isEmpty(html)){
			this.addFieldError("error2", "链接不能为空");
			dto = (ContentDto) pageCompose.findVideoById(contentId, account);
			return "fail";
		}
		
		ContentVideo video = new ContentVideo();
		video.setId(contentId);
		video.setOwnerid(account.getOwner().getId());
		video.setCatid(ccid);
		video.setTitle(title);
		video.setVideourl(videourl);
		video.setStatus(status);
		if (img != null) {
			String imgpath = FileUploadService.getFilePath(IPageConstants.TYPE_CONTENT, account.getOwner().getId(), IPageConstants.CONTENT_VIDEO_FILEFATH);
			String simgfileName = FileUploadService.createFileName(imgFileName);
			String result1 = FileUploadService.saveFile(img, imgpath, simgfileName);
			video.setPicurl(result1);
		} else {
			video.setPicurl(imgurl);
		}
		int reuslt = pageCompose.editVideo(video,tagJson);
		return SUCCESS;
	}

	public String shenhePre() throws Exception {
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		dto = (ContentDto) pageCompose.findVideoById(contentId, account);
		return SUCCESS;
	}

	public String shenheSub() throws Exception {
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		int result = pageCompose.checkVideo(contentId, status, account);
		return SUCCESS;
	}

	public String delVideo() throws Exception {
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		int result = pageCompose.delVideo(contentId, account.getOwner().getId());
		Gson gson = new Gson();
		out.print(gson.toJson(result));
		out.flush();
		out.close();
		return null;
	}

	public String publicVideo() throws Exception {
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		int result = pageCompose.publicVideo(contentId, account.getOwner().getId());
		Gson gson = new Gson();
		out.print(gson.toJson(result));
		out.flush();
		out.close();
		return null;
	}
	
	public String offVideo() throws Exception {
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		int result = pageCompose.offVideo(contentId, account.getOwner().getId());
		Gson gson = new Gson();
		out.print(gson.toJson(result));
		out.flush();
		out.close();
		return null;
	}
	
	public long getCcid() {
		return ccid;
	}

	public void setCcid(long ccid) {
		this.ccid = ccid;
	}

	public long getContentId() {
		return contentId;
	}

	public void setContentId(long contentId) {
		this.contentId = contentId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public String getVideourl() {
		return videourl;
	}

	public void setVideourl(String videourl) {
		this.videourl = videourl;
	}

	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}

	public String getStatus() {
		return status;
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

	
	public JSONObject getTagJson()
	{
		return tagJson;
	}

	
	public void setTagJson(JSONObject tagJson)
	{
		this.tagJson = tagJson;
	}

}
