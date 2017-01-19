package com.huiyee.esite.dto;

import com.huiyee.esite.model.AreaAnalysis;
import java.util.List;
public class HdAreaDto implements IDto{
	private List<AreaAnalysis> list;
	private Pager pager;
	public List<AreaAnalysis> getList()
	{
		return list;
	}
	public void setList(List<AreaAnalysis> list)
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
}
