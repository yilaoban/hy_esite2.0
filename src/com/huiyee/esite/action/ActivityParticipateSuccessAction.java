package com.huiyee.esite.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.huiyee.esite.dto.SiteVistLogDto;
import com.huiyee.esite.model.Account;
import com.opensymphony.xwork2.ActionContext;

public class ActivityParticipateSuccessAction extends AbstractAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6963688144379062409L;
	private long sitegroupid;
	private SiteVistLogDto dto;
	private long activityid;
	@Override
	public String execute() throws Exception {
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		long ownerid = account.getOwner().getId();
		dto = (SiteVistLogDto) pageCompose.activityParticipateSuccessDataAll(sitegroupid,activityid,ownerid);
		return SUCCESS;
	}
	
	public String getActivityParticipateSuccessData() throws Exception {
		 HttpServletResponse response = ServletActionContext.getResponse();
	        response.setHeader("pragma", "no-cache");
	        response.setHeader("cache-control", "no-cache");
	        response.setContentType("text/html; charset=utf-8");
	        dto = (SiteVistLogDto) pageCompose.activityParticipateSuccessData(sitegroupid,activityid);
	        Gson gson = new Gson();
	        String gsonComments = gson.toJson(dto);
	        PrintWriter pw = response.getWriter();
	        pw.print(gsonComments);
	        pw.flush();
	        pw.close();
	        return null;
	}
	
	public String getActivityParticipateSourceSuccessData() throws Exception {
		 HttpServletResponse response = ServletActionContext.getResponse();
	        response.setHeader("pragma", "no-cache");
	        response.setHeader("cache-control", "no-cache");
	        response.setContentType("text/html; charset=utf-8");
	        dto = (SiteVistLogDto) pageCompose.activityParticipateSourceSuccessData(sitegroupid,activityid);
	        Gson gson = new Gson();
	        String gsonComments = gson.toJson(dto);
	        PrintWriter pw = response.getWriter();
	        pw.print(gsonComments);
	        pw.flush();
	        pw.close();
	        return null;
	}
	
	public String getActivityParticipateFailData() throws Exception {
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		long ownerid = account.getOwner().getId();
		dto = (SiteVistLogDto) pageCompose.activityParticipateSourceFailDataAll(sitegroupid,activityid,ownerid);
	        return SUCCESS;
	}
	
	public String getActivityParticipateFail() throws Exception {
		 HttpServletResponse response = ServletActionContext.getResponse();
	        response.setHeader("pragma", "no-cache");
	        response.setHeader("cache-control", "no-cache");
	        response.setContentType("text/html; charset=utf-8");
	        dto = (SiteVistLogDto) pageCompose.activityParticipateSourceFailData(sitegroupid,activityid);
	        Gson gson = new Gson();
	        String gsonComments = gson.toJson(dto);
	        PrintWriter pw = response.getWriter();
	        pw.print(gsonComments);
	        pw.flush();
	        pw.close();
	        return null;
	}
	
	public String getActivityParticipateFailSource() throws Exception {
		 HttpServletResponse response = ServletActionContext.getResponse();
	        response.setHeader("pragma", "no-cache");
	        response.setHeader("cache-control", "no-cache");
	        response.setContentType("text/html; charset=utf-8");
	        dto = (SiteVistLogDto) pageCompose.activityParticipateSourceFailSource(sitegroupid,activityid);
	        Gson gson = new Gson();
	        String gsonComments = gson.toJson(dto);
	        PrintWriter pw = response.getWriter();
	        pw.print(gsonComments);
	        pw.flush();
	        pw.close();
	        return null;
	}
	
	public long getSitegroupid() {
		return sitegroupid;
	}

	public void setSitegroupid(long sitegroupid) {
		this.sitegroupid = sitegroupid;
	}

	public SiteVistLogDto getDto() {
		return dto;
	}

	public void setDto(SiteVistLogDto dto) {
		this.dto = dto;
	}

	public long getActivityid() {
		return activityid;
	}

	public void setActivityid(long activityid) {
		this.activityid = activityid;
	}
	
}
