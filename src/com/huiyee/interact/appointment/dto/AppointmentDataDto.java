package com.huiyee.interact.appointment.dto;

import java.util.List;

import com.huiyee.esite.dto.Pager;
import com.huiyee.interact.appointment.model.AppointmentDataModel;
import com.huiyee.interact.appointment.model.AptMapping;

public class AppointmentDataDto implements IDto
{

	private List<AppointmentDataModel> list;
	private List<AptMapping> mapping;
	private Pager pager;

	public List<AppointmentDataModel> getList()
	{
		return list;
	}

	public void setList(List<AppointmentDataModel> list)
	{
		this.list = list;
	}

	public List<AptMapping> getMapping()
	{
		return mapping;
	}

	public void setMapping(List<AptMapping> mapping)
	{
		this.mapping = mapping;
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
