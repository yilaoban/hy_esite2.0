package com.huiyee.interact.auction.mgr;

import java.util.List;

import com.huiyee.esite.constants.IInteractConstants;
import com.huiyee.esite.model.VisitUser;
import com.huiyee.interact.auction.dao.IAuctionDao;
import com.huiyee.interact.auction.dto.AuctionDto;
import com.huiyee.interact.auction.dto.IDto;
import com.huiyee.interact.auction.model.Auction;
import com.huiyee.interact.auction.model.AuctionRecord;
import com.huiyee.interact.auction.model.Pager;

public class AuctionMgr implements IAuctionMgr
{
	private IAuctionDao auctionDao;

	public void setAuctionDao(IAuctionDao auctionDao)
	{
		this.auctionDao = auctionDao;
	}

	@Override
	public IDto findAuctionList(int pageId, long ownerid, long omid)
	{
		AuctionDto dto = new AuctionDto();
		int total = auctionDao.findAuctionTotal(ownerid, omid);
		dto.setPager(new Pager(pageId, total, IInteractConstants.AUCTION_LIST_LIMIT));
		if (total > 0)
		{
			List<Auction> list = auctionDao.findAuctionList(ownerid, (pageId - 1) * IInteractConstants.AUCTION_LIST_LIMIT, IInteractConstants.AUCTION_LIST_LIMIT, omid);
			dto.setList(list);
		}
		return dto;
	}

	@Override
	public long saveAuction(Auction auction, long omid)
	{
		return auctionDao.saveAuction(auction, omid);
	}

	@Override
	public Auction findAuctionById(long id)
	{
		return auctionDao.findAuctionById(id);
	}

	@Override
	public int updateAuction(Auction auction, long auid, long owner)
	{
		return auctionDao.updateAuction(auction, auid, owner);
	}

	@Override
	public int updateStatus(long auid, String status, long owner)
	{
		return auctionDao.updateStatus(auid,status,owner);
	}
	
	@Override
	public long saveWinnerUser(VisitUser vu,long auid, String username, String userphone, String useremail, String userlocation)
	{
		AuctionRecord record=auctionDao.findWinerRecord(auid);
		if(record!=null){
			long entity=vu.getUid();
			if(entity==record.getWbuid()){
				return auctionDao.saveWinnerUser(record.getId(),username,userphone,useremail,userlocation);
			}
		}
		return 0;
	}

	@Override
	public long addAuction(long ownerid, String title)
	{
		return auctionDao.addAuction(ownerid, title);
	}
}
