package com.huiyee.interact.sitesearch.mgr;

import java.util.List;

import com.huiyee.interact.sitesearch.model.SiteSearchIndex;

public interface ISiteSearchIndexMgr {

	public int getSiteSearchIndexCount(long ownerid);

	public List<SiteSearchIndex> getSiteSearchIndexList(long ownerid, int start, int rows);

	public List<Long> getSitegroupidList(long ownerid, int status);

	public int addSiteSearchIndex(SiteSearchIndex ssi);

	public int updateStarttime(long id);

	public int updateStatus(long id, int status);

	public int deleteSiteSearchIndex(long id);

}
