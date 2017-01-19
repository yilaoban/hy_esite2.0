package com.huiyee.bdmap.dto;

public class BDLBSRs
{
	private String status;//必须。0代表成功，其它取值含义另行说明
	private long id;
	private String message;
	public String getStatus()
	{
		return status;
	}
	public void setStatus(String status)
	{
		this.status = status;
	}
	public long getId()
	{
		return id;
	}
	public void setId(long id)
	{
		this.id = id;
	}
	public String getMessage()
	{
		return message;
	}
	public void setMessage(String message)
	{
		this.message = message;
	}
}
