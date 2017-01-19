
package com.huiyee.interact.offcheck.model;

import java.util.Date;

public class OffCheckDzWay
{

	private long id;
	private long csid;
	private long cwxuid;
	private long swxuid;
	private int status;
	private Date createtime;
	private Date endtime;
	private Date stime;
    private int rmb;
    private int jf;
	
	public long getId()
	{
		return id;
	}
	
	public long getCsid()
	{
		return csid;
	}
	
	public long getCwxuid()
	{
		return cwxuid;
	}
	
	public long getSwxuid()
	{
		return swxuid;
	}
	
	public int getStatus()
	{
		return status;
	}
	
	public Date getCreatetime()
	{
		return createtime;
	}
	
	public Date getEndtime()
	{
		return endtime;
	}
	
	public Date getStime()
	{
		return stime;
	}
	
	public int getRmb()
	{
		return rmb;
	}
	
	public int getJf()
	{
		return jf;
	}
	
	public void setId(long id)
	{
		this.id = id;
	}
	
	public void setCsid(long csid)
	{
		this.csid = csid;
	}
	
	public void setCwxuid(long cwxuid)
	{
		this.cwxuid = cwxuid;
	}
	
	public void setSwxuid(long swxuid)
	{
		this.swxuid = swxuid;
	}
	
	public void setStatus(int status)
	{
		this.status = status;
	}
	
	public void setCreatetime(Date createtime)
	{
		this.createtime = createtime;
	}
	
	public void setEndtime(Date endtime)
	{
		this.endtime = endtime;
	}
	
	public void setStime(Date stime)
	{
		this.stime = stime;
	}
	
	public void setRmb(int rmb)
	{
		this.rmb = rmb;
	}
	
	public void setJf(int jf)
	{
		this.jf = jf;
	}
}
