package com.huiyee.esite.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.huiyee.esite.dto.SiteIndexActionDto;

public class DelActivityAction extends AbstractAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1810553998145362917L;
	private long activityid;
	private SiteIndexActionDto dto;
	private String result;
	@Override
	public String execute() throws Exception {
		result = pageCompose.composeDelActivity(activityid); 
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(result);
		out.flush();
		out.close();
		return null;
	}
	
	public long getActivityid() {
		return activityid;
	}

	public void setActivityid(long activityid) {
		this.activityid = activityid;
	}

	public SiteIndexActionDto getDto() {
		return dto;
	}
	public void setDto(SiteIndexActionDto dto) {
		this.dto = dto;
	}

	public String getResult() {
		return result;
	}
	
}
