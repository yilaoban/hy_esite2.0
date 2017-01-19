package com.huiyee.pay.model;


public class AliPay {
	
	private long id;
	private long ownerid;
	private String alipartner;
	private String aliseller_email;
	private String alikey;
	
	private String type;
	private String appid;
	private String appsecret;
	private String mchid;
	private String key;
	
	public String getType()
	{
		return type;
	}

	
	public void setType(String type)
	{
		this.type = type;
	}

	
	public String getAppid()
	{
		return appid;
	}

	
	public void setAppid(String appid)
	{
		this.appid = appid;
	}

	
	public String getAppsecret()
	{
		return appsecret;
	}

	
	public void setAppsecret(String appsecret)
	{
		this.appsecret = appsecret;
	}

	
	public String getMchid()
	{
		return mchid;
	}

	
	public void setMchid(String mchid)
	{
		this.mchid = mchid;
	}

	
	public String getKey()
	{
		return key;
	}

	
	public void setKey(String key)
	{
		this.key = key;
	}

	public long getId()
	{
		return id;
	}
	
	public void setId(long id)
	{
		this.id = id;
	}
	
	public long getOwnerid()
	{
		return ownerid;
	}
	
	public void setOwnerid(long ownerid)
	{
		this.ownerid = ownerid;
	}
	
	public String getAlipartner()
	{
		return alipartner;
	}
	
	public void setAlipartner(String alipartner)
	{
		this.alipartner = alipartner;
	}
	
	public String getAliseller_email()
	{
		return aliseller_email;
	}
	
	public void setAliseller_email(String aliseller_email)
	{
		this.aliseller_email = aliseller_email;
	}
	
	public String getAlikey()
	{
		return alikey;
	}
	
	public void setAlikey(String alikey)
	{
		this.alikey = alikey;
	}
	
	
}
