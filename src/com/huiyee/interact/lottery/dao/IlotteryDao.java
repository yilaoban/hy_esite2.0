package com.huiyee.interact.lottery.dao;

import java.util.List;

import com.huiyee.esite.model.SinaUser;
import com.huiyee.esite.model.WxUser;
import com.huiyee.interact.lottery.model.Lottery;
import com.huiyee.interact.lottery.model.LotteryUserRecord;
import com.huiyee.interact.lottery.model.LotteryUserSub;
import com.huiyee.interact.lottery.model.LotteryWinner;

public interface IlotteryDao
{

	public int findLotteryTotal(long owner, String type, long omid);

	public List<Lottery> findAllLottery(int start, int size, long owner, String type, long omid);

	public Lottery findLotteryById(long id);

	public long saveLottery(Lottery l, long omid);

	public int updateLottery(Lottery l, long ownerid, long lid);

	public int updateStatus(long lid, String status, long owner);
	
	public long saveLotteryWinnerUser(LotteryUserSub lotteryuser);

	public List<Lottery> findLotteryByOwner(long ownerid);

	public void updateLotteryGuanLian(long lotteryid);
	
	public List<LotteryWinner> findLotteryWinner(long lid,long start,int size);
	
	public WxUser findWxUser(long wxuid);
	
	public SinaUser findSinaUser(long wbuid);

	public List<LotteryUserRecord> findUserRecord(long lid, long entity, int type);
	
	public long addLottery(long ownerid,String type,String title);

	public List<LotteryUserRecord> findRecordByUser(long lid, long entity, int type);
	
}
