package com.huiyee.esite.model;

import java.io.Serializable;


public class Tag implements Serializable
{
	private static final long serialVersionUID = 5082372057284862482L;
	private long id;
	private String name;
	private String type;
	
	private int count;
	
	public int getCount()
	{
		return count;
	}

	public void setCount(int count)
	{
		this.count = count;
	}


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
	
	public String getType()
	{
		return type;
	}
	
	public void setType(String type)
	{
		this.type = type;
	}
	
	
	
}
