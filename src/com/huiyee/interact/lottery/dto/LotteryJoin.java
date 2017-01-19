package com.huiyee.interact.lottery.dto;

import java.util.List;

public class LotteryJoin
{
	private int joinnum;// 抽奖次数
	private int winnum;// 中奖次数
	private int failnum;// 失败次数
	private List<String> lpname;// 奖品名称

	public int getJoinnum()
	{
		return joinnum;
	}

	public void setJoinnum(int joinnum)
	{
		this.joinnum = joinnum;
	}

	public int getWinnum()
	{
		return winnum;
	}

	public void setWinnum(int winnum)
	{
		this.winnum = winnum;
	}

	public int getFailnum()
	{
		return failnum;
	}

	public void setFailnum(int failnum)
	{
		this.failnum = failnum;
	}

	public List<String> getLpname()
	{
		return lpname;
	}

	public void setLpname(List<String> lpname)
	{
		this.lpname = lpname;
	}
}
