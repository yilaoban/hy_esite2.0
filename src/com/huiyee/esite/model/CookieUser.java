package com.huiyee.esite.model;

import java.io.Serializable;

public class CookieUser implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2314387474427790192L;
	private long id;
	private String cookie;
	private long owner;

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public String getCookie()
	{
		return cookie;
	}

	public void setCookie(String cookie)
	{
		this.cookie = cookie;
	}

	public long getOwner()
	{
		return owner;
	}

	public void setOwner(long owner)
	{
		this.owner = owner;
	}
}
