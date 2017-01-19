package com.huiyee.esite.model;

import java.io.Serializable;

public class WeiBoRsDto implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3368206254437715221L;
	private String status = "ERR";// ERR ´íÎó CMP³É¹¦
	private String desc;
	private Object obj;

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}

	public String getDesc()
	{
		return desc;
	}

	public void setDesc(String desc)
	{
		this.desc = desc;
	}

	public Object getObj()
	{
		return obj;
	}

	public void setObj(Object obj)
	{
		this.obj = obj;
	}
}
