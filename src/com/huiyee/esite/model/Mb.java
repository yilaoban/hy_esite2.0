package com.huiyee.esite.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


public class Mb implements Serializable
{
	private static final long serialVersionUID = 3014770465500968522L;
	private long id;
	private long ownerid;
	private String title;
	private String shortdesc;
	private String img;
	private String type;
	private String status;
	private Date createtime;
	private String link;
	private List<Long> pages;
	private List<String> tags;
	
	public List<Long> getPages()
	{
		return pages;
	}
	
	public void setPages(List<Long> pages)
	{
		this.pages = pages;
	}


	public String getLink()
	{
		return link;
	}

	
	public void setLink(String link)
	{
		this.link = link;
	}

	public long getId()
	{
		return id;
	}
	
	public void setId(long id)
	{
		this.id = id;
	}
	
	public String getTitle()
	{
		return title;
	}
	
	public void setTitle(String title)
	{
		this.title = title;
	}
	
	public String getImg()
	{
		return img;
	}
	
	public void setImg(String img)
	{
		this.img = img;
	}
	
	public String getType()
	{
		return type;
	}
	
	public void setType(String type)
	{
		this.type = type;
	}
	
	public String getStatus()
	{
		return status;
	}
	
	public void setStatus(String status)
	{
		this.status = status;
	}
	
	public Date getCreatetime()
	{
		return createtime;
	}
	
	public void setCreatetime(Date createtime)
	{
		this.createtime = createtime;
	}

	
	public List<String> getTags()
	{
		return tags;
	}

	
	public void setTags(List<String> tags)
	{
		this.tags = tags;
	}

	
	public String getShortdesc()
	{
		return shortdesc;
	}

	
	public void setShortdesc(String shortdesc)
	{
		this.shortdesc = shortdesc;
	}
	
	public long getOwnerid()
	{
		return ownerid;
	}

	
	public void setOwnerid(long ownerid)
	{
		this.ownerid = ownerid;
	}
	
}
