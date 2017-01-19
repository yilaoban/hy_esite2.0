package com.huiyee.esite.dao;

import java.util.List;

import com.huiyee.esite.model.CbActivity;


public interface IMarketActivityDao
{
	public int findTotalCbActivityByCbid(long owner);
	
	public List<CbActivity> findCbActivityListByCbid(long owner,int start,int size);
	
	public int saveMarketActivity(CbActivity cbActivity);
	
	public int delMarketActivity(long aid);
	
	public CbActivity findCbActivityByAid(long aid);
	
	public int updateMarketActivity(CbActivity cbActivity);
	
	public int updateMarketActivityStatus(long aid, int status);
	
	public String findM_title(long owner, int type, long enid);
	
	public long findNpageid(long owner);
	
}
