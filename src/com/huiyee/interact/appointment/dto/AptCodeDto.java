
package com.huiyee.interact.appointment.dto;

import java.util.List;

import com.huiyee.esite.dto.Pager;
import com.huiyee.interact.appointment.model.AppointmentModel;
import com.huiyee.interact.appointment.model.AptCode;

public class AptCodeDto implements IDto
{

	private List<AptCode> list;
	private Pager pager;
	private int used;
	private int least;
	private AppointmentModel apt;

	public List<AptCode> getList()
	{
		return list;
	}

	public void setList(List<AptCode> list)
	{
		this.list = list;
	}

	public Pager getPager()
	{
		return pager;
	}

	public void setPager(Pager pager)
	{
		this.pager = pager;
	}

	public int getUsed()
	{
		return used;
	}

	public void setUsed(int used)
	{
		this.used = used;
	}

	public int getLeast()
	{
		return least;
	}

	public void setLeast(int least)
	{
		this.least = least;
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
