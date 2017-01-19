package com.huiyee.interact.lottery.dao;

import java.util.List;
import java.util.Map;
import com.huiyee.interact.lottery.model.LotteryUser;

public interface ILotteryUserDao
{
	public LotteryUser findLotteryUser(long wbuid, long lid,int type);
	
	public void addLotteryUser(long wbuid,long lid,int total,int used,int type);
	
	public void updateLotteryUser(long wbuid,long lid,int total,int type);
	
	public void updateLotteryUsed(long wbuid,long lid,int type);

	public int findAllLotteryUserTotal(String nickName, long lid, long ownerid);

	public List<LotteryUser> findAllLotteryUser(String nickName, int i, int lotteryUserLimit, long lid, long ownerid);

	public int findAllWxUserTotal(String nickName, long lid, long ownerid,int type);

	public List<LotteryUser> findAllWxUser(String nickName, int i, int lotteryUserLimit, long lid, long ownerid,int type);

	public String findWbNickName(long wbuid);

	public String findWxNickName(long wbuid);

	public Map<String, String> findWxUserInfo(long uid);

	public Map<String, String> findHyUserInfo(long uid);
	

}
