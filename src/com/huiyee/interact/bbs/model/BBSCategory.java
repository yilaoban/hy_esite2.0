package com.huiyee.interact.bbs.model;

import java.io.Serializable;

public class BBSCategory implements Serializable{

	private static final long serialVersionUID = 1038248177001817676L;
	private String name;
	private long owner;
	private int rank;
	private long bbsid;
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
	public long getBbsid()
	{
		return bbsid;
	}
	public void setBbsid(long bbsid)
	{
		this.bbsid = bbsid;
	}
	
	
	
}
