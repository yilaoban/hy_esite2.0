package com.huiyee.interact.auction.service;

import com.huiyee.esite.dto.HdRsDto;
import com.huiyee.esite.model.VisitUser;
import com.huiyee.interact.auction.model.Auction;

public interface IAuctionDrawService
{
	public HdRsDto bidOneAuction(VisitUser vu ,Auction au,int addnum,long pageid,String ip,String terminal,String source);
}
