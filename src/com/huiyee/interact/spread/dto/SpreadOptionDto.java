package com.huiyee.interact.spread.dto;

import java.util.List;

import com.huiyee.interact.spread.model.Pager;
import com.huiyee.interact.spread.model.SpreadModel;
import com.huiyee.interact.spread.model.SpreadOption;

public class SpreadOptionDto implements IDto
{

	private List<SpreadOption> list;
	private SpreadModel sm;
	private SpreadOption sp;
	private Pager pager;

	public List<SpreadOption> getList()
	{
		return list;
	}

	public void setList(List<SpreadOption> list)
	{
		this.list = list;
	}

	public SpreadModel getSm()
	{
		return sm;
	}

	public void setSm(SpreadModel sm)
	{
		this.sm = sm;
	}

	public Pager getPager()
	{
		return pager;
	}

	public void setPager(Pager pager)
	{
		this.pager = pager;
	}

	public SpreadOption getSp()
	{
		return sp;
	}

	public void setSp(SpreadOption sp)
	{
		this.sp = sp;
	}
}
