package com.huiyee.interact.renqi.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.huiyee.esite.action.InteractModelAction;
import com.huiyee.esite.model.VisitUser;
import com.huiyee.esite.util.ClientUserIp;
import com.huiyee.esite.util.HttpRequestDeviceUtils;
import com.huiyee.interact.renqi.dto.RsRqDto;
import com.huiyee.interact.renqi.mgr.IRenQiMgr;
import com.huiyee.interact.renqi.model.RenQi;
import com.huiyee.interact.renqi.service.IRenQiDrawService;
import com.opensymphony.xwork2.ActionContext;

public class RenQiDrawAction extends InteractModelAction
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long rqid;
	private String content;
	private IRenQiMgr renQiMgr;
	private IRenQiDrawService renQiDrawService;

	public String execute() throws Exception
	{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		RenQi rq = renQiMgr.findRenQiById(rqid);
		HttpServletRequest request = ServletActionContext.getRequest();
		String ip = ClientUserIp.getIpAddr(request);
		String userA = request.getHeader("user-agent");
		String userAgent = HttpRequestDeviceUtils.getMediaDevice(userA);
		VisitUser vu = (VisitUser) ActionContext.getContext().getSession().get("visitUser");
		RsRqDto rs=  renQiDrawService.bidOneRenQi(vu, rq, ip, userAgent, vu.getSource());
		Gson gson = new Gson();
		out.print(gson.toJson(rs));
		out.flush();
		out.close();
		return null;
	}

	public String share() throws Exception
	{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		HttpServletRequest request = ServletActionContext.getRequest();
		String ip = ClientUserIp.getIpAddr(request);
		String userA = request.getHeader("user-agent");
		String userAgent = HttpRequestDeviceUtils.getMediaDevice(userA);
		VisitUser vu = (VisitUser) ActionContext.getContext().getSession().get("visitUser");
		renQiDrawService.fuidShare(vu, rqid, content, ip, userAgent, vu.getSource());
		out.print(1);
		out.flush();
		out.close();
		return null;
	}

	public void setRenQiMgr(IRenQiMgr renQiMgr)
	{
		this.renQiMgr = renQiMgr;
	}

	public void setContent(String content)
	{
		this.content = content;
	}

	public void setRenQiDrawService(IRenQiDrawService renQiDrawService)
	{
		this.renQiDrawService = renQiDrawService;
	}

	public void setRqid(long rqid) {
		this.rqid = rqid;
	}
}
