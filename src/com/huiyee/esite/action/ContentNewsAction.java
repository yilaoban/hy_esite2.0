package com.huiyee.esite.action;

import java.io.File;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.huiyee.esite.constants.IPageConstants;
import com.huiyee.esite.dto.ContentDto;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.model.ContentNew;
import com.huiyee.esite.service.FileUploadService;
import com.opensymphony.xwork2.ActionContext;

public class ContentNewsAction extends AbstractAction {
	private ContentDto dto;

	private long ccid;
	private String title;
	private String content;
	private String status;

	private long contentId;
	
	private String linkurl;
	private File simg;
	private String simgFileName;
	private String simgContentType;
	private File bimg;
	private String bimgFileName;
	private String bimgContentType;
	private String shortdesc;
	private String simgurl;
	private String bimgurl;
	private String islink;
	private String radio;
	private String startTime;
	private String endTime;
	private String author;
	private String source;
	private String publishtime;
	
	private int initialZan;
	private int initialRead;
	
	private JSONObject tagJson;
	
	public String addNewPre() throws Exception {
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		dto = (ContentDto) pageCompose.addContentPre(account, IPageConstants.CONTENT_NEW,ccid);
		return SUCCESS;
	}

	public String addNewSub() throws Exception {
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		ContentNew cn = new ContentNew();
		
		if(StringUtils.isEmpty(title)){
			this.addFieldError("error", "标题不能为空");
			dto = (ContentDto) pageCompose.addContentPre(account, IPageConstants.CONTENT_NEW,ccid);
			return "fail";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		if(startTime == null || !"".equals(startTime)){
			cn.setStartTime(sdf.parse(startTime));
		}
		if(endTime == null || !"".equals(endTime)){
			cn.setEndTime(sdf.parse(endTime));
		}
		if(publishtime == null || !"".equals(publishtime)){
			cn.setPublishtime(sdf.parse(publishtime));
		}
		cn.setAuthor(author);
		cn.setSource(source);
		cn.setOwnerid(account.getOwner().getId());
		cn.setCatid(ccid);
		cn.setTitle(title);
		cn.setContent(content==null?"":content);
		cn.setStatus(status);
		cn.setLinkurl(linkurl);
		cn.setShortdesc(shortdesc);
		cn.setIslink(islink);
		cn.setInitialRead(initialRead);
		cn.setInitialZan(initialZan);
		String imgfilePath = FileUploadService.getFilePath(IPageConstants.TYPE_NEWS, account.getOwner().getId(), IPageConstants.CONTENT_NEW_FILEFATH);
		// 小图
		if (simg != null) {
			String simgfileName = FileUploadService.createFileName(simgFileName);
			String result1 = FileUploadService.saveFile(simg, imgfilePath, simgfileName);
			cn.setSimgurl(result1);
		}

		// 大图
		if (bimg != null) {
			String bimgfileName = FileUploadService.createFileName(bimgFileName);
			String result2 = FileUploadService.saveFile(bimg, imgfilePath, bimgfileName);
			cn.setBimgurl(result2);
		}
		long newid = pageCompose.saveNew(cn,tagJson);
		return SUCCESS;
	}

	public String showNew() throws Exception {
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		dto = (ContentDto) pageCompose.findNewById(contentId, account);
		return SUCCESS;
	}

	public String editNewPre() throws Exception {
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		dto = (ContentDto) pageCompose.findNewById(contentId, account);
		return SUCCESS;
	}

	public String editNewSub() throws Exception {
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		
		if(StringUtils.isEmpty(title)){
			this.addFieldError("error", "标题不能为空");
			dto = (ContentDto) pageCompose.findNewById(contentId, account);
			return "fail";
		}
		ContentNew cn = new ContentNew();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		if(startTime == null || !"".equals(startTime)){
			cn.setStartTime(sdf.parse(startTime));
		}
		if(endTime == null || !"".equals(endTime)){
			cn.setEndTime(sdf.parse(endTime));
		}
		if(publishtime == null || !"".equals(publishtime)){
			cn.setPublishtime(sdf.parse(publishtime));
		}
		cn.setId(contentId);
		cn.setAuthor(author);
		cn.setSource(source);
		cn.setOwnerid(account.getOwner().getId());
		cn.setCatid(ccid);
		cn.setTitle(title);
		if("N".equals(islink)){
			String str="";
			cn.setLinkurl(str);
			cn.setContent(content==null?"":content);
		}else{
			String str="";
			cn.setContent(str);
			cn.setLinkurl(linkurl);
		}
		cn.setIslink(islink);
		cn.setStatus(status);
		cn.setShortdesc(shortdesc.replaceAll("\r\n", "<br/>"));
		cn.setInitialRead(initialRead);
		cn.setInitialZan(initialZan);
		String imgfilePath = FileUploadService.getFilePath(IPageConstants.TYPE_CONTENT, account.getOwner().getId(), IPageConstants.CONTENT_PRODUCT_FILEFATH);
		if (simg != null) {
			// 小图
			String simgfileName = FileUploadService.createFileName(simgFileName);
			String result1 = FileUploadService.saveFile(simg, imgfilePath, simgfileName);
			cn.setSimgurl(result1);

		} else {
			cn.setSimgurl(simgurl);
		}

		if (bimg != null) {
			// 大图
			String bimgfileName = FileUploadService.createFileName(bimgFileName);
			String result2 = FileUploadService.saveFile(bimg, imgfilePath, bimgfileName);
			cn.setBimgurl(result2);
		} else {
			cn.setBimgurl(bimgurl);
		}
		int result = pageCompose.editNew(cn,tagJson);
		return SUCCESS;
	}

	public String shenhePre() throws Exception {
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		dto = (ContentDto) pageCompose.findNewById(contentId, account);
		return SUCCESS;
	}

	public String shenheSub() throws Exception {
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		int result = pageCompose.checkNew(contentId, status, account);
		return SUCCESS;
	}

	public String delNew() throws Exception {
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		int result = pageCompose.delNew(contentId, account.getOwner().getId());
		Gson gson = new Gson();
		out.print(gson.toJson(result));
		out.flush();
		out.close();
		return null;
	}

	public String publicNew() throws Exception {
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		int result = pageCompose.publicNew(contentId, account.getOwner().getId());
		Gson gson = new Gson();
		out.print(gson.toJson(result));
		out.flush();
		out.close();
		return null;
	}
	
	public String offNew() throws Exception {
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		int result = pageCompose.offNew(contentId, account.getOwner().getId());
		Gson gson = new Gson();
		out.print(gson.toJson(result));
		out.flush();
		out.close();
		return null;
	}

	public String ajaxFindNews() throws Exception {
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		List<ContentNew> list = pageCompose.findNewsByOwner(account.getOwner().getId());
		Gson gson = new Gson();
		out.print(gson.toJson(list));
		out.flush();
		out.close();
		return null;
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

	public void setCcid(long ccid) {
		this.ccid = ccid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public long getContentId() {
		return contentId;
	}

	public void setContentId(long contentId) {
		this.contentId = contentId;
	}

	public String getLinkurl()
	{
		return linkurl;
	}

	public void setLinkurl(String linkurl)
	{
		this.linkurl = linkurl;
	}

	public File getSimg()
	{
		return simg;
	}

	public void setSimg(File simg)
	{
		this.simg = simg;
	}

	public String getSimgFileName()
	{
		return simgFileName;
	}

	public void setSimgFileName(String simgFileName)
	{
		this.simgFileName = simgFileName;
	}

	public String getSimgContentType()
	{
		return simgContentType;
	}

	public void setSimgContentType(String simgContentType)
	{
		this.simgContentType = simgContentType;
	}

	public File getBimg()
	{
		return bimg;
	}

	public void setBimg(File bimg)
	{
		this.bimg = bimg;
	}

	public String getBimgFileName()
	{
		return bimgFileName;
	}

	public void setBimgFileName(String bimgFileName)
	{
		this.bimgFileName = bimgFileName;
	}

	public String getBimgContentType()
	{
		return bimgContentType;
	}

	public void setBimgContentType(String bimgContentType)
	{
		this.bimgContentType = bimgContentType;
	}

	public String getShortdesc()
	{
		return shortdesc;
	}

	public void setShortdesc(String shortdesc)
	{
		this.shortdesc = shortdesc;
	}

	public String getSimgurl()
	{
		return simgurl;
	}

	public void setSimgurl(String simgurl)
	{
		this.simgurl = simgurl;
	}

	public String getBimgurl()
	{
		return bimgurl;
	}

	public void setBimgurl(String bimgurl)
	{
		this.bimgurl = bimgurl;
	}

	public String getIslink()
	{
		return islink;
	}

	public void setIslink(String islink)
	{
		this.islink = islink;
	}

	public String getRadio()
	{
		return radio;
	}

	public void setRadio(String radio)
	{
		this.radio = radio;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	
	public String getAuthor()
	{
		return author;
	}

	
	public void setAuthor(String author)
	{
		this.author = author;
	}

	
	public String getSource()
	{
		return source;
	}

	
	public void setSource(String source)
	{
		this.source = source;
	}

	
	public String getPublishtime()
	{
		return publishtime;
	}

	
	public void setPublishtime(String publishtime)
	{
		this.publishtime = publishtime;
	}

	
	public int getInitialZan()
	{
		return initialZan;
	}

	
	public void setInitialZan(int initialZan)
	{
		this.initialZan = initialZan;
	}

	
	public int getInitialRead()
	{
		return initialRead;
	}

	
	public void setInitialRead(int initialRead)
	{
		this.initialRead = initialRead;
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
