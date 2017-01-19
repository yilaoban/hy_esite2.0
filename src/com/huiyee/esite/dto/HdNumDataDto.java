package com.huiyee.esite.dto;
import java.util.List;

import com.huiyee.esite.model.HdModel;
import com.huiyee.esite.model.Site;
public class HdNumDataDto implements IDto{
	private String  nicknames;
	private List<ReportHdNum> list;
	private Pager pager;
	private Site site;
	private List<HdModel> models;
	public String getNicknames()
	{
		return nicknames;
	}
	public void setNicknames(String nicknames)
	{
		this.nicknames = nicknames;
	}
	public List<ReportHdNum> getList()
	{
		return list;
	}
	public void setList(List<ReportHdNum> list)
	{
		this.list = list;
	}
	public Pager getPager()
	{
		return pager;
	}
	public void setPager(Pager pager)
	{
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
	public List<HdModel> getModels()
	{
		return models;
	}
	public void setModels(List<HdModel> models)
	{
		this.models = models;
	}

}
