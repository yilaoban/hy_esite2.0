package com.huiyee.esite.dao;

import java.util.List;

import com.huiyee.esite.model.SiteCount;

public interface ISiteActionDao
{

	public List<SiteCount> findSiteActionBySiteid(long sitegid, long owner);
	
	public List<SiteCount> findSiteCountBySite(long siteid,long owner);

}
