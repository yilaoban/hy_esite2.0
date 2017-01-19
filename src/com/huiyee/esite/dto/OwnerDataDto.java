package com.huiyee.esite.dto;

import java.util.List;

public class OwnerDataDto implements IDto {
	private List<Integer> vwbdaynums;
	private List<Integer> vwxdaynums;
	private List<Integer> hdaynums;
	private List<String> dates;
	private List<PageDto> list;
	private Pager pager;
	
	public List<Integer> getVwbdaynums()
	{
		return vwbdaynums;
	}
	public void setVwbdaynums(List<Integer> vwbdaynums)
	{
		this.vwbdaynums = vwbdaynums;
	}
	public List<Integer> getVwxdaynums()
	{
		return vwxdaynums;
	}
	public void setVwxdaynums(List<Integer> vwxdaynums)
	{
		this.vwxdaynums = vwxdaynums;
	}
	public List<Integer> getHdaynums()
	{
		return hdaynums;
	}
	public void setHdaynums(List<Integer> hdaynums)
	{
		this.hdaynums = hdaynums;
	}
	public List<String> getDates()
	{
		return dates;
	}
	public void setDates(List<String> dates)
	{
		this.dates = dates;
	}
	public Pager getPager()
	{
		return pager;
	}
	public void setPager(Pager pager)
	{
		this.pager = pager;
	}
	public List<PageDto> getList()
	{
		return list;
	}
	public void setList(List<PageDto> list)
	{
		this.list = list;
	}
	
}
