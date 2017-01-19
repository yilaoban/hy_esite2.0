package com.huiyee.interact.lottery.mgr;

import com.huiyee.interact.lottery.dao.ILotteryPrizeCodeDao;
import com.huiyee.interact.lottery.model.LotteryPrizeCode;

public class LotteryPrizeCodeMgr implements ILotteryPrizeCodeMgr
{
   private ILotteryPrizeCodeDao lotteryPrizeCodeDao;

public void setLotteryPrizeCodeDao(ILotteryPrizeCodeDao lotteryPrizeCodeDao)
{
	this.lotteryPrizeCodeDao = lotteryPrizeCodeDao;
}

@Override
public LotteryPrizeCode findOneCode(long lpid,int rm)
{
	return lotteryPrizeCodeDao.findOneCode(lpid,rm);
}

@Override
public void updateOneCode(long lpcid)
{
	lotteryPrizeCodeDao.updateOneCode(lpcid);
}
}
