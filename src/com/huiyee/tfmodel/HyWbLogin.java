package com.huiyee.tfmodel;

import java.io.Serializable;

public class HyWbLogin implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String accessToken;
	private long expireIn;
	private String refreshToken;
	private long wbuid;
	
	public HyWbLogin(){
		super();
	}
	public String getAccessToken()
	{
		return accessToken;
	}
	public void setAccessToken(String accessToken)
	{
		this.accessToken = accessToken;
	}
	public long getExpireIn()
	{
		return expireIn;
	}
	public void setExpireIn(long expireIn)
	{
		this.expireIn = expireIn;
	}
	public String getRefreshToken()
	{
		return refreshToken;
	}
	public void setRefreshToken(String refreshToken)
	{
		this.refreshToken = refreshToken;
	}
	public long getWbuid()
	{
		return wbuid;
	}
	public void setWbuid(long wbuid)
	{
		this.wbuid = wbuid;
	}

}
