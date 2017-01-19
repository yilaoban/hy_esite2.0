package com.huiyee.interact.vote.action;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.huiyee.esite.action.InteractModelAction;
import com.huiyee.esite.dto.HdRsDto;
import com.huiyee.esite.model.VisitUser;
import com.huiyee.esite.util.ClientUserIp;
import com.huiyee.interact.vote.mgr.IVoteMgr;
import com.huiyee.interact.vote.model.InteractVote;
import com.huiyee.interact.vote.util.HttpRequestDeviceUtils;
import com.opensymphony.xwork2.ActionContext;

public class VoteSubmitAction extends InteractModelAction
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7142655512582382922L;
	private long voteid;
	private long pageid;
	private long cho;
	
	public void setCho(long cho)
	{
		this.cho = cho;
	}

	private String source;
	private long cid;
	private long mid = 10002;
	private long relationid;
	private IVoteMgr voteMgr;
	private long featureid = 123;
	@Override
	public String execute() throws Exception
	{
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		InteractVote vote=voteMgr.findVoteByIdAndPageid(voteid);
		HdRsDto rs = pageCompose.ineractCanRun(vote);
		if (rs.getStatus()==10000)
		{
			String ip = ClientUserIp.getIpAddr(request);
			String userAgent = request.getHeader("user-agent");
			try
			{
				VisitUser visit = (VisitUser) ActionContext.getContext().getSession().get("visitUser");
				if(visit.getWxUser()==null)
				{
					rs.setHydesc("投票失败，严重问题！");
					rs.setStatus(-11000);
					out.print(new Gson().toJson(rs));
					out.flush();
					out.close();
					return null;
				}
				
				rs = voteMgr.saveVoteRecord(visit, cho, ip, HttpRequestDeviceUtils.getMediaDevice(userAgent), pageid, relationid,vote);
			}
			catch (Exception e)
			{
				e.printStackTrace();
				rs.setHydesc("投票失败，严重问题！");
				rs.setStatus(-11000);
			}
			pageCompose.canRunLog(vote.getId(), "t", request);
		}
		out.print(new Gson().toJson(rs));
		out.flush();
		out.close();
		return null;
	}

	public void setVoteMgr(IVoteMgr voteMgr)
	{
		this.voteMgr = voteMgr;
	}

	public long getVoteid()
	{
		return voteid;
	}

	public void setVoteid(long voteid)
	{
		this.voteid = voteid;
	}

	public String getSource()
	{
		return source;
	}

	public void setSource(String source)
	{
		this.source = source;
	}

	public long getPageid()
	{
		return pageid;
	}

	public void setPageid(long pageid)
	{
		this.pageid = pageid;
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
