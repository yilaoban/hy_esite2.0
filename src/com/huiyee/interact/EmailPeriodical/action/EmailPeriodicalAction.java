package com.huiyee.interact.EmailPeriodical.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.huiyee.esite.action.InteractModelAction;
import com.huiyee.esite.constants.IInteractConstants;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.util.ClientUserIp;
import com.huiyee.interact.EmailPeriodical.dto.EmailPeriodicalDto;
import com.huiyee.interact.EmailPeriodical.mgr.IEmailPeriodicalManager;
import com.huiyee.interact.EmailPeriodical.model.EmailPeriodicalModel;
import com.huiyee.interact.EmailPeriodical.model.Pager;

public class EmailPeriodicalAction extends InteractModelAction{
	
	private IEmailPeriodicalManager emailPeriodicalManager;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1406942862631777018L;
	private long id;
	private String title;
	private String url;
	private String status="EDT";
	private String publish="N";
	private EmailPeriodicalModel model;
	private String result;
	private int pageId=1;
	private EmailPeriodicalDto dto;
	private String _;
	private String callback;
	private long mid=10001;
	
	public String get_() {
		return _;
	}
	public void set_(String _) {
		this._ = _;
	}
	public String getCallback() {
		return callback;
	}
	public void setCallback(String callback) {
		this.callback = callback;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String saveEmailPeriodical()throws Exception{
		Account account=(Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		long ownerid=account.getOwner().getId();
		HttpServletRequest req=(HttpServletRequest)ServletActionContext.getRequest();
		String visitIp=ClientUserIp.getIpAddr(req);
		long resultid=emailPeriodicalManager.saveEmailPeriodical(title, url, status, publish, ownerid);
		if(resultid>0){
			result="Y";
		}
		else{
			result = "N";
		}
		String callback = ServletActionContext.getRequest().getParameter("callback");
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		response.sendRedirect("/interact/index.action?mid=10001");
		PrintWriter out = response.getWriter();
		Gson g = new Gson();
		out.print(callback + "(" + g.toJson(result) + ")");
		out.flush();
		out.close();
		return null;
	}
	
	public String searchEmailPeriodical()throws Exception{
		Account account=(Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		long ownerid=account.getOwner().getId();
		dto=new EmailPeriodicalDto();
		int start=(pageId-1)*IInteractConstants.EMAIL_LIMIT;
		int total=emailPeriodicalManager.searchEmailPeriodicalTotal(ownerid);
		dto.setTotal(total);
		dto.setList(emailPeriodicalManager.searchEmailPeriodical(ownerid, start, IInteractConstants.EMAIL_LIMIT));
		Pager pager=new Pager(pageId,total,IInteractConstants.EMAIL_LIMIT);
		dto.setPager(pager);
		return SUCCESS;
	}
	
	public String deleteEmailPeriodical()throws Exception{
		int len = emailPeriodicalManager.deleteEmailPeriodical(id);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.write(len);
		out.flush();
		out.close();
		return null;
	}
	
	public String changePublish()throws Exception{
		Account account=(Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		long ownerid=account.getOwner().getId();
		int len = emailPeriodicalManager.updatePublish(id,ownerid);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.write(len);
		out.flush();
		out.close();
		return null;
	}
	
	public String updateEP() throws Exception
	{
		dto = new EmailPeriodicalDto();
		EmailPeriodicalModel ep = emailPeriodicalManager.findemailpublishByLpid(id);
		dto.setEmailPeriodical(ep);
		return SUCCESS;
	}

	public String update() throws Exception
	{
	
		if (StringUtils.isEmpty(title))
		{
			result = "标题不能为空";
		}else if(StringUtils.isEmpty(url)){
			result = "连接不能为空";
		}
		if(StringUtils.isEmpty(result))
		{
			long typeid = emailPeriodicalManager.updateemailpublish(title,url,id);
			if (typeid > 0)
			{
				result = "Y";
			}
			else
			{
				result = "修改失败";
			}
		}
		return SUCCESS;
	}

	
	public IEmailPeriodicalManager getEmailPeriodicalManager() {
		return emailPeriodicalManager;
	}
	public void setEmailPeriodicalManager(
			IEmailPeriodicalManager emailPeriodicalManager) {
		this.emailPeriodicalManager = emailPeriodicalManager;
	}
	public EmailPeriodicalModel getModel() {
		return model;
	}
	public void setModel(EmailPeriodicalModel model) {
		this.model = model;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public EmailPeriodicalDto getDto() {
		return dto;
	}
	public void setDto(EmailPeriodicalDto dto) {
		this.dto = dto;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public void setPublish(String publish) {
		this.publish = publish;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getMid()
	{
		return mid;
	}
	public void setMid(long mid)
	{
		this.mid = mid;
	}
	
	
}
