
package com.huiyee.weixin.model;

public class Wkq
{

	private long id;
	private long owner;
	private long yzpage;
	private long spage;
	private long fpage;
	private int bili;

	
	public int getBili()
	{
		return bili;
	}

	
	public void setBili(int bili)
	{
		this.bili = bili;
	}

	public long getId()
	{
		return id;
	}

	public long getOwner()
	{
		return owner;
	}

	public long getYzpage()
	{
		return yzpage;
	}

	public long getSpage()
	{
		return spage;
	}

	public long getFpage()
	{
		return fpage;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public void setOwner(long owner)
	{
		this.owner = owner;
	}

	public void setYzpage(long yzpage)
	{
		this.yzpage = yzpage;
	}

	public void setSpage(long spage)
	{
		this.spage = spage;
	}

	public void setFpage(long fpage)
	{
		this.fpage = fpage;
	}
}
