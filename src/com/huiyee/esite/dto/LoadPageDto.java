package com.huiyee.esite.dto;

import java.util.List;

import com.huiyee.esite.model.Page;
import com.huiyee.esite.model.Site;
import com.huiyee.esite.model.Sitegroup;

public class LoadPageDto implements IDto{

	private Site site;
	private List<Page> pages;
	private Page page;
	
	private List<Site> sites;
	private Sitegroup sitegroup;
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

}
