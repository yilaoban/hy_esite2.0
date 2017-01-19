package com.huiyee.esite.action;

import com.opensymphony.xwork2.Action;

public class OauthForLinkAction extends AbstractAction
{
	/**
	 * 
	 */
	private long appid = 1;// 申请的app在本地数据库的id
	private static final long serialVersionUID = 1L;
	private String sinaOauthUrl;
	private long entityid;
	private String oldurl;
	private long siteid;

	public void setSiteid(long siteid)
	{
		this.siteid = siteid;
	}

	public void setOldurl(String oldurl)
	{
		this.oldurl = oldurl;
	}

	@Override
	public String execute() throws Exception
	{
		if (siteid > 0)
		{
			appid = this.pageCompose.findAppidBySiteid(siteid);
			entityid=siteid;
		}
		String state = entityid + "," + appid + "," + oldurl;
		sinaOauthUrl = this.pageCompose.findOauthLink(state, appid);
		return Action.SUCCESS;
	}

	public void setAppid(long appid)
	{
		this.appid = appid;
	}

	public String getSinaOauthUrl()
	{
		return sinaOauthUrl;
	}

	public void setEntityid(long entityid)
	{
		this.entityid = entityid;
	}
}
