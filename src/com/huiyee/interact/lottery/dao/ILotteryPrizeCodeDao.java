package com.huiyee.interact.lottery.dao;

import java.util.List;

import com.huiyee.interact.lottery.model.LotteryPrizeCode;

public interface ILotteryPrizeCodeDao
{
	 public LotteryPrizeCode findOneCode(long lpid,int rm);
	 
	 public void updateOneCode(long lpcid);

	public int addCouponsCode(List<LotteryPrizeCode> list);
	
	public List<LotteryPrizeCode> findLotteryCodeListByLpid(long lpid,int start,int size);
	
	public int findLotteryPrizeCodeTotal(long lpid);

	public int findCodeTotal(long id);

	public LotteryPrizeCode findLotteryCodeByLpcid(long lpcid);
	
	public List<LotteryPrizeCode> findLotteryCodeByLpidAndCode(long lpid,String code);
}
