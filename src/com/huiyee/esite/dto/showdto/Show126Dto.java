package com.huiyee.esite.dto.showdto;

import java.io.Serializable;

import com.huiyee.esite.dto.IDto;
import com.huiyee.interact.auction.model.Auction;

public class Show126Dto implements IDto, Serializable {

	private static final long serialVersionUID = 8580431324477519722L;
	
	private long fid;
	private Auction auction;
	
	public long getFid()
	{
		return fid;
	}
	public void setFid(long fid)
	{
		this.fid = fid;
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
