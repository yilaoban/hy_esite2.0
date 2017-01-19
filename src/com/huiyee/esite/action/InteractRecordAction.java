package com.huiyee.esite.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.huiyee.esite.model.VisitUser;
import com.opensymphony.xwork2.ActionContext;


public class InteractRecordAction extends AbstractAction
{
	private long hdid;
	
	public long getHdid()
	{
		return hdid;
	}
	
	public void setHdid(long hdid)
	{
		this.hdid = hdid;
	}
	@Override
	public String execute() throws Exception
	{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		VisitUser vu = (VisitUser) ActionContext.getContext().getSession().get("visitUser");
		String json=pageCompose.findInteractRecord(vu,hdid);
		out.print(json);
		out.flush();
		out.close();
		return null;
	}
}
