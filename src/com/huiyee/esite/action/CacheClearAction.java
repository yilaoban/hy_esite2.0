package com.huiyee.esite.action;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import com.huiyee.esite.util.CacheUtil;
import com.huiyee.esite.util.ClientUserIp;
import com.huiyee.esite.util.HyConfig;

public class CacheClearAction extends AbstractAction
{
	/**
	 * 用来清理缓存的接口
	 */
	private static final long serialVersionUID = 1L;

	private long key;
	private int type;// 1-pgs;2-psds;3-htmls

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
			//虽然会误清理，但是缓存清理没有事！
			CacheUtil.pgds.remove(key);
			CacheUtil.psds.clear();
			CacheUtil.htmls.remove(key);
			CacheUtil.wxmps.remove(key);
			CacheUtil.wxmpso.clear();
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

	public void setKey(long key)
	{
		this.key = key;
	}

	public void setType(int type)
	{
		this.type = type;
	}

}
