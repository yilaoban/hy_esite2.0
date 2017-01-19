package com.huiyee.interact.sitesearch.mgr;

import com.huiyee.interact.sitesearch.model.SiteSearch;

public interface ISiteSearchMgr {

	public SiteSearch getSiteSearchByOwner(long ownerid);

	public int addSiteSearch(SiteSearch ss);

	public int updateSiteSearch(SiteSearch ss);

	public int deleteSiteSearch(long ownerid);

}
