package com.huiyee.interact.lottery.mgr;

import com.huiyee.interact.lottery.model.LotteryUserDate;

public interface ILotteryUserDateMgr
{
	public LotteryUserDate findUserDate(long lid, long wbuid,int type);

	public void addUserDate(long lid, long wbuid,int type);
}
