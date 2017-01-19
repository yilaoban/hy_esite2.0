
package com.huiyee.interact.appointment.dto;

import java.util.List;

import com.huiyee.interact.appointment.model.AppointmentModel;
import com.huiyee.interact.appointment.model.Pager;

public class OrderMesDto implements IDto
{

	private List<AppointmentModel> list;
	private int total;
	private Pager pager;

	public List<AppointmentModel> getList()
	{
		return list;
	}

	public void setList(List<AppointmentModel> list)
	{
		this.list = list;
	}

	public int getTotal()
	{
		return total;
	}

	public void setTotal(int total)
	{
		this.total = total;
	}

	public Pager getPager()
	{
		return pager;
	}

	public void setPager(Pager pager)
	{
		this.pager = pager;
	}

}
