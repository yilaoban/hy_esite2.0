package com.huiyee.interact.cs.dto;

import java.io.Serializable;

public class RsCsDto implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int status;// 1:参与成功,-1:visituser不存在
	private long rid;//记录的id
	private String hydesc;

	public int getStatus()
	{
		return status;
	}

	public void setStatus(int status)
	{
		this.status = status;
	}


	public String getHydesc()
	{
		return hydesc;
	}

	public void setHydesc(String hydesc)
	{
		this.hydesc = hydesc;
	}

	public long getRid()
	{
		return rid;
	}

	public void setRid(long rid)
	{
		this.rid = rid;
	}
}
