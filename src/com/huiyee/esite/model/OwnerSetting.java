package com.huiyee.esite.model;

import java.io.Serializable;

public class OwnerSetting implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long ownerid;
	private String odomain;
	private String wxappid;//微信应用的id，如果商家设置了用商家自己的微信应用
	private String wxsecret;//微信应用的秘钥

	public long getOwnerid()
	{
		return ownerid;
	}

	public void setOwnerid(long ownerid)
	{
		this.ownerid = ownerid;
	}

	public String getOdomain()
	{
		return odomain;
	}

	public void setOdomain(String odomain)
	{
		this.odomain = odomain;
	}

	public String getWxappid()
	{
		return wxappid;
	}

	public void setWxappid(String wxappid)
	{
		this.wxappid = wxappid;
	}

	public String getWxsecret()
	{
		return wxsecret;
	}

	public void setWxsecret(String wxsecret)
	{
		this.wxsecret = wxsecret;
	}
}
