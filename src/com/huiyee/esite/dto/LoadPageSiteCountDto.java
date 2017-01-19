package com.huiyee.esite.dto;

import java.util.List;

import com.huiyee.esite.model.Site;
import com.huiyee.esite.model.SiteCount;
public class LoadPageSiteCountDto implements IDto
{
	private Site site;
	private List<SiteCount> siteCountList;
	private Pager pager;


	public List<SiteCount> getSiteCountList()
	{
		return siteCountList;
	}

	public void setSiteCountList(List<SiteCount> siteCountList)
	{
		this.siteCountList = siteCountList;
	}

	public Pager getPager()
	{
		return pager;
	}

	public void setPager(Pager pager)
	{
		this.pager = pager;
	}

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }
}
