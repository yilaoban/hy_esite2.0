package com.huiyee.esite.action;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;

import com.huiyee.esite.util.CacheUtil;
import com.huiyee.esite.util.ClientUserIp;
import com.huiyee.esite.util.HyConfig;

public class Cache4WxTokenAction extends AbstractAction
{
	/**
	 * 用来同步缓存的接口
	 */
	private static final long serialVersionUID = 1L;

	private String key;
	private int type;// 1-ticket;2-comm_token

	@Override
	public String execute() throws Exception
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter pw;
		String ip = ClientUserIp.getIpAddr(request);
		if (HyConfig.getAllowClearIp().contains(ip))
		{
			if (type == 1)
			{
             
			}
			else if (type == 2)
			{

			}
			try
			{
				pw = response.getWriter();
				pw.print("OK");
				pw.flush();
				pw.close();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		else
		{
			try
			{
				pw = response.getWriter();
				pw.print("not allow ip");
				pw.flush();
				pw.close();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}

		return null;
	}

	public void setType(int type)
	{
		this.type = type;
	}

	public void setKey(String key)
	{
		this.key = key;
	}

}
