package com.huiyee.interact.sitesearch.mgr.impl;

import java.util.List;

import com.huiyee.esite.model.Sitegroup;
import com.huiyee.interact.sitesearch.dao.ISiteGroupDao;
import com.huiyee.interact.sitesearch.mgr.ISiteGroupMgr;

public class SiteGroupMgrImpl implements ISiteGroupMgr {

	private ISiteGroupDao siteGroupDao;

	public void setSiteGroupDao(ISiteGroupDao siteGroupDao) {
		this.siteGroupDao = siteGroupDao;
	}

	@Override
	public int getSiteGroupCount(long ownerid, List<String> types) {
		return siteGroupDao.getSiteGroupCount(ownerid, types);
	}

	@Override
	public List<Sitegroup> getSiteGroupList(long ownerid, List<String> types, int start, int rows) {
		return siteGroupDao.getSiteGroupList(ownerid, types, start, rows);
	}

}
