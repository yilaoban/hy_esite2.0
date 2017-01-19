package com.huiyee.esite.dto;

import java.util.List;

import com.huiyee.esite.model.CustomVisitReport;
import com.huiyee.esite.model.Site;
import com.huiyee.esite.model.Sitegroup;

public class CustomVisitDto implements IDto {

	private List<CustomVisitReport> reports;
	private Pager pager;
	private String sitegroupname;
	private Site site;
	
	public String getSitegroupname() {
		return sitegroupname;
	}

	public void setSitegroupname(String sitegroupname) {
		this.sitegroupname = sitegroupname;
	}

	public List<CustomVisitReport> getReports() {
		return reports;
	}

	public void setReports(List<CustomVisitReport> reports) {
		this.reports = reports;
	}

	public Pager getPager() {
		return pager;
	}

	public void setPager(Pager pager) {
		this.pager = pager;
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
