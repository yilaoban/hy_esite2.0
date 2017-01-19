package com.huiyee.interact.spread.model;

import java.io.File;
import java.io.Serializable;
import java.util.Date;

public class SpreadOption implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1098595367689760553L;
	private long id;
	private long spreadid;
	private String wbid;
	private String title;
	private String content;
	private String pic;
	private String img;//±¸ÓÃµÄ
	private Date createtime;
	private String wburl;
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
	public String getWbid()
	{
		return wbid;
	}
	public void setWbid(String wbid)
	{
		this.wbid = wbid;
	}
	public String getContent()
	{
		return content;
	}
	public void setContent(String content)
	{
		this.content = content;
	}
	public String getPic()
	{
		return pic;
	}
	public void setPic(String pic)
	{
		this.pic = pic;
	}
	public Date getCreatetime()
	{
		return createtime;
	}
	public void setCreatetime(Date createtime)
	{
		this.createtime = createtime;
	}
	public String getImg()
	{
		return img;
	}
	public void setImg(String img)
	{
		this.img = img;
	}
	public String getTitle()
	{
		return title;
	}
	public void setTitle(String title)
	{
		this.title = title;
	}
	public String getWburl()
	{
		return wburl;
	}
	public void setWburl(String wburl)
	{
		this.wburl = wburl;
	}
	
}
