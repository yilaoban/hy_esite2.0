package com.huiyee.interact.sitesearch.dao;

import com.huiyee.interact.sitesearch.model.SiteSearch;

public interface ISiteSearchDao {

	public SiteSearch getSiteSearchByOwner(long ownerid);

	public int addSiteSearch(SiteSearch ss);

	public int updateSiteSearch(SiteSearch ss);

	public int deleteSiteSearch(long ownerid);

}
