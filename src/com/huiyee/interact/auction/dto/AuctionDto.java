package com.huiyee.interact.auction.dto;

import java.util.List;

import com.huiyee.interact.auction.model.Auction;
import com.huiyee.interact.auction.model.Pager;

public class AuctionDto implements IDto
{
	private List<Auction> list;
	private Pager pager;
	private Auction auction;

	public List<Auction> getList()
	{
		return list;
	}

	public void setList(List<Auction> list)
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

	public Auction getAuction()
	{
		return auction;
	}

	public void setAuction(Auction auction)
	{
		this.auction = auction;
	}
}
