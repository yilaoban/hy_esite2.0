package com.huiyee.interact.auction.service;

import com.huiyee.interact.auction.mgr.IAuctionMgr;
import com.huiyee.interact.auction.mgr.IAuctionRecordMgr;

public class AuctionResultJob
{
	private IAuctionMgr auctionMgr;
	private IAuctionRecordMgr auctionRecordMgr;
	public void setAuctionMgr(IAuctionMgr auctionMgr)
	{
		this.auctionMgr = auctionMgr;
	}
	public void setAuctionRecordMgr(IAuctionRecordMgr auctionRecordMgr)
	{
		this.auctionRecordMgr = auctionRecordMgr;
	}
	public void dowork()
	{

	}
}
