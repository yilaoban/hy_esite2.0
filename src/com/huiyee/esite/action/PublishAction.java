package com.huiyee.esite.action;

import java.io.File;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;

import com.huiyee.esite.dto.PublishDto;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.model.PageAddress;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;


public class PublishAction extends AbstractAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6002337145682705393L;
	
	private long siteid;
	private PublishDto pbdto;
	private long pageid;
	private long appid;
	private String publishUrl;
	private String refUrl;
	private String state;
	private String oldurl;
	private String code;
	private String name;
	private String appID;
	private String appSecret;
	private String pic;
	private String infoed;
	private File img;
	private String imgFileName;
	private String imgContentType;
	private String wx_url;
	private String result;
	
	@Override
	public String execute() throws Exception{
		pbdto=(PublishDto)pageCompose.composePublishSinaIndex(siteid);
		return SUCCESS;
	}
	
	public String publishLink() throws Exception
	{
		String state = pageid + "," + appid+","+oldurl;
		publishUrl = this.pageCompose.findPublishLink(state, appid,pageid);
		return SUCCESS;
	}
	
	public String publish() throws Exception
	{
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		String[] strs = state.split(",");
		long pageid = Long.valueOf(strs[0]);
		long appid = Long.valueOf(strs[1]);
		String oldurl = strs[2];
		refUrl = this.pageCompose.publishByCode(appid, code, pageid,account.getOwner().getId());
		if (!oldurl.trim().equals("null"))
		{
			refUrl = oldurl;
		}
		return Action.SUCCESS;
	}

	public String qq() throws Exception{
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		//pbdto=(PublishDto)pageCompose.composePublishQQIndex(siteid,account.getOwner().getId());
		return SUCCESS;
	}
	
//	public String qqSub() throws Exception{
//		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
//		wx_url = pageCompose.composePublic2qq(pageid,account.getOwner().getId(),name, appID, appSecret, pic,infoed);
//		return SUCCESS;
//	}
	
	public String weixinSub()throws Exception{
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
	//	wx_url = pageCompose.weixinSub(pageid, account.getOwner().getId(), name, appID, appSecret, pic, infoed);
		return SUCCESS;
	}
	
	public String findSinaTokenIsExit()throws Exception{
		PageAddress pa = pageCompose.findSinaTokenIsExit(pageid);
		JSONArray json =JSONArray.fromObject(pa);
		String jsonStr=json.toString();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(jsonStr);
		out.flush();
		out.close();
		return null;
	}
	
	public String subWeiBoFlow()throws Exception{
		return SUCCESS;
	}
	
	public String subWeiBoFlow1()throws Exception{
		return SUCCESS;
	}
	
	public String mymap()throws Exception{
		return SUCCESS;
	}
	
	public long getSiteid()
	{
		return siteid;
	}

	public void setSiteid(long siteid)
	{
		this.siteid = siteid;
	}

	public PublishDto getPbdto()
	{
		return pbdto;
	}

	public long getPageid()
	{
		return pageid;
	}

	public void setPageid(long pageid)
	{
		this.pageid = pageid;
	}

	public long getAppid()
	{
		return appid;
	}

	public void setAppid(long appid)
	{
		this.appid = appid;
	}

	public String getPublishUrl()
	{
		return publishUrl;
	}

	public void setPublishUrl(String publishUrl)
	{
		this.publishUrl = publishUrl;
	}

	public String getRefUrl()
	{
		return refUrl;
	}

	public void setRefUrl(String refUrl)
	{
		this.refUrl = refUrl;
	}

	public String getState()
	{
		return state;
	}

	public void setState(String state)
	{
		this.state = state;
	}

	public String getOldurl()
	{
		return oldurl;
	}

	public void setOldurl(String oldurl)
	{
		this.oldurl = oldurl;
	}

	public String getCode()
	{
		return code;
	}

	public void setCode(String code)
	{
		this.code = code;
	}

	public String getAppID() {
		return appID;
	}

	public void setAppID(String appID) {
		this.appID = appID;
	}

	public String getAppSecret() {
		return appSecret;
	}

	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
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

	public String getWx_url() {
		return wx_url;
	}

	public void setWx_url(String wx_url) {
		this.wx_url = wx_url;
	}

	public String getInfoed() {
		return infoed;
	}

	public void setInfoed(String infoed) {
		this.infoed = infoed;
	}

	public String getResult()
	{
		return result;
	}

	public void setResult(String result)
	{
		this.result = result;
	}
}
