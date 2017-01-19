package com.huiyee.esite.dto;

import java.util.List;

public class ReportRecordDto implements IDto
{
	 private List<SolrRecord> list;
	 private Pager pager;
	public List<SolrRecord> getList()
	{
		return list;
	}
	public void setList(List<SolrRecord> list)
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
