package com.huiyee.interact.cs.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.huiyee.esite.action.InteractModelAction;
import com.huiyee.esite.model.VisitUser;
import com.huiyee.esite.util.ClientUserIp;
import com.huiyee.esite.util.HttpRequestDeviceUtils;
import com.huiyee.interact.cs.dto.JContent;
import com.huiyee.interact.cs.dto.RsCsDto;
import com.huiyee.interact.cs.service.ICsDrawService;
import com.opensymphony.xwork2.ActionContext;

public class CsDrawAction extends InteractModelAction
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long csid;
	private JContent jcontent;
	private ICsDrawService csDrawService;

	public String execute() throws Exception
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
		Gson gson = new Gson();
		RsCsDto rs=csDrawService.fuidDraw(vu, csid, gson.toJson(jcontent), ip, userAgent);
		out.print(gson.toJson(rs));
		out.flush();
		out.close();
		return null;
	}
	
	public void setCsid(long csid)
	{
		this.csid = csid;
	}

	public void setCsDrawService(ICsDrawService csDrawService)
	{
		this.csDrawService = csDrawService;
	}

	public void setJcontent(JContent jcontent) {
		this.jcontent = jcontent;
	}

	public JContent getJcontent()
	{
		return jcontent;
	}

}
