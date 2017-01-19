package com.huiyee.interact.bbs.model;

import java.io.Serializable;

public class BBSUserOnline implements Serializable
{
	private static final long serialVersionUID = -8124580951851184690L;
	private long user_id;
	private long online_latest;
	private long online_day;
	private long online_week;
	private long online_month;
	private long online_year;
	private long online_total;
	
	public long getUser_id()
	{
		return user_id;
	}
	public void setUser_id(long user_id)
	{
		this.user_id = user_id;
	}
	public long getOnline_latest()
	{
		return online_latest;
	}
	public void setOnline_latest(long online_latest)
	{
		this.online_latest = online_latest;
	}
	public long getOnline_day()
	{
		return online_day;
	}
	public void setOnline_day(long online_day)
	{
		this.online_day = online_day;
	}
	public long getOnline_week()
	{
		return online_week;
	}
	public void setOnline_week(long online_week)
	{
		this.online_week = online_week;
	}
	public long getOnline_month()
	{
		return online_month;
	}
	public void setOnline_month(long online_month)
	{
		this.online_month = online_month;
	}
	public long getOnline_year()
	{
		return online_year;
	}
	public void setOnline_year(long online_year)
	{
		this.online_year = online_year;
	}
	public long getOnline_total()
	{
		return online_total;
	}
	public void setOnline_total(long online_total)
	{
		this.online_total = online_total;
	}
	
}
