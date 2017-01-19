package com.huiyee.interact.lottery.mgr;

import java.util.List;

import com.huiyee.interact.lottery.dao.ISinaGroupDao;
import com.huiyee.interact.lottery.model.SinaGroup;

public class SinaGroupMgr implements ISinaGroupMgr
{
	private ISinaGroupDao sinaGroupDao;
	
	public ISinaGroupDao getSinaGroupDao()
	{
		return sinaGroupDao;
	}

	public void setSinaGroupDao(ISinaGroupDao sinaGroupDao)
	{
		this.sinaGroupDao = sinaGroupDao;
	}

	@Override
	public List<SinaGroup> findWbGroupListByOwner(long owner){
		return sinaGroupDao.findWbGroupListByOwner(owner);
	}

	@Override
	public int findWbuidInGroup(long gid, long owner, long wbuid)
	{
		return sinaGroupDao.findWbuidInGroup(gid, owner, wbuid);
	}

}
