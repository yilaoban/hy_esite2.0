package com.huiyee.esite.fdao;

import java.util.List;

import com.huiyee.esite.model.Module;
import com.huiyee.esite.model.PageBlockRelation;
import com.huiyee.interact.lottery.model.Lottery;
import com.huiyee.interact.lottery.model.LotteryPrize;

public interface IHd125Dao {
	
	public long saveFeatureInteractLottery(long pageid,String type);
	
	public Module findLotteryidByFid(long fid);
	
	public Module findLotteryTypeByFid(long fid);
	
	public List<Lottery> findInteractLotteryByOwner(long ownerid,String type);
	
	public int updateFeatureIneractLottery(long lotteryid,long fid);
	
	public Lottery findFeatureInteractLotteryById(long fid);
	
	public List<LotteryPrize> findLotteryPriceByLotteryid(long lid);
	
	public PageBlockRelation findPageBlockRelationByRelationid(long relationid);
	
	public int updatePageBlockRelationByRelationid(long relationid,String json);
}
