package com.huiyee.esite.model;

public class PageTemplate
{

	private long id;
	private long pageid;
	private String name;
	private String img;
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
	public String getImg()
	{
		return img;
	}
	public void setImg(String img)
	{
		this.img = img;
	}
}
