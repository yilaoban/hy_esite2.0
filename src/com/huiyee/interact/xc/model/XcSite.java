package com.huiyee.interact.xc.model;

import java.io.Serializable;

public class XcSite implements Serializable
{
	private long id;
	private long siteid;
	private long xcid;
	private String type;
	private String name; //Õ¾µãÃû³Æ
	public long getId()
	{
		return id;
	}
	public void setId(long id)
	{
		this.id = id;
	}
	public long getSiteid()
	{
		return siteid;
	}
	public void setSiteid(long siteid)
	{
		this.siteid = siteid;
	}
	public long getXcid()
	{
		return xcid;
	}
	public void setXcid(long xcid)
	{
		this.xcid = xcid;
	}
	public String getType()
	{
		return type;
	}
	public void setType(String type)
	{
		this.type = type;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	
}
