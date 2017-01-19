package com.huiyee.esite.model;


public class WxOA
{
	private long id;
	private long expires_in;
	private String access_token;
	private String refresh_token;
	private String third_appid;
	private long third_expires_in;
	private String third_access_token;//第三方的access_token
	
	public long getThird_expires_in()
	{
		return third_expires_in;
	}
	
	public void setThird_expires_in(long third_expires_in)
	{
		this.third_expires_in = third_expires_in;
	}

	public long getId()
	{
		return id;
	}
	
	public void setId(long id)
	{
		this.id = id;
	}


	public String getThird_access_token()
	{
		return third_access_token;
	}


	
	public void setThird_access_token(String third_access_token)
	{
		this.third_access_token = third_access_token;
	}


	public String getRefresh_token()
	{
		return refresh_token;
	}

	
	public void setRefresh_token(String refresh_token)
	{
		this.refresh_token = refresh_token;
	}


	public String getThird_appid()
	{
		return third_appid;
	}

	
	public void setThird_appid(String third_appid)
	{
		this.third_appid = third_appid;
	}

	public String getAccess_token()
	{
		return access_token;
	}
	
	public long getExpires_in()
	{
		return expires_in;
	}
	
	public void setAccess_token(String access_token)
	{
		this.access_token = access_token;
	}
	
	public void setExpires_in(long expires_in)
	{
		this.expires_in = expires_in;
	}
}
