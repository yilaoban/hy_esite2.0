package com.huiyee.interact.lottery.mgr;

import java.util.List;

import com.huiyee.esite.model.Account;
import com.huiyee.esite.model.ContentProduct;
import com.huiyee.interact.lottery.model.LotteryLose;
import com.huiyee.interact.lottery.model.LotteryPrize;
import com.huiyee.interact.lottery.model.LotteryPrizeCode;
import com.huiyee.interact.lottery.dto.IDto;


public interface ILotteryPrizeMgr
{

	public List<LotteryPrize> findLotteryNPrize(long lid);
	
	public List<LotteryPrize> findLotteryYPrize(long lid);
	
	public LotteryLose findLose(long uid,int type,long lid);
	
	public void addLose(long uid,int type,long lid);
	
	public void updateLose(long id);
	
	public void updateWinLose(long id);
	
	public void updateUsed(long lpid);
	
	public IDto findprizeList(long lid, int pageId);

	public IDto findLotteryPrize(long lid, int pageId);
	
	public long delprize(long id);

	public LotteryPrize findPrizeByLpid(long id);

	public int addLotteryPrizeCode(List<LotteryPrizeCode> list, long id);
	
	public IDto findLotteryCodeListByLpid(long lpid,int pageId);
	
	public IDto findLotteryCodeByLpidAndCode(long lpid,String code);

	public long addprize(LotteryPrize lp);

	public long updateprize(LotteryPrize lp);
	
	public int findLotteryTotalPrizeById(long lid);

	public List<ContentProduct> findProductByTypeZ(Account account, String productSubtypeK);
	
	public List<LotteryPrize> findLotteryPrize(long lid);
	
	public int updateLotteryPrize(List<LotteryPrize> prizes);
}
