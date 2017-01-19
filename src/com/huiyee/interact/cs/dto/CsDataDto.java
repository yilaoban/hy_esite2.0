package com.huiyee.interact.cs.dto;

import java.util.List;

import com.huiyee.esite.dto.Pager;
import com.huiyee.interact.cs.model.CsData;

public class CsDataDto implements IDto
{
	private List<CsData> list;
	private Pager pager;

	public List<CsData> getList()
	{
		return list;
	}

	public void setList(List<CsData> list)
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
}
