package com.huiyee.interact.lottery.mgr;

import java.util.List;

import com.huiyee.esite.model.VisitUser;
import com.huiyee.interact.lottery.dto.IDto;
import com.huiyee.interact.lottery.dto.LotteryJoin;
import com.huiyee.interact.lottery.model.Lottery;
import com.huiyee.interact.lottery.model.LotteryUserRecord;
import com.huiyee.interact.lottery.model.LotteryUserSub;
import com.huiyee.interact.lottery.model.LotteryWinner;

public interface ILotteryMgr
{

	public IDto findAllLottery(int pageId, long owner, String string, long omid);

	public Lottery findLotteryById(long id);

	public long saveLottery(Lottery l, long omid);

	public IDto findLotteryById(long lid, long ownerid);

	public int updateLottery(Lottery l, long ownerid, long lid);

	public int updateStatus(long lid, String status, long owner);

	public long saveLotteryWinnerUser(LotteryUserSub lotteryuser);
	
	public IDto findPrizeWinnnerInformation(long lid,long wbuid,int type,long pageid);
	
	public IDto findPrizeWinnnerInformation(long lid,int type,long pageid,int pageId,int size);//所有中奖人
	
	public List<LotteryWinner> findLotteryWinner(long lid,String[] lpid, long start,int size,long xcid);

	public LotteryJoin findLotteryJoinDetail(long lid, VisitUser vu, String source);
	
	public long addLottery(long ownerid,String type,String title);

	public List<LotteryUserRecord> findRecordByUser(long lid, VisitUser vu);
	
}
