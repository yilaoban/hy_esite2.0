package com.huiyee.esite.mgr;

import java.util.List;

import com.huiyee.esite.model.SiteCount;

public interface ISiteActionManager
{
	public List<SiteCount> findSiteActionBySiteid(long sitegid,long owner);
	
	public List<SiteCount> findSiteCountBySite(long siteid,long owner);;

}
