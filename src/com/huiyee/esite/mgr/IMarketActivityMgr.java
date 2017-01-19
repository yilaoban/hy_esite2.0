package com.huiyee.esite.mgr;

import java.util.List;

import com.huiyee.esite.model.CbActivity;


public interface IMarketActivityMgr
{
	public int findTotalCbActivityByCbid(long owner);
	
	public List<CbActivity> findCbActivityListByCbid(long owner,int start,int size);
	
	public int saveMarketActivity(CbActivity cbActivity);
	
	public int delMarketActivity(long aid);
	
	public CbActivity findCbActivityByAid(long ownerid,long aid);
	
	public int updateMarketActivity(CbActivity cbActivity);
	
	public int updateMarketActivityStatus(long aid,int status);
}
