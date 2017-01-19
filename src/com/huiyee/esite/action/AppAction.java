
package com.huiyee.esite.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.huiyee.esite.model.Account;

public class AppAction extends AbstractAction
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7812343830699959375L;
	private long app_id;
	
	public long getApp_id()
	{
		return app_id;
	}

	
	public void setApp_id(long app_id)
	{
		this.app_id = app_id;
	}

	@Override
	public String execute() throws Exception
	{
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		int result = pageCompose.freeOpenApp(app_id, account.getOwner().getId());
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(result);
		out.flush();
		out.close();
		return null;
	}

}
