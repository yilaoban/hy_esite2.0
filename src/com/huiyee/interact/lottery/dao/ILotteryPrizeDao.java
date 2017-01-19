package com.huiyee.interact.lottery.dao;

import java.util.List;

import com.huiyee.interact.lottery.model.LotteryLose;
import com.huiyee.interact.lottery.model.LotteryPrize;



public interface ILotteryPrizeDao
{

	public List<LotteryPrize> findLotteryNPrize(long lid);
	
	public List<LotteryPrize> findLotteryYPrize(long lid);
	
	public void updateUsed(long lpid);
	
    public LotteryLose findLose(long uid,int type,long lid);
    
    public void addLose(long uid,int type,long lid);
	
	public void updateLose(long id);
	
	public void updateWinLose(long id);

	public int findLotteryprizeTotal(long lid);
	
	public List<LotteryPrize> findPrizeList(int start, int size,long lid);
	
	public long delprice(long id);

	public LotteryPrize findPrizeByLpid(long id);

	public void updateTotal(int count, long id);

	public long saveprice(LotteryPrize lp);

	public long updateprice(LotteryPrize lp);
	
	public List<LotteryPrize> findLotteryTotalPrizeById(long lid);

	/**
	 * 查抽奖的前N等奖
	 * @param lid
	 * @return
	 */
	public List<LotteryPrize> findPrizeOrdByPrice(long lid);
	
	
	public List<LotteryPrize> findLotteryPrize(long lid);
	
	public int updateLotteryPrize(List<LotteryPrize> prizes);
}
