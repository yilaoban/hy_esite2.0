
package com.huiyee.esite.dto;

import com.huiyee.esite.model.Site;
import com.huiyee.esite.model.Sitegroup;

public class DaohangDto implements IDto
{

	private Sitegroup sitegroup;
	private Site site;
	private String title;

	public Sitegroup getSitegroup()
	{
		return sitegroup;
	}

	public void setSitegroup(Sitegroup sitegroup)
	{
		this.sitegroup = sitegroup;
	}

	public Site getSite()
	{
		return site;
	}

	public void setSite(Site site)
	{
		this.site = site;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}
}
