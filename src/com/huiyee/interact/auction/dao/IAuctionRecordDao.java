package com.huiyee.interact.auction.dao;

import java.util.List;

import com.huiyee.interact.auction.model.AuctionRecord;

import com.huiyee.interact.auction.model.AuctionUserRecord;

public interface IAuctionRecordDao
{
	public long addAuctionRecord(long ownerwbuid,long wbuid,int utype, long auid, long pageid, int bidnum, String ip, String terminal, String source);

	public int findAuctionJoinTotal(long auid, int type);
	
	public void updatePssed(long id);

	public List<AuctionRecord> findAuctionJoin(long auid, String nickName, int i, int auctionRecordLimit, int type);
	
	public List<AuctionRecord> findAuctionPssed(long auid, int utype);

	public AuctionUserRecord findRecord(long auid, long wbuid,int utype);

	public List<AuctionRecord> findJoinDetail(long auid, long wbuid);

	public AuctionRecord findAuctionWinner(long auid);
}
