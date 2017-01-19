package com.huiyee.esite.dto;

import java.util.List;


import com.huiyee.esite.model.Site;
import com.huiyee.esite.model.Sitegroup;
import com.huiyee.esite.model.WeiXinPage;

public class PageConfigDto implements IDto {

	
	private List<Site> sites;
	private Site site;
	private Sitegroup sitegroup;
	
	public Sitegroup getSitegroup()
	{
		return sitegroup;
	}
	
	public void setSitegroup(Sitegroup sitegroup)
	{
		this.sitegroup = sitegroup;
	}
	public List<Site> getSites() {
		return sites;
	}
	public void setSites(List<Site> sites) {
		this.sites = sites;
	}
	public Site getSite()
	{
		return site;
	}
	public void setSite(Site site)
	{
		this.site = site;
	}
	
	
	
	
}
