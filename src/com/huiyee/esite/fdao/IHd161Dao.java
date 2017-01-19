package com.huiyee.esite.fdao;

import com.huiyee.interact.lottery.model.Lottery;

public interface IHd161Dao {

	public long saveFeatureInteractLottery(final long pageid,final String type,long lotteryid);
	 
	public long findLotteryIdByFid(long fid);
	
	public int updateLottery(Lottery lottery);
	
}
