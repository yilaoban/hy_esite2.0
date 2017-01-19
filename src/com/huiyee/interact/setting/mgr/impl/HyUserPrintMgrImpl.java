package com.huiyee.interact.setting.mgr.impl;

import com.huiyee.interact.setting.dao.IHyUserPrintDao;
import com.huiyee.interact.setting.mgr.IHyUserPrintMgr;
import com.huiyee.interact.setting.model.HyUserPrint;

public class HyUserPrintMgrImpl implements IHyUserPrintMgr {

	private IHyUserPrintDao hyUserPrintDao;

	public void setHyUserPrintDao(IHyUserPrintDao hyUserPrintDao) {
		this.hyUserPrintDao = hyUserPrintDao;
	}

	@Override
	public HyUserPrint findPrint(long owner) {
		return hyUserPrintDao.findPrint(owner);
	}

	@Override
	public int addPrint(HyUserPrint print) {
		return hyUserPrintDao.addPrint(print);
	}

	@Override
	public int updatePrint(HyUserPrint print) {
		return hyUserPrintDao.updatePrint(print);
	}

}
