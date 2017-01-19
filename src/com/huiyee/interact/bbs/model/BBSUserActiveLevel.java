package com.huiyee.interact.bbs.model;

import java.io.Serializable;

public class BBSUserActiveLevel implements Serializable
{
	private static final long serialVersionUID = 9195525978531034885L;
	private int id;
	private String level_name;
	private long required_hour;
	private String level_img;
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public String getLevel_name()
	{
		return level_name;
	}
	public void setLevel_name(String level_name)
	{
		this.level_name = level_name;
	}
	public long getRequired_hour()
	{
		return required_hour;
	}
	public void setRequired_hour(long required_hour)
	{
		this.required_hour = required_hour;
	}
	public String getLevel_img()
	{
		return level_img;
	}
	public void setLevel_img(String level_img)
	{
		this.level_img = level_img;
	}
	
	
}
