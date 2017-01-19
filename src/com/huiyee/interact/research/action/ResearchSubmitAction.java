package com.huiyee.interact.research.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.huiyee.esite.action.InteractModelAction;
import com.huiyee.esite.dto.HdRsDto;
import com.huiyee.esite.model.VisitUser;
import com.huiyee.interact.research.dto.ResearchSubDto;
import com.huiyee.interact.research.mgr.IResearchManager;
import com.huiyee.interact.research.model.ResearchModel;
import com.huiyee.esite.util.ClientUserIp;
import com.huiyee.interact.vote.util.HttpRequestDeviceUtils;

public class ResearchSubmitAction extends InteractModelAction
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4190372881263117821L;
	private long pageid;
	private String source;
	private ResearchSubDto dto;
	
	public void setDto(ResearchSubDto dto)
	{
		this.dto = dto;
	}

	public ResearchSubDto getDto()
	{
		return dto;
	}

	private long cid;
	private long relationid;
	private IResearchManager researchManager;
	private long mid = 10006;

	@Override
	public String execute() throws Exception
	{
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		ResearchModel research=researchManager.findModelByIdAndPageId(dto.getResearchid(),pageid);
		HdRsDto rs = pageCompose.ineractCanRun(research);
		if (rs.getStatus()==10000)
		{
			String ip = ClientUserIp.getIpAddr(request);
			String userAgent = request.getHeader("user-agent");
			VisitUser u = (VisitUser) ServletActionContext.getRequest().getSession().getAttribute("visitUser");
			try
			{
				rs =  researchManager.saveResearchReocrd(dto,pageid, u, ip, HttpRequestDeviceUtils.getMediaDevice(userAgent), relationid,research);
			}
			catch (Exception e)
			{
				e.printStackTrace();
				rs.setHydesc("调研失败，严重问题！");
				rs.setStatus(-11000);
			}
			pageCompose.canRunLog(research.getId(), "d", request);
		}
		out.print(new Gson().toJson(rs));
		out.flush();
		out.close();
		return null;
	}

	public void setResearchManager(IResearchManager researchManager)
	{
		this.researchManager = researchManager;
	}

	public long getPageid()
	{
		return pageid;
	}

	public void setPageid(long pageid)
	{
		this.pageid = pageid;
	}

	public String getSource()
	{
		return source;
	}

	public void setSource(String source)
	{
		this.source = source;
	}

	public long getCid()
	{
		return cid;
	}

	public void setCid(long cid)
	{
		this.cid = cid;
	}

	public long getMid()
	{
		return mid;
	}

	public void setMid(long mid)
	{
		this.mid = mid;
	}

	public long getRelationid()
	{
		return relationid;
	}

	public void setRelationid(long relationid)
	{
		this.relationid = relationid;
	}

}
