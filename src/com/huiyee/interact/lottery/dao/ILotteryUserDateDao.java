package com.huiyee.interact.lottery.dao;

import com.huiyee.interact.lottery.model.LotteryUserDate;

public interface ILotteryUserDateDao
{
	public LotteryUserDate findUserDate(long lid, long wbuid,int type);

	public void addUserDate(long lid, long wbuid,int type);

}
