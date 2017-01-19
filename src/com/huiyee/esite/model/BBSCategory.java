package com.huiyee.esite.model;

import java.io.Serializable;

public class BBSCategory implements Serializable
{
	private static final long serialVersionUID = 1148045484410069167L;
	private long id;
	private String name;
	private long owner;
	private int rank;

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
	public long getOwner()
	{
		return owner;
	}
	public void setOwner(long owner)
	{
		this.owner = owner;
	}
	public int getRank()
	{
		return rank;
	}
	public void setRank(int rank)
	{
		this.rank = rank;
	}
	
}
