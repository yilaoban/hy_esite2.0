package com.huiyee.interact.renqi.dto;

import java.util.List;

import com.huiyee.esite.dto.Pager;
import com.huiyee.interact.lottery.model.Lottery;
import com.huiyee.interact.renqi.model.RenQi;

public class RqDto implements IDto
{
	private List<RenQi> list;
	private List<Lottery> lottery;
	private Pager pager;
	private RenQi renqi;

	public List<RenQi> getList()
	{
		return list;
	}

	public void setList(List<RenQi> list)
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

	public List<Lottery> getLottery()
	{
		return lottery;
	}

	public void setLottery(List<Lottery> lottery)
	{
		this.lottery = lottery;
	}

	public RenQi getRenqi()
	{
		return renqi;
	}

	public void setRenqi(RenQi renqi)
	{
		this.renqi = renqi;
	}
}
