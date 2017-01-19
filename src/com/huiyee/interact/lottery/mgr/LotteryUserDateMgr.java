package com.huiyee.interact.lottery.mgr;

import com.huiyee.interact.lottery.dao.ILotteryUserDateDao;
import com.huiyee.interact.lottery.model.LotteryUserDate;


public class LotteryUserDateMgr implements ILotteryUserDateMgr
{
	private ILotteryUserDateDao lotteryUserDateDao;

	public void setLotteryUserDateDao(ILotteryUserDateDao lotteryUserDateDao)
	{
		this.lotteryUserDateDao = lotteryUserDateDao;
	}

	@Override
	public void addUserDate(long lid, long wbuid,int type)
	{
		lotteryUserDateDao.addUserDate(lid, wbuid,type);
	}

	@Override
	public LotteryUserDate findUserDate(long lid, long wbuid,int type)
	{
		return lotteryUserDateDao.findUserDate(lid, wbuid,type);
	}
}
