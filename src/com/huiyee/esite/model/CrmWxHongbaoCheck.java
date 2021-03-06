package com.huiyee.esite.model;

import java.io.Serializable;


public class CrmWxHongbaoCheck implements Serializable
{
	private static final long serialVersionUID = -6306144207816465379L;
	private long id;
	private long mpid;
	private int total;
	private int used;
	private int status;
	private int updatestatus;
	private int type;
	private long enid;
	private String reason;
	public long getId()
	{
		return id;
	}
	
	public void setId(long id)
	{
		this.id = id;
	}
	
	public long getMpid()
	{
		return mpid;
	}

	
	public void setMpid(long mpid)
	{
		this.mpid = mpid;
	}
	
	public int getTotal()
	{
		return total;
	}
	
	public void setTotal(int total)
	{
		this.total = total;
	}
	
	public int getUsed()
	{
		return used;
	}
	
	public void setUsed(int used)
	{
		this.used = used;
	}
	
	public int getStatus()
	{
		return status;
	}
	
	public void setStatus(int status)
	{
		this.status = status;
	}
	
	public int getUpdatestatus()
	{
		return updatestatus;
	}
	
	public void setUpdatestatus(int updatestatus)
	{
		this.updatestatus = updatestatus;
	}
	
	public int getType()
	{
		return type;
	}
	
	public void setType(int type)
	{
		this.type = type;
	}
	
	public long getEnid()
	{
		return enid;
	}
	
	public void setEnid(long enid)
	{
		this.enid = enid;
	}

	
	public String getReason()
	{
		return reason;
	}

	public void setReason(String reason)
	{
		this.reason = reason;
	}
	
}
