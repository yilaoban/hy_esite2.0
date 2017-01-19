package com.huiyee.interact.cb.dao;

import java.util.List;

import com.huiyee.esite.model.CbActivity;
import com.huiyee.weixin.model.WxPageShow;


public interface IActivityMatterDao
{
	public List<CbActivity> findCbActivityList(long owner);
	
	public int findTotalActivityMatterList(long cbid,long aid);
	
	public List<WxPageShow> findActivityMatterList(long cbid,long aid,int start,int size);
	
	public WxPageShow findWxPageShowById(long id,long aid);

	public List<Long> findCbAptRecord(long cbid, long wxuid);
	
	public WxPageShow findActivityMatterDetailById(long id,long aid);
}
