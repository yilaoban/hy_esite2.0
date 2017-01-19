package com.huiyee.esite.dao;

import com.huiyee.esite.model.UserBalance;

public interface IOwnerBalanceDao
{
	public void addMoreBalance(long hyuid, int balance);

	public void addLessBalance(long hyuid, int balance);
	
	public void addMoreRmbBalance(long hyuid, int balance);

	public void addLessRmbBalance(long hyuid, int balance);
	
	public int findRmbByUser(long uid);

	
	public int findJFByUser(long uid);
	/**
	 * 
	 * @param uid
	 * @param ownerwbuid
	 * @param utype
	 * @param preused 可以是负数,负数是解冻 冻结的积分
	 */
	public void updatePreUsedByUser(long hyuid,int preused);
	
	public int findTotalRmbByUser(long uid);
	
	public int findRmbusedByUser(long uid);
	
}
