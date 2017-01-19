package com.huiyee.interact.lottery.mgr;

import com.huiyee.interact.lottery.model.LotteryPrizeCode;

public interface ILotteryPrizeCodeMgr
{
 public LotteryPrizeCode findOneCode(long lpid,int rm);
 
 public void updateOneCode(long lpcid);
}
