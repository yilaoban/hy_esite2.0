package com.huiyee.esite.mgr.imp;

import java.util.List;

import com.huiyee.esite.dao.IMoveToGroupDao;
import com.huiyee.esite.mgr.IMoveToGroupMgr;
import com.huiyee.esite.model.WxGroup;


public class MoveToGroupMgrImpl implements IMoveToGroupMgr
{
	private IMoveToGroupDao moveToGroupDao;

	public void setMoveToGroupDao(IMoveToGroupDao moveToGroupDao)
	{
		this.moveToGroupDao = moveToGroupDao;
	}

	@Override
	public int saveWxGroupJob(long mpid, String name, String jobrule,String type)
	{
		return moveToGroupDao.saveWxGroupJob(mpid,name,jobrule,type);
	}

	@Override
	public List<WxGroup> findWxGroupList(long mpid)
	{
		return moveToGroupDao.findWxGroupList(mpid);
	}
	
	@Override
	public long saveCrmWxGroup(long mpid, String name) {
		return moveToGroupDao.saveCrmWxGroup(mpid, name);
	}
	
}
