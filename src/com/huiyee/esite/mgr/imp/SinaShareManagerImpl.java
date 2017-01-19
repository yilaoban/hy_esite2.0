package com.huiyee.esite.mgr.imp;

import java.util.List;

import com.huiyee.esite.dao.ISinaShareDao;
import com.huiyee.esite.mgr.ISinaShareManager;
import com.huiyee.esite.model.SinaShare;

public class SinaShareManagerImpl implements ISinaShareManager {
	private ISinaShareDao sinaShareDao;

	public void setSinaShareDao(ISinaShareDao sinaShareDao) {
		this.sinaShareDao = sinaShareDao;
	}

	@Override
	public List<SinaShare> findTask() {
		return sinaShareDao.findTask();
	}

	@Override
	public void updateStatus(long id, String status) {
		sinaShareDao.updateStatus(id, status);
	}

	@Override
	public List<SinaShare> findSinaShareBySiteGroup(long sitegroupid, long owner) {
		return sinaShareDao.findSinaShareBySiteGroup(sitegroupid,owner);
	}

	@Override
	public int findSinaShareTotal(long shareid) {
		return sinaShareDao.findSinaShareTotal(shareid);
	}

	@Override
	public SinaShare findSinaShareById(long shareid) {
		return sinaShareDao.findSinaShareById(shareid);
	}
}
