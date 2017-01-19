package com.huiyee.interact.renqi.dto;

import java.util.List;

import com.huiyee.esite.dto.Pager;
import com.huiyee.interact.renqi.model.RenQiData;
import com.huiyee.interact.renqi.model.RenQiDetail;

public class RenQiDataDto implements IDto
{
	private List<RenQiData> list;
	private Pager pager;
	private List<RenQiDetail> details;

	public List<RenQiData> getList()
	{
		return list;
	}

	public void setList(List<RenQiData> list)
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

	public List<RenQiDetail> getDetails()
	{
		return details;
	}

	public void setDetails(List<RenQiDetail> details)
	{
		this.details = details;
	}

}
