package com.huiyee.esite.model;

import java.io.Serializable;
import java.util.Date;


public class WxPP implements Serializable
{
	private long id;
	private long wxpid;
	private long pageid;
	private Date createtime;
	
	public long getId()
	{
		return id;
	}
	
	public void setId(long id)
	{
		this.id = id;
	}
	
	public long getWxpid()
	{
		return wxpid;
	}
	
	public void setWxpid(long wxpid)
	{
		this.wxpid = wxpid;
	}
	
	public long getPageid()
	{
		return pageid;
	}
	
	public void setPageid(long pageid)
	{
		this.pageid = pageid;
	}
	
	public Date getCreatetime()
	{
		return createtime;
	}
	
	public void setCreatetime(Date createtime)
	{
		this.createtime = createtime;
	}
	
	
}
