package com.huiyee.esite.model;

import java.io.Serializable;

public class BBSJfRule implements Serializable
{
	private static final long serialVersionUID = -3572475159572102922L;
	private long id;
	private int action;
	private int jifen;
	private long owner;
	
	public int getAction()
	{
		return action;
	}
	public void setAction(int action)
	{
		this.action = action;
	}
	public int getJifen()
	{
		return jifen;
	}
	public void setJifen(int jifen)
	{
		this.jifen = jifen;
	}
	public long getOwner()
	{
		return owner;
	}
	public void setOwner(long owner)
	{
		this.owner = owner;
	}
	public long getId()
	{
		return id;
	}
	public void setId(long id)
	{
		this.id = id;
	}
	

}
