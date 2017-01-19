package com.huiyee.esite.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;

public class FeatureFriendsShipsAction extends AbstractAction
{
	private long pageid;
	private long wbuid;
	private long cid;

	@Override
	public String execute() throws Exception
	{
		String result;
		if (pageid == 0)
		{
			result = "缺少参数pageid";
		}
		else if (wbuid == 0)
		{
			result = "缺少参数wbuid";
		}
		else if (cid == 0)
		{
			result = "缺少参数cid";
		}
		else
		{
			result = pageCompose.createFriendsShips(pageid, wbuid, cid);
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter pw;
		try
		{
			pw = response.getWriter();
			pw.print(new Gson().toJson(result));
			pw.flush();
			pw.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	public long getPageid()
	{
		return pageid;
	}

	public void setPageid(long pageid)
	{
		this.pageid = pageid;
	}

	public long getWbuid()
	{
		return wbuid;
	}

	public void setWbuid(long wbuid)
	{
		this.wbuid = wbuid;
	}

	public long getCid()
	{
		return cid;
	}

	public void setCid(long cid)
	{
		this.cid = cid;
	}
}
