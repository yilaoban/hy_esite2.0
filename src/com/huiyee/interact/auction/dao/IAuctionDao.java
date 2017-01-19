package com.huiyee.interact.auction.dao;

import java.util.List;
import com.huiyee.interact.auction.model.Auction;
import com.huiyee.interact.auction.model.AuctionRecord;

import java.util.Date;

public interface IAuctionDao
{
	public Auction findAuctionById(long id);

	public void updateProEndtime(long id, long urid,int addnum,Date proendtime);
	
	public void updateUrid(long id, long urid,int addnum);

	public int findAuctionTotal(long ownerid, long omid);

	public List<Auction> findAuctionList(long ownerid, int auctionListLimit, int auctionListLimit2, long omid);

	public long saveAuction(Auction auction, long omid);

	public int updateAuction(Auction auction, long auid, long owner);

	public int updateStatus(long auid, String status, long owner);

	public long saveWinnerUser(long aurid, String username, String userphone, String useremail, String userlocation);

	public AuctionRecord findWinerRecord(long auid);
	
	public long addAuction(long ownerid,String title);

}
