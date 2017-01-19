package com.huiyee.esite.action;

import com.opensymphony.xwork2.Action;

public class OauthAction extends AbstractAction
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String code;
	private String refUrl;
	private String state;

	public void setState(String state)
	{
		this.state = state;
	}

	@Override
	public String execute() throws Exception
	{
		String[] strs = state.split(",");
		long entityid = Long.valueOf(strs[0]);
		long appid = Long.valueOf(strs[1]);
		String oldurl = strs[2];
		refUrl = this.pageCompose.OauthByCode(appid, code,entityid);
		if (!oldurl.trim().equals("null"))
		{
			refUrl = oldurl;
		}
		return Action.SUCCESS;
	}

	public void setCode(String code)
	{
		this.code = code;
	}

	public String getRefUrl()
	{
		return refUrl;
	}
}
