package com.huiyee.interact.cs.model;

import java.util.Date;

import com.huiyee.esite.model.SuperHdModel;

public class ChuanSan extends SuperHdModel
{

	private static final long serialVersionUID = 1L;
	private long id;
	private String title;
	private long owner;
	private int utype;
	private String content;
	private String link;
	private String jcontent;

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

	public long getOwner()
	{
		return owner;
	}

	public void setOwner(long owner)
	{
		this.owner = owner;
	}

	public int getUtype()
	{
		return utype;
	}

	public void setUtype(int utype)
	{
		this.utype = utype;
	}

	public String getContent()
	{
		return content;
	}

	public void setContent(String content)
	{
		this.content = content;
	}

	public String getLink()
	{
		return link;
	}

	public void setLink(String link)
	{
		this.link = link;
	}

	public String getJcontent()
	{
		return jcontent;
	}

	public void setJcontent(String jcontent)
	{
		this.jcontent = jcontent;
	}

}
