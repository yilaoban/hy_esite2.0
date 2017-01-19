package com.huiyee.interact.cs.dto;

import java.util.List;

import com.huiyee.esite.dto.Pager;
import com.huiyee.interact.cs.model.ChuanSan;

public class CsDto implements IDto
{
	private List<ChuanSan> list;
	private Pager pager;

	public List<ChuanSan> getList()
	{
		return list;
	}

	public void setList(List<ChuanSan> list)
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
