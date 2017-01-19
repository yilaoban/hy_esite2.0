package com.huiyee.esite.mgr.imp;

import com.huiyee.esite.dao.ICrmDao;
import com.huiyee.esite.mgr.ICrmMgr;
import com.huiyee.weixin.model.WxMediaNews;

public class CrmMgr implements ICrmMgr {

	private ICrmDao crmDao;

	public void setCrmDao(ICrmDao crmDao) {
		this.crmDao = crmDao;
	}

	@Override
	public String findCrmKey(long id) {
		return crmDao.findCrmKey(id);
	}

	@Override
	public long findMpidByAccout(String accout) {
		return crmDao.findMpidByAccout(accout);
	}

	@Override
	public WxMediaNews findNews(long id) {
		return crmDao.findNews(id);
	}

}
