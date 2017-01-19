package com.huiyee.esite.dto;

import java.util.List;

import com.huiyee.esite.model.Page;
import com.huiyee.esite.model.PageFeature;

public class PersonalTailorDto implements IDto{

	private List<PageFeature> list;
	private Pager pager;
	private String pagename;

	public List<PageFeature> getList()
	{
		return list;
	}

	public void setList(List<PageFeature> list)
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

	public String getPagename()
	{
		return pagename;
	}

	public void setPagename(String pagename)
	{
		this.pagename = pagename;
	}


	
}
