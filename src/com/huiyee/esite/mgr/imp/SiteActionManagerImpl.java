package com.huiyee.esite.mgr.imp;

import java.util.List;

import com.huiyee.esite.dao.ISiteActionDao;
import com.huiyee.esite.mgr.ISiteActionManager;
import com.huiyee.esite.model.SiteCount;

public class SiteActionManagerImpl implements ISiteActionManager
{
	private ISiteActionDao siteActionDao;

	@Override
	public List<SiteCount> findSiteActionBySiteid(long sitegid,long owner)
	{
		return siteActionDao.findSiteActionBySiteid(sitegid,owner);
	}

	public void setSiteActionDao(ISiteActionDao siteActionDao)
	{
		this.siteActionDao = siteActionDao;
	}

    @Override
    public List<SiteCount> findSiteCountBySite(long siteid, long owner) 
    {
        return siteActionDao.findSiteCountBySite(siteid, owner);
    }

}
