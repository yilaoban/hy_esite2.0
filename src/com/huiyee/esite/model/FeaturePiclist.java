package com.huiyee.esite.model;

import java.io.Serializable;

public class FeaturePiclist implements Serializable
{
	private static final long serialVersionUID = -3542618725231225370L;
	private long id;
	private long pageid;
	private String name;
	private long categoryid;
	public long getId()
	{
		return id;
	}
	public void setId(long id)
	{
		this.id = id;
	}
	public long getPageid()
	{
		return pageid;
	}
	public void setPageid(long pageid)
	{
		this.pageid = pageid;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public long getCategoryid()
	{
		return categoryid;
	}
	public void setCategoryid(long categoryid)
	{
		this.categoryid = categoryid;
	}
	
	
}
