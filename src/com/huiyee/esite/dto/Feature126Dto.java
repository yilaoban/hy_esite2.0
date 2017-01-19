package com.huiyee.esite.dto;

import java.io.Serializable;
import java.util.List;
import com.huiyee.interact.auction.model.Auction;

public class Feature126Dto implements IDto , Serializable{

	private static final long serialVersionUID = 4998940590086646696L;

	private List<Auction> auctionList;
	private long auctionid;
	private long fid;
	
	private long relationid;
	private String type;
	private long pageid;
	
	public String getType()
	{
		return type;
	}
	public void setType(String type)
	{
		this.type = type;
	}
	public long getPageid()
	{
		return pageid;
	}
	public void setPageid(long pageid)
	{
		this.pageid = pageid;
	}
	public long getRelationid()
	{
		return relationid;
	}
	public void setRelationid(long relationid)
	{
		this.relationid = relationid;
	}
	public List<Auction> getAuctionList()
	{
		return auctionList;
	}
	public void setAuctionList(List<Auction> auctionList)
	{
		this.auctionList = auctionList;
	}
	public long getAuctionid()
	{
		return auctionid;
	}
	public void setAuctionid(long auctionid)
	{
		this.auctionid = auctionid;
	}
	public long getFid()
	{
		return fid;
	}
	public void setFid(long fid)
	{
		this.fid = fid;
	}
	
	
}
