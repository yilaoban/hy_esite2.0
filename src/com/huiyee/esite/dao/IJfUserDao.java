package com.huiyee.esite.dao;

import java.util.Date;
import java.util.List;

import com.huiyee.esite.model.UserBalance;


public interface IJfUserDao
{
	public int findUserBalanceTotal(long owner,long mpid,long hyuid,String nickname,Date startTime,Date endTime,int type);
	
	public List<UserBalance> findUserBalance(long owner,long mpid,long hyuid,String nickname,Date startTime,Date endTime,int type,int start,int size);
	
	public UserBalance findUserBalanceByHyUid(long hyuid);
}
