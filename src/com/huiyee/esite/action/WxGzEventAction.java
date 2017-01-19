
package com.huiyee.esite.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.huiyee.esite.mgr.IGzEventMgr;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.model.WxGzEvent;
import com.huiyee.interact.xc.model.KeywordMsg;
import com.opensymphony.xwork2.ActionContext;

public class WxGzEventAction extends AbstractAction
{

	private IGzEventMgr gzEventMgr;
	private WxGzEvent gzEvent;
	private String furl;
	private KeywordMsg keyMsg;
	private long id;

	// 微现场配置 仅关注者可签到时,引导用户关注以及关注后消息显示
	public String xcCrmMsgShow() throws Exception
	{
		gzEvent = gzEventMgr.findGzEvent(id);
		return SUCCESS;
	}

	// 微现场配置 仅关注者可签到时,引导用户关注以及关注后消息设置
	public String xcCrmMsgConfig() throws Exception
	{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		long rs = gzEventMgr.updateMsg(id, furl, keyMsg, account.getOwner().getId());
		out.write(String.valueOf(rs));
		out.flush();
		out.close();
		return null;
	}

	public void setGzEventMgr(IGzEventMgr gzEventMgr)
	{
		this.gzEventMgr = gzEventMgr;
	}

	public WxGzEvent getGzEvent()
	{
		return gzEvent;
	}

	public void setGzEvent(WxGzEvent gzEvent)
	{
		this.gzEvent = gzEvent;
	}

	public String getFurl()
	{
		return furl;
	}

	public void setFurl(String furl)
	{
		this.furl = furl;
	}

	public KeywordMsg getKeyMsg()
	{
		return keyMsg;
	}

	public void setKeyMsg(KeywordMsg keyMsg)
	{
		this.keyMsg = keyMsg;
	}

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}
}
