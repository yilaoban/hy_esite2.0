package com.huiyee.interact.lottery.dto;

import com.huiyee.interact.lottery.model.LotteryPrize;

public class DrawLp
{
	private LotteryPrize lp;
	private int min;
	private int max;

	public LotteryPrize getLp()
	{
		return lp;
	}

	public int getMin()
	{
		return min;
	}

	public int getMax()
	{
		return max;
	}

	public void setLp(LotteryPrize lp)
	{
		this.lp = lp;
	}

	public void setMin(int min)
	{
		this.min = min;
	}

	public void setMax(int max)
	{
		this.max = max;
	}

}
