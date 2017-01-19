package com.huiyee.interact.bbs.model;

public class BBS
{
	private long id;
	private String name;
	private long owner;
	private int visittype;
	private int ovisittype;
	
	public int getOvisittype()
	{
		return ovisittype;
	}
	
	public void setOvisittype(int ovisittype)
	{
		this.ovisittype = ovisittype;
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
	public long getOwner()
	{
		return owner;
	}
	public void setOwner(long owner)
	{
		this.owner = owner;
	}
	public int getVisittype()
	{
		return visittype;
	}
	public void setVisittype(int visittype)
	{
		this.visittype = visittype;
	}
}
