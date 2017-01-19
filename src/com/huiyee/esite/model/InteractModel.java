package com.huiyee.esite.model;

public class InteractModel
{

	private long id;
	private String name;
	private boolean used;
	private String title;
	private long omid;// owner moduel
	private String status="REG";

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public boolean isUsed()
	{
		return used;
	}

	public void setUsed(boolean used)
	{
		this.used = used;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public long getOmid()
	{
		return omid;
	}

	public void setOmid(long omid)
	{
		this.omid = omid;
	}

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}

}
