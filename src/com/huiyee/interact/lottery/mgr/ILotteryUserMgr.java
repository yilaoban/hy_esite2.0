package com.huiyee.interact.lottery.mgr;

import com.huiyee.interact.lottery.dto.IDto;
import com.huiyee.interact.lottery.model.LotteryUser;

public interface ILotteryUserMgr
{
	public LotteryUser findLotteryUser(long wbuid, long lid,int type);
	
	public void addLotteryUser(long wbuid,long lid,int total,int used,int type);
	
	public void updateLotteryUser(long wbuid,long lid,int total,int type);

	public IDto findLotteryData(int pageId, String nickName, long lid, long ownerid,int type);
	
	public int findJFen(long hyuid);
	
}
