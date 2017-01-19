package com.huiyee.interact.servicerpj.mgr;

import com.huiyee.interact.servicerpj.dao.IServicerPjPageDao;
import com.huiyee.interact.servicerpj.model.ServicerPjPage;

public class ServicerPjPageMgrImpl implements IServicerPjPageMgr {

	private IServicerPjPageDao servicerPjPageDao;

	public void setServicerPjPageDao(IServicerPjPageDao servicerPjPageDao) {
		this.servicerPjPageDao = servicerPjPageDao;
	}

	@Override
	public ServicerPjPage findServicerPjPage(long owner, int type) {
		return servicerPjPageDao.findServicerPjPage(owner, type);
	}

}
