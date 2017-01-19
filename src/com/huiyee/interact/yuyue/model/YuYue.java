package com.huiyee.interact.yuyue.model;

import com.huiyee.interact.appointment.model.AppointmentModel;


public class YuYue
{
	private long id;
	private long owner;
	private long aptid;
	private AppointmentModel apt;
	
	public long getId()
	{
		return id;
	}
	
	public void setId(long id)
	{
		this.id = id;
	}
	
	public long getOwner()
	{
		return owner;
	}
	
	public void setOwner(long owner)
	{
		this.owner = owner;
	}
	
	public long getAptid()
	{
		return aptid;
	}
	
	public void setAptid(long aptid)
	{
		this.aptid = aptid;
	}

	
	public AppointmentModel getApt()
	{
		return apt;
	}

	
	public void setApt(AppointmentModel apt)
	{
		this.apt = apt;
	}
	
	
}
