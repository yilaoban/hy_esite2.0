package com.huiyee.interact.servicerpj.mgr;

import java.util.List;

import com.huiyee.interact.servicerpj.dao.IYuyueServicerDao;
import com.huiyee.interact.yuyue.model.YuYueServicer;

public class ServicerMgrImpl implements IServicerMgr {

	private IYuyueServicerDao yuyueServicerDao;

	public void setYuyueServicerDao(IYuyueServicerDao yuyueServicerDao) {
		this.yuyueServicerDao = yuyueServicerDao;
	}

	@Override
	public int findServicerTotal(long owner) {
		return yuyueServicerDao.findServicerTotalByOwner(owner);
	}

	@Override
	public List<YuYueServicer> findServicerList(long owner, int start, int rows) {
		List<YuYueServicer> list = yuyueServicerDao.findServicers(owner, start, rows);
		for (YuYueServicer ser : list) {
			ser.setWdList(yuyueServicerDao.findServicerPjWdListBySerid(ser.getId()));
		}
		return list;
	}

}
