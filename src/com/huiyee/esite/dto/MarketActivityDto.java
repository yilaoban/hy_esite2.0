package com.huiyee.esite.dto;

import java.util.List;

import com.huiyee.esite.model.CbActivity;


public class MarketActivityDto implements IDto
{
	private Pager pager;
	private List<CbActivity> cbActivityList;
	
	
	public List<CbActivity> getCbActivityList()
	{
		return cbActivityList;
	}
	
	public void setCbActivityList(List<CbActivity> cbActivityList)
	{
		this.cbActivityList = cbActivityList;
	}

	public Pager getPager()
	{
		return pager;
	}
	
	public void setPager(Pager pager)
	{
		this.pager = pager;
	}
	
	
}
