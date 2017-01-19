package com.huiyee.esite.model;

import java.io.Serializable;

public class SiteCount implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7598553611415278485L;
	private long visteNum;
	private String type;
	private String updateTime;

	public long getVisteNum()
	{
		return visteNum;
	}

	public void setVisteNum(long visteNum)
	{
		this.visteNum = visteNum;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public String getUpdateTime()
	{
		return updateTime;
	}

	public void setUpdateTime(String updateTime)
	{
		this.updateTime = updateTime;
	}

}
