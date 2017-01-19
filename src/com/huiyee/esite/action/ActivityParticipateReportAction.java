package com.huiyee.esite.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.huiyee.esite.dto.SiteVistLogDto;
import com.huiyee.esite.model.Account;
import com.opensymphony.xwork2.ActionContext;

public class ActivityParticipateReportAction extends AbstractAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6426432875391961780L;
	private long sitegroupid;
	private SiteVistLogDto dto;
	
	private long activityid;
	@Override
	public String execute() throws Exception {
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		long ownerid = account.getOwner().getId();
		dto = (SiteVistLogDto) pageCompose.activityParticipateDataAll(sitegroupid,activityid,ownerid);
		return SUCCESS;
	}
	
	public String getActivityParticipateData() throws Exception {
		 HttpServletResponse response = ServletActionContext.getResponse();
	        response.setHeader("pragma", "no-cache");
	        response.setHeader("cache-control", "no-cache");
	        response.setContentType("text/html; charset=utf-8");
	        dto = (SiteVistLogDto) pageCompose.activityParticipateData(sitegroupid,activityid);
	        Gson gson = new Gson();
	        String gsonComments = gson.toJson(dto);
	        PrintWriter pw = response.getWriter();
	        pw.print(gsonComments);
	        pw.flush();
	        pw.close();
	        return null;
	}
	
	public String getActivityParticipateSourceData() throws Exception {
		 HttpServletResponse response = ServletActionContext.getResponse();
	        response.setHeader("pragma", "no-cache");
	        response.setHeader("cache-control", "no-cache");
	        response.setContentType("text/html; charset=utf-8");
	        dto = (SiteVistLogDto) pageCompose.activityParticipateSourceData(sitegroupid,activityid);
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
