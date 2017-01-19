package com.huiyee.esite.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.huiyee.esite.dto.SiteVistLogDto;
import com.huiyee.esite.model.Account;
import com.opensymphony.xwork2.ActionContext;

public class ActivityVisitReportAction extends AbstractAction {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2928636295885342492L;
	private long siteid;
	private SiteVistLogDto dto;
	private long activityid;
	private int pageId = 1;
	@Override
	public String execute() throws Exception {
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		long ownerid = account.getOwner().getId();
		dto = (SiteVistLogDto) pageCompose.activityReportDataAll(siteid,activityid,ownerid);
		if(activityid == 0 && dto.getActivityList() != null && dto.getActivityList().size() > 0 ){
			activityid= dto.getActivityList().get(0).getId();			
		}
		return SUCCESS;
	}
	
	 public String getActivityData() throws Exception {
	        HttpServletResponse response = ServletActionContext.getResponse();
	        response.setHeader("pragma", "no-cache");
	        response.setHeader("cache-control", "no-cache");
	        response.setContentType("text/html; charset=utf-8");
	        dto = (SiteVistLogDto) pageCompose.activityReportData(siteid,activityid);
	        Gson gson = new Gson();
	        String gsonComments = gson.toJson(dto);
	        PrintWriter pw = response.getWriter();
	        pw.print(gsonComments);
	        pw.flush();
	        pw.close();
	        return null;
	    }
	 
	 public String getVisitTermianlCount() throws Exception {
		 	HttpServletResponse response = ServletActionContext.getResponse();
	        response.setHeader("pragma", "no-cache");
	        response.setHeader("cache-control", "no-cache");
	        response.setContentType("text/html; charset=utf-8");
	        dto = (SiteVistLogDto) pageCompose.visitTerminalCount(siteid,activityid);
	        Gson gson = new Gson();
	        String gsonComments = gson.toJson(dto);
	        PrintWriter pw = response.getWriter();
	        pw.print(gsonComments);
	        pw.flush();
	        pw.close();
	        return null;
	 }
	 
	public String getVisitSourceCount() throws Exception {
			HttpServletResponse response = ServletActionContext.getResponse();
	        response.setHeader("pragma", "no-cache");
	        response.setHeader("cache-control", "no-cache");
	        response.setContentType("text/html; charset=utf-8");
	        dto = (SiteVistLogDto) pageCompose.visitSourceCount(siteid,activityid);
	        Gson gson = new Gson();
	        String gsonComments = gson.toJson(dto);
	        PrintWriter pw = response.getWriter();
	        pw.print(gsonComments);
	        pw.flush();
	        pw.close();
	        return null;
	}
	
	
	public int getPageId() {
		return pageId;
	}

	public void setPageId(int pageId) {
		this.pageId = pageId;
	}

	public String getAnonVisitData() throws Exception {
		dto = (SiteVistLogDto) pageCompose.getAnonVisitData(siteid,pageId,activityid);
		return SUCCESS;
	}
	
	public long getActivityid() {
		return activityid;
	}

	public void setActivityid(long activityid) {
		this.activityid = activityid;
	}



	public long getSiteid()
	{
		return siteid;
	}

	public void setSiteid(long siteid)
	{
		this.siteid = siteid;
	}

	public SiteVistLogDto getDto() {
		return dto;
	}

	public void setDto(SiteVistLogDto dto) {
		this.dto = dto;
	}
}
