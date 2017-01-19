package com.huiyee.interact.appointment.model;

import java.io.Serializable;

public class AptMapping implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String mapping;
	private int dataShow;
	private String stype;

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getMapping()
	{
		return mapping;
	}

	public void setMapping(String mapping)
	{
		this.mapping = mapping;
	}

	public int getDataShow()
	{
		return dataShow;
	}

	public void setDataShow(int dataShow)
	{
		this.dataShow = dataShow;
	}

	public String getStype()
	{
		return stype;
	}

	public void setStype(String stype)
	{
		this.stype = stype;
	}
}
