package com.huiyee.esite.model;

public class HD149Model
{
	private long id;
	private long pageid;
	private long categoryid;
	private String type;
	private long topage;

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

	public long getCategoryid()
	{
		return categoryid;
	}

	public void setCategoryid(long categoryid)
	{
		this.categoryid = categoryid;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public long getTopage()
	{
		return topage;
	}

	public void setTopage(long topage)
	{
		this.topage = topage;
	}
}
