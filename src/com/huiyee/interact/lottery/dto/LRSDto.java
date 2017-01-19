
package com.huiyee.interact.lottery.dto;

import java.util.List;
import java.util.Map;

import com.huiyee.interact.lottery.model.LotteryPrize;
import com.huiyee.interact.lottery.model.LotteryUserShow;

public class LRSDto implements IDto
{

	private long maxRecordid;
	private List<LotteryUserShow> list;
	private int winnum;
	private int totalnum;

	public long getMaxRecordid()
	{
		return maxRecordid;
	}

	public void setMaxRecordid(long maxRecordid)
	{
		this.maxRecordid = maxRecordid;
	}

	public List<LotteryUserShow> getList()
	{
		return list;
	}

	public void setList(List<LotteryUserShow> list)
	{
		this.list = list;
	}

	public int getWinnum()
	{
		return winnum;
	}

	public void setWinnum(int winnum)
	{
		this.winnum = winnum;
	}

	public int getTotalnum()
	{
		return totalnum;
	}

	public void setTotalnum(int totalnum)
	{
		this.totalnum = totalnum;
	}

}
