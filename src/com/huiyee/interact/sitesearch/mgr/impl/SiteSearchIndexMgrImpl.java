package com.huiyee.interact.sitesearch.mgr.impl;

import java.util.List;

import com.huiyee.interact.sitesearch.dao.ISiteSearchIndexDao;
import com.huiyee.interact.sitesearch.mgr.ISiteSearchIndexMgr;
import com.huiyee.interact.sitesearch.model.SiteSearchIndex;

public class SiteSearchIndexMgrImpl implements ISiteSearchIndexMgr {

	private ISiteSearchIndexDao siteSearchIndexDao;

	public void setSiteSearchIndexDao(ISiteSearchIndexDao siteSearchIndexDao) {
		this.siteSearchIndexDao = siteSearchIndexDao;
	}

	@Override
	public int getSiteSearchIndexCount(long ownerid) {
		return siteSearchIndexDao.getSiteSearchIndexCount(ownerid);
	}

	@Override
	public List<SiteSearchIndex> getSiteSearchIndexList(long ownerid, int start, int rows) {
		return siteSearchIndexDao.getSiteSearchIndexList(ownerid, start, rows);
	}

	@Override
	public List<Long> getSitegroupidList(long ownerid, int status) {
		return siteSearchIndexDao.getSitegroupidList(ownerid, status);
	}

	@Override
	public int addSiteSearchIndex(SiteSearchIndex ssi) {
		return siteSearchIndexDao.addSiteSearchIndex(ssi);
	}

	@Override
	public int updateStarttime(long id) {
		return siteSearchIndexDao.updateStarttime(id);
	}

	@Override
	public int updateStatus(long id, int status) {
		return siteSearchIndexDao.updateStatus(id, status);
	}

	@Override
	public int deleteSiteSearchIndex(long id) {
		return siteSearchIndexDao.deleteSiteSearchIndex(id);
	}

}
