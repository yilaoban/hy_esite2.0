package com.huiyee.esite.mgr.imp;

import java.util.List;

import com.huiyee.esite.dao.IMarketActivityDao;
import com.huiyee.esite.mgr.IMarketActivityMgr;
import com.huiyee.esite.model.CbActivity;


public class MarketActivityMgrImpl implements IMarketActivityMgr
{
	private IMarketActivityDao marketActivityDao;

	public void setMarketActivityDao(IMarketActivityDao marketActivityDao)
	{
		this.marketActivityDao = marketActivityDao;
	}

	@Override
	public int findTotalCbActivityByCbid(long owner)
	{
		return marketActivityDao.findTotalCbActivityByCbid(owner);
	}

	@Override
	public List<CbActivity> findCbActivityListByCbid(long owner,int start,int size)
	{
		return marketActivityDao.findCbActivityListByCbid(owner,start,size);
	}

	@Override
	public int saveMarketActivity(CbActivity cbActivity)
	{
		return marketActivityDao.saveMarketActivity(cbActivity);
	}

	@Override
	public int delMarketActivity(long aid)
	{
		return marketActivityDao.delMarketActivity(aid);
	}

	@Override
	public CbActivity findCbActivityByAid(long ownerid,long aid)
	{
		CbActivity cb =  marketActivityDao.findCbActivityByAid(aid);
		if(cb!=null){
			cb.setM_title(marketActivityDao.findM_title(ownerid, cb.getType(), cb.getEnid()));
		}
		return cb;
	}

	@Override
	public int updateMarketActivity(CbActivity cbActivity)
	{
		return marketActivityDao.updateMarketActivity(cbActivity);
	}

	@Override
	public int updateMarketActivityStatus(long aid, int status)
	{
		return marketActivityDao.updateMarketActivityStatus(aid,status);
	}
	
	
}
