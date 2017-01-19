
package com.huiyee.interact.exam.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.huiyee.esite.action.InteractModelAction;
import com.huiyee.esite.dto.HdRsDto;
import com.huiyee.esite.model.VisitUser;
import com.huiyee.esite.util.HyConfig;
import com.huiyee.interact.exam.dto.ExamRsDto;
import com.huiyee.interact.exam.dto.ExamSubDto;
import com.huiyee.interact.exam.mgr.IExamManager;
import com.huiyee.interact.exam.model.ExamModel;
import com.huiyee.esite.util.ClientUserIp;
import com.huiyee.interact.vote.util.HttpRequestDeviceUtils;

public class ExamSubmitAction extends InteractModelAction
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4190372881263117821L;
	private long pageid;
	private String source;
	private ExamSubDto dto;

	public void setDto(ExamSubDto dto)
	{
		this.dto = dto;
	}

	public ExamSubDto getDto()
	{
		return dto;
	}

	private long cid;
	private long relationid;
	private IExamManager examManager;
	private long mid = 10006;

	@Override
	public String execute() throws Exception
	{
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		ExamModel exam = examManager.findModelByIdAndPageId(dto.getExamid(), pageid);
		HdRsDto rs = pageCompose.ineractCanRun(exam);
		if(HyConfig.isRun()){
			rs.setStatus(-1);
		}
		if (rs.getStatus() == 10000)
		{
			String ip = ClientUserIp.getIpAddr(request);
			String userAgent = request.getHeader("user-agent");
			VisitUser u = (VisitUser) ServletActionContext.getRequest().getSession().getAttribute("visitUser");
			try
			{
				ExamRsDto exRs = examManager.saveExamReocrd(dto, pageid, u, ip, HttpRequestDeviceUtils.getMediaDevice(userAgent), relationid, exam, this.getOwner());
				pageCompose.canRunLog(exam.getId(), "c", request);
				out.print(new Gson().toJson(exRs));
				out.flush();
				out.close();
				return null;
			} catch (Exception e)
			{
				e.printStackTrace();
				rs.setHydesc("调研失败，严重问题！");
				rs.setStatus(-11000);
			}
			pageCompose.canRunLog(exam.getId(), "d", request);
		}
		out.print(new Gson().toJson(rs));
		out.flush();
		out.close();
		return null;
	}

	public void setExamManager(IExamManager examManager)
	{
		this.examManager = examManager;
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
