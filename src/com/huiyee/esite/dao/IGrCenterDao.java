package com.huiyee.esite.dao;

import com.huiyee.esite.model.BalancePay;

public interface IGrCenterDao {
	
	public int findTotalUnusedKQ(long hyuid, String status);
	
	public long saveBalancePay(String ip, int price, long hyuid, long owner,long ruleid);
	
	public int updateBalancePayById(long id,String mediaorder,long hyuid,long rid,int price);
	
	public BalancePay findBalancePayById(long payid);
	
}
