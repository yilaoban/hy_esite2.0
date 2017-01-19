package com.huiyee.esite.dto;

import java.util.List;
import com.huiyee.esite.model.ZanDetail;
public class ZanDetailDto implements IDto {

	private List<ZanDetail> list;
	private Pager pager;
	public List<ZanDetail> getList()
	{
		return list;
	}
	public void setList(List<ZanDetail> list)
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
