package com.huiyee.esite.dto;

import java.util.List;

import com.huiyee.esite.model.Page;
import com.huiyee.esite.model.Site;
import com.huiyee.esite.model.Sitegroup;
import com.huiyee.esite.model.TemplateModel;

public class PageActionDto implements IDto{

	private Site site;
	private List<Page> pages;
	private Page page;
	private Pager pager;
	private List<Site> sites;
	private Sitegroup sitegroup;
	private List<TemplateModel> list;
	

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public List<Page> getPages() {
		return pages;
	}

	public void setPages(List<Page> pages) {
		this.pages = pages;
	}

	public Site getSite() {
		return site;
	}

	public void setSite(Site site) {
		this.site = site;
	}

	public List<Site> getSites() {
		return sites;
	}

	public void setSites(List<Site> sites) {
		this.sites = sites;
	}

	public Sitegroup getSitegroup() {
		return sitegroup;
	}

	public void setSitegroup(Sitegroup sitegroup) {
		this.sitegroup = sitegroup;
	}

    public Pager getPager() {
        return pager;
    }

    public void setPager(Pager pager) {
        this.pager = pager;
    }

	public List<TemplateModel> getList()
	{
		return list;
	}

	public void setList(List<TemplateModel> list)
	{
		this.list = list;
	}

}
