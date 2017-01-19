package com.huiyee.interact.lottery.dto;

import java.util.List;

import com.huiyee.interact.lottery.model.Lottery;
import com.huiyee.interact.lottery.model.LotteryPrizeCode;
import com.huiyee.interact.lottery.model.LotteryUserSub;
import com.huiyee.interact.lottery.model.Pager;

public class LotteryDto implements IDto
{
	private List<Lottery> list;
	private Pager pager;
	private Lottery lottery;
	private Usertype usertype;
	private LotteryUserSub lotteryUser;
	private List<LotteryPrizeCode> lotteryPrizeList;
	
	public List<LotteryPrizeCode> getLotteryPrizeList()
	{
		return lotteryPrizeList;
	}

	public void setLotteryPrizeList(List<LotteryPrizeCode> lotteryPrizeList)
	{
		this.lotteryPrizeList = lotteryPrizeList;
	}

	public List<Lottery> getList()
	{
		return list;
	}

	public void setList(List<Lottery> list)
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

	public Lottery getLottery()
	{
		return lottery;
	}

	public void setLottery(Lottery lottery)
	{
		this.lottery = lottery;
	}

	public Usertype getUsertype()
	{
		return usertype;
	}

	public void setUsertype(Usertype usertype)
	{
		this.usertype = usertype;
	}

	public LotteryUserSub getLotteryUser()
	{
		return lotteryUser;
	}

	public void setLotteryUser(LotteryUserSub lotteryUser)
	{
		this.lotteryUser = lotteryUser;
	}
	
	
}
