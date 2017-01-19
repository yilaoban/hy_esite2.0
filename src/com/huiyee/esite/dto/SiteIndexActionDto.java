
package com.huiyee.esite.dto;

import java.util.List;

import com.huiyee.esite.model.Activity;
import com.huiyee.esite.model.Module;
import com.huiyee.esite.model.SinaApp;
import com.huiyee.esite.model.Site;
import com.huiyee.esite.model.Sitegroup;

public class SiteIndexActionDto implements IDto
{

	private List<Site> siteList;
	private Pager pager;
	private List<Module> modules;
	private String result;
	private Site site;
	private List<Long> moduleArr;
	private List<Sitegroup> sitegroup;
	private List<SinaApp> apps;
	private List<Activity> activity;
	private Activity activityone;

	private List<Sitegroup> wSiteGroupList;// 微现场
	private List<Sitegroup> bSiteGroupList;// 微社区
	private List<Sitegroup> cSiteGroupList;// 微传播
	private List<Sitegroup> pSiteGroupList;// 微传播

	public List<Site> getSiteList()
	{
		return siteList;
	}

	public void setSiteList(List<Site> siteList)
	{
		this.siteList = siteList;
	}

	public Pager getPager()
	{
		return pager;
	}

	public void setPager(Pager pager)
	{
		this.pager = pager;
	}

	public List<Module> getModules()
	{
		return modules;
	}

	public void setModules(List<Module> modules)
	{
		this.modules = modules;
	}

	public String getResult()
	{
		return result;
	}

	public void setResult(String result)
	{
		this.result = result;
	}

	public Site getSite()
	{
		return site;
	}

	public void setSite(Site site)
	{
		this.site = site;
	}

	public List<Long> getModuleArr()
	{
		return moduleArr;
	}

	public void setModuleArr(List<Long> moduleArr)
	{
		this.moduleArr = moduleArr;
	}

	public List<Sitegroup> getSitegroup()
	{
		return sitegroup;
	}

	public void setSitegroup(List<Sitegroup> sitegroup)
	{
		this.sitegroup = sitegroup;
	}

	public List<SinaApp> getApps()
	{
		return apps;
	}

	public void setApps(List<SinaApp> apps)
	{
		this.apps = apps;
	}

	public List<Activity> getActivity()
	{
		return activity;
	}

	public void setActivity(List<Activity> activity)
	{
		this.activity = activity;
	}

	public Activity getActivityone()
	{
		return activityone;
	}

	public void setActivityone(Activity activityone)
	{
		this.activityone = activityone;
	}

	public List<Sitegroup> getWSiteGroupList()
	{
		return wSiteGroupList;
	}

	public void setWSiteGroupList(List<Sitegroup> siteGroupList)
	{
		wSiteGroupList = siteGroupList;
	}

	public List<Sitegroup> getBSiteGroupList()
	{
		return bSiteGroupList;
	}

	public void setBSiteGroupList(List<Sitegroup> siteGroupList)
	{
		bSiteGroupList = siteGroupList;
	}

	public List<Sitegroup> getCSiteGroupList()
	{
		return cSiteGroupList;
	}

	public void setCSiteGroupList(List<Sitegroup> siteGroupList)
	{
		cSiteGroupList = siteGroupList;
	}

	public List<Sitegroup> getPSiteGroupList()
	{
		return pSiteGroupList;
	}

	public void setPSiteGroupList(List<Sitegroup> pSiteGroupList)
	{
		this.pSiteGroupList = pSiteGroupList;
	}

	public List<Sitegroup> getcSiteGroupList()
	{
		return cSiteGroupList;
	}

	public void setcSiteGroupList(List<Sitegroup> cSiteGroupList)
	{
		this.cSiteGroupList = cSiteGroupList;
	}

	public List<Sitegroup> getwSiteGroupList()
	{
		return wSiteGroupList;
	}

	public void setwSiteGroupList(List<Sitegroup> wSiteGroupList)
	{
		this.wSiteGroupList = wSiteGroupList;
	}

	public List<Sitegroup> getbSiteGroupList()
	{
		return bSiteGroupList;
	}

	public void setbSiteGroupList(List<Sitegroup> bSiteGroupList)
	{
		this.bSiteGroupList = bSiteGroupList;
	}

}
