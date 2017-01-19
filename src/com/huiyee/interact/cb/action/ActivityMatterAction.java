package com.huiyee.interact.cb.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.huiyee.esite.model.VisitUser;
import com.huiyee.esite.util.HyConfig;
import com.huiyee.interact.cb.dto.ActivityMatterDto;
import com.huiyee.interact.cb.mgr.IActivityMatterMgr;
import com.opensymphony.xwork2.ActionContext;


public class ActivityMatterAction extends AbstractCbUserAction
{
	private static final long serialVersionUID = 1501362383880965580L;
	private ActivityMatterDto dto;
	private IActivityMatterMgr activityMatterMgr;
	private int pageId = 1;
	private long id;
	private long pageid;
	private long aid;
	
	public void setActivityMatterMgr(IActivityMatterMgr activityMatterMgr)
	{
		this.activityMatterMgr = activityMatterMgr;
	}
	
	public String findActivityMatterList() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setHeader("cache-control", "no-cache");
		response.setContentType("application/json; charset=utf-8");
		dto = activityMatterMgr.findActivityMatterList(this.getOwner(),pageId);
		dto.setStatus(1);
		Gson gson = new Gson();
		PrintWriter pw = response.getWriter();
		pw.print(gson.toJson(dto));
		pw.flush();
		pw.close();
		return null;
	}
	
	public String findActivityMatterChildList() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setHeader("cache-control", "no-cache");
		response.setContentType("application/json; charset=utf-8");
		dto = activityMatterMgr.findActivityMatterChildList(this.getOwner(),aid,pageid);
		dto.setAid(aid);
		dto.setStatus(1);
		Gson gson = new Gson();
		PrintWriter pw = response.getWriter();
		pw.print(gson.toJson(dto));
		pw.flush();
		pw.close();
		return null;
	}
	
	
	public String findActivityMatterDetail() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setHeader("cache-control", "no-cache");
		response.setContentType("application/json; charset=utf-8");
		VisitUser vu = (VisitUser) ActionContext.getContext().getSession().get("visitUser");
		if(vu != null&&!HyConfig.isRun()){
			dto = activityMatterMgr.findActivityMatterDetail(this.getOwner(),id,aid,vu.getWxUser().getId());
			dto.setStatus(1);
		}else{
			dto = new ActivityMatterDto();
		}
		Gson gson = new Gson();
		PrintWriter pw = response.getWriter();
		pw.print(gson.toJson(dto));
		pw.flush();
		pw.close();
		return null;
	}
	
	public ActivityMatterDto getDto()
	{
		return dto;
	}
	
	public void setDto(ActivityMatterDto dto)
	{
		this.dto = dto;
	}
	
	public int getPageId()
	{
		return pageId;
	}
	
	public void setPageId(int pageId)
	{
		this.pageId = pageId;
	}

	
	public long getId()
	{
		return id;
	}
	
	public void setId(long id)
	{
		this.id = id;
	}

	
	public long getPageid()
	{
		return pageid;
	}

	
	public void setPageid(long pageid)
	{
		this.pageid = pageid;
	}

	
	public long getAid()
	{
		return aid;
	}

	
	public void setAid(long aid)
	{
		this.aid = aid;
	}
	
}
