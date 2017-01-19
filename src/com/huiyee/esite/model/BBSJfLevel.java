package com.huiyee.esite.model;

import java.io.Serializable;

public class BBSJfLevel implements Serializable
{
	private static final long serialVersionUID = 7887791139554794432L;
	private String level_name;
	private long id;
	private long require_jf;
	private long owner;
	public String getLevel_name()
	{
		return level_name;
	}
	public void setLevel_name(String level_name)
	{
		this.level_name = level_name;
	}
	public long getId()
	{
		return id;
	}
	public void setId(long id)
	{
		this.id = id;
	}
	public long getRequire_jf()
	{
		return require_jf;
	}
	public void setRequire_jf(long require_jf)
	{
		this.require_jf = require_jf;
	}
	public long getOwner()
	{
		return owner;
	}
	public void setOwner(long owner)
	{
		this.owner = owner;
	}
	
	
	
}
