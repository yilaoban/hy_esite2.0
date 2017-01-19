package com.huiyee.interact.lottery.dto;

import java.util.List;

import com.huiyee.interact.lottery.model.LotteryPrize;
import com.huiyee.interact.lottery.model.LotteryPrizeCode;
import com.huiyee.interact.lottery.model.Pager;

public class PrizeDto implements IDto
{
	private List<LotteryPrize> prizelist;
	private LotteryPrize prize;
	private Pager pager;
	private List<LotteryPrizeCode> prizeCodeList;
	
	public List<LotteryPrizeCode> getPrizeCodeList()
	{
		return prizeCodeList;
	}

	public void setPrizeCodeList(List<LotteryPrizeCode> prizeCodeList)
	{
		this.prizeCodeList = prizeCodeList;
	}

	public List<LotteryPrize> getPrizelist()
	{
		return prizelist;
	}

	public void setPrizelist(List<LotteryPrize> prizelist)
	{
		this.prizelist = prizelist;
	}

	public LotteryPrize getPrize()
	{
		return prize;
	}

	public void setPrize(LotteryPrize prize)
	{
		this.prize = prize;
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
