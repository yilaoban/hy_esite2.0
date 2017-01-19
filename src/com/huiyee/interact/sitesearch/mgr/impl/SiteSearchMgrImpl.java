package com.huiyee.interact.sitesearch.mgr.impl;

import com.huiyee.interact.sitesearch.dao.ISiteSearchDao;
import com.huiyee.interact.sitesearch.mgr.ISiteSearchMgr;
import com.huiyee.interact.sitesearch.model.SiteSearch;

public class SiteSearchMgrImpl implements ISiteSearchMgr {

	private ISiteSearchDao siteSearchDao;

	public void setSiteSearchDao(ISiteSearchDao siteSearchDao) {
		this.siteSearchDao = siteSearchDao;
	}

	@Override
	public SiteSearch getSiteSearchByOwner(long ownerid) {
		return siteSearchDao.getSiteSearchByOwner(ownerid);
	}

	@Override
	public int addSiteSearch(SiteSearch ss) {
		return siteSearchDao.addSiteSearch(ss);
	}

	@Override
	public int updateSiteSearch(SiteSearch ss) {
		return siteSearchDao.updateSiteSearch(ss);
	}

	@Override
	public int deleteSiteSearch(long ownerid) {
		return siteSearchDao.deleteSiteSearch(ownerid);
	}

}
