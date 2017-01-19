package com.huiyee.interact.auction.mgr;

import com.huiyee.esite.model.VisitUser;
import com.huiyee.interact.auction.dto.IDto;
import com.huiyee.interact.auction.model.Auction;

public interface IAuctionMgr
{
    public Auction findAuctionById(long id);
   
	public IDto findAuctionList(int pageId, long ownerid, long omid);

	public long saveAuction(Auction auction, long omid);

	public int updateAuction(Auction auction, long auid, long ownerid);

	public int updateStatus(long auid, String string, long id);

	/*
	 * 中表人填写联系信息
	 */
	public long saveWinnerUser(VisitUser vu, long auid, String username, String userphone, String useremail, String userlocation);
	
	public long addAuction(long ownerid,String title);

}
