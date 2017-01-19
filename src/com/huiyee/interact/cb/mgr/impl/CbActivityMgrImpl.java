package com.huiyee.interact.cb.mgr.impl;

import java.util.List;

import com.huiyee.esite.model.CbActivity;
import com.huiyee.interact.cb.dao.ICbActivityDao;
import com.huiyee.interact.cb.mgr.ICbActivityMgr;
import com.huiyee.interact.cb.model.InteractCb;
import com.huiyee.interact.cb.model.WxHbCheck;

public class CbActivityMgrImpl implements ICbActivityMgr {

	private ICbActivityDao cbActivityDao;

	public void setCbActivityDao(ICbActivityDao cbActivityDao) {
		this.cbActivityDao = cbActivityDao;
	}

	@Override
	public List<CbActivity> findActivityList(long owner, int start, int rows) {
		return cbActivityDao.findActivityList(owner, start, rows);
	}

	@Override
	public CbActivity findActivity(long id) {
		return cbActivityDao.findActivity(id);
	}

	@Override
	public InteractCb findCb(long owner) {
		return cbActivityDao.findCb(owner);
	}

	@Override
	public WxHbCheck findCbHbCheck(long mpid, long aid) {
		return cbActivityDao.findCbHbCheck(mpid, aid);
	}

}
