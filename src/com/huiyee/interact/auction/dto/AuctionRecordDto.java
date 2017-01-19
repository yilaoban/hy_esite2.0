package com.huiyee.interact.auction.dto;

import java.io.Serializable;
import java.util.List;

import com.huiyee.interact.auction.model.AuctionRecord;
import com.huiyee.interact.auction.model.AuctionUserRecord;
import com.huiyee.interact.auction.model.Pager;

public class AuctionRecordDto implements IDto
{

	private List<AuctionRecord> list;
	private Pager pager;
	private AuctionRecord record;

	public List<AuctionRecord> getList()
	{
		return list;
	}

	public void setList(List<AuctionRecord> list)
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

	public AuctionRecord getRecord()
	{
		return record;
	}

	public void setRecord(AuctionRecord record)
	{
		this.record = record;
	}

}
