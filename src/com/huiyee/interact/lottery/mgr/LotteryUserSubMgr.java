package com.huiyee.interact.lottery.mgr;

import com.huiyee.interact.lottery.dao.ILotteryUserSubDao;


public class LotteryUserSubMgr implements ILotteryUserSubMgr
{
private ILotteryUserSubDao lotteryUserSubDao;

public void setLotteryUserSubDao(ILotteryUserSubDao lotteryUserSubDao)
{
	this.lotteryUserSubDao = lotteryUserSubDao;
}
}
