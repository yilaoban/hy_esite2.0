package com.huiyee.interact.ad.dto;

import java.util.List;

import com.huiyee.esite.dto.Pager;
import com.huiyee.interact.ad.model.Adgg;

public class AdGGDto implements IDto
{
	public Pager pager;
	public List<Adgg> adGGList;
	
	public Pager getPager()
	{
		return pager;
	}
	
	public void setPager(Pager pager)
	{
		this.pager = pager;
	}
	
	public List<Adgg> getAdGGList()
	{
		return adGGList;
	}
	
	public void setAdGGList(List<Adgg> adGGList)
	{
		this.adGGList = adGGList;
	}
	
}
