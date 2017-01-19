package com.huiyee.interact.lottery.dao;

import java.util.List;

import com.huiyee.interact.lottery.model.LotteryPrize;
import com.huiyee.interact.lottery.model.LotteryPrizeCode;

public interface IlotteryPrizeInfoDao {
	
	public List<LotteryPrizeCode> findPrizeWinnnerInformation(long lid,long ownerid,int type,long pageid);
	
	public List<LotteryPrizeCode> findPrizeWinnnerInformation(long lid,int type,long pageid,int start,int size);

	public LotteryPrize findPrizeNameById(long lpid);
}
