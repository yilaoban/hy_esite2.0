package com.huiyee.esite.mgr.imp;

import com.huiyee.esite.dao.IHdUserDataDao;
import com.huiyee.esite.mgr.IHdUserDataMgr;

public class HdUserDataMgrImpl implements IHdUserDataMgr {

	private IHdUserDataDao hdUserDataDao;

	public void setHdUserDataDao(IHdUserDataDao hdUserDataDao) {
		this.hdUserDataDao = hdUserDataDao;
	}

	@Override
	public int addUserData(long uid, int utype, long hdid, long featureid) {
		return hdUserDataDao.addUserData(uid, utype, hdid, featureid);
	}

	@Override
	public int findUserDayNum(long uid, int utype, long hdid, long featureid) {
		return hdUserDataDao.findUserDayNum(uid, utype, hdid, featureid);
	}

	@Override
	public int findUserTotal(long uid, int utype, long hdid, long featureid) {
		return hdUserDataDao.findUserTotal(uid, utype, hdid, featureid);
	}

}
