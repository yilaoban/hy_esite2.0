
package com.huiyee.esite.dto;

import java.util.List;

import com.huiyee.esite.model.MarketingOrder;
import com.huiyee.esite.model.OrderGoods;

public class MarketingOrderDto implements IDto
{

	private List<MarketingOrder> list;
	private Pager pager;

	public List<MarketingOrder> getList()
	{
		return list;
	}

	public void setList(List<MarketingOrder> list)
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
