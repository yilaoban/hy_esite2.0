package com.huiyee.interact.checkin.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.huiyee.esite.action.InteractModelAction;
import com.huiyee.esite.model.VisitUser;
import com.huiyee.esite.util.ClientUserIp;
import com.huiyee.esite.util.HttpRequestDeviceUtils;
import com.huiyee.interact.checkin.dto.CheckinRs;
import com.huiyee.interact.checkin.service.ICheckinDrawService;
import com.opensymphony.xwork2.ActionContext;

public class CheckinDrawAction extends InteractModelAction
{
	private long wbuid;
	private long ownerwbuid;
	private long pageid;
	private String source;
	private ICheckinDrawService checkinDrawService;
	private long mid=10008;
	/**
	 * 
	 */
	private static final long serialVersionUID = 3887688790629684079L;

	public String execute() throws Exception
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		String ip = ClientUserIp.getIpAddr(request);
		String userA = request.getHeader("user-agent");
		String userAgent = HttpRequestDeviceUtils.getMediaDevice(userA);
		VisitUser vu = (VisitUser) ActionContext.getContext().getSession().get("visitUser");
		CheckinRs rs = checkinDrawService.checkin(vu, pageid, ip, userAgent, source,this.getOwner());

		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		out.print(gson.toJson(rs));
		out.flush();
		out.close();
		return null;
	}

	public long getWbuid()
	{
		return wbuid;
	}

	public void setWbuid(long wbuid)
	{
		this.wbuid = wbuid;
	}

	public String getSource()
	{
		return source;
	}

	public void setSource(String source)
	{
		this.source = source;
	}

	public void setPageid(long pageid)
	{
		this.pageid = pageid;
	}

	public void setCheckinDrawService(ICheckinDrawService checkinDrawService)
	{
		this.checkinDrawService = checkinDrawService;
	}

	public long getOwnerwbuid()
	{
		return ownerwbuid;
	}

	public void setOwnerwbuid(long ownerwbuid)
	{
		this.ownerwbuid = ownerwbuid;
	}

	public long getPageid()
	{
		return pageid;
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
