package com.huiyee.interact.lottery.dto;

import java.util.List;

import com.huiyee.interact.lottery.model.LotteryPrize;
import com.huiyee.interact.lottery.model.LotteryUser;
import com.huiyee.interact.lottery.model.LotteryUserRecord;
import com.huiyee.interact.lottery.model.LotteryWinnerDetail;
import com.huiyee.interact.lottery.model.Pager;
import com.huiyee.interact.lottery.model.WxHbLotterySend;

public class LotteryDataDto implements IDto
{
	private List<LotteryUser> user;
	private Pager pager;
	private List<LotteryPrize> prize;
	private List<LotteryWinnerDetail> record;
	private List<LotteryUserRecord> lur;
	private LotteryPrize lotteryPrize;
	
	private List<WxHbLotterySend> sends;
	
	public List<WxHbLotterySend> getSends()
	{
		return sends;
	}

	
	public void setSends(List<WxHbLotterySend> sends)
	{
		this.sends = sends;
	}

	public List<LotteryUserRecord> getLur()
	{
		return lur;
	}

	public void setLur(List<LotteryUserRecord> lur)
	{
		this.lur = lur;
	}

	public List<LotteryUser> getUser()
	{
		return user;
	}

	public void setUser(List<LotteryUser> user)
	{
		this.user = user;
	}

	public Pager getPager()
	{
		return pager;
	}

	public void setPager(Pager pager)
	{
		this.pager = pager;
	}

	public List<LotteryPrize> getPrize()
	{
		return prize;
	}

	public void setPrize(List<LotteryPrize> prize)
	{
		this.prize = prize;
	}

	public List<LotteryWinnerDetail> getRecord()
	{
		return record;
	}

	public void setRecord(List<LotteryWinnerDetail> record)
	{
		this.record = record;
	}

	public LotteryPrize getLotteryPrize()
	{
		return lotteryPrize;
	}

	public void setLotteryPrize(LotteryPrize lotteryPrize)
	{
		this.lotteryPrize = lotteryPrize;
	}

}
