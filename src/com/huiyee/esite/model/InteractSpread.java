package com.huiyee.esite.model;

import java.io.Serializable;
import java.util.Date;

public class InteractSpread implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2762414309196312681L;
	private long id;
	private long fid;
	private long pageid;
	private Date createtime;
	private long spreadid;
	private String title;
	private String type;
	private long omid;
	
	public long getOmid() {
		return omid;
	}

	public void setOmid(long omid) {
		this.omid = omid;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public long getSpreadid()
	{
		return spreadid;
	}

	public void setSpreadid(long spreadid)
	{
		this.spreadid = spreadid;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public long getFid()
	{
		return fid;
	}

	public void setFid(long fid)
	{
		this.fid = fid;
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
