package com.huiyee.interact.cb.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.huiyee.esite.model.VisitUser;
import com.huiyee.interact.cb.dto.CbActivityDto;
import com.huiyee.interact.cb.mgr.ICbActivityJlMgr;
import com.huiyee.interact.cb.model.CbSender;
import com.opensymphony.xwork2.ActionContext;


public class CbActivityJlRecordAction extends AbstractCbUserAction
{
	private static final long serialVersionUID = 7724301210084841252L;
	private CbActivityDto dto;
	private ICbActivityJlMgr cbActivityJlMgr;
	private int pageId = 1;
	
	public void setCbActivityJlMgr(ICbActivityJlMgr cbActivityJlMgr)
	{
		this.cbActivityJlMgr = cbActivityJlMgr;
	}

	public String findCbActivityJlRecordList() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setHeader("cache-control", "no-cache");
		response.setContentType("application/json; charset=utf-8");
		VisitUser vu = (VisitUser) ActionContext.getContext().getSession().get("visitUser");
//		if(vu != null){
//			CbSender cs = (CbSender) vu.getAppUsers().get("cb");
//			if(cs != null){
//				dto = cbActivityJlMgr.findCbActivityJlRecordList(cs.getId(),pageId);
//				dto.setStatus(1);
//			}
//		}
		if(dto == null){
			dto = new CbActivityDto();
		}
		Gson gson = new Gson();
		PrintWriter pw = response.getWriter();
		pw.print(gson.toJson(dto));
		pw.flush();
		pw.close();
		return null;
	}

	public int getPageId()
	{
		return pageId;
	}
	
	public void setPageId(int pageId)
	{
		this.pageId = pageId;
	}


	public CbActivityDto getDto()
	{
		return dto;
	}

	public void setDto(CbActivityDto dto)
	{
		this.dto = dto;
	}
	
	
}
