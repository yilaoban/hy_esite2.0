package com.huiyee.interact.yuyue.model;


public class YuYueSSTimeUsed
{
	private long id;
	private long dateday;
	private long stid;
	private long ssid;
	private int used;
	private long owner;
	
	public long getId()
	{
		return id;
	}
	
	public void setId(long id)
	{
		this.id = id;
	}
	
	public long getDateday()
	{
		return dateday;
	}
	
	public void setDateday(long dateday)
	{
		this.dateday = dateday;
	}
	
	public long getStid()
	{
		return stid;
	}
	
	public void setStid(long stid)
	{
		this.stid = stid;
	}
	
	public long getSsid()
	{
		return ssid;
	}
	
	public void setSsid(long ssid)
	{
		this.ssid = ssid;
	}
	
	public int getUsed()
	{
		return used;
	}
	
	public void setUsed(int used)
	{
		this.used = used;
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
