package com.huiyee.interact.auction.mgr;

import com.huiyee.interact.auction.dto.IDto;

import java.util.Date;

import com.huiyee.interact.auction.model.AuctionUserRecord;

public interface IAuctionRecordMgr
{
    public void addAuctionRecord(long ownerwbuid,long wbuid,int utype, long auid, long pageid,int bidnum, String ip, String terminal, String source);
   
    public void addAuctionRecord(long ownerwbuid,long wbuid,int utype, long auid, long pageid,int bidnum, String ip, String terminal, String source,Date proendtime);
   
    public AuctionUserRecord findRecord(long auid,long wbuid,int utype);
  
	public IDto findAuctionJoin(long auid, String nickName, int pageId, int type);

	public IDto findJoinDetail(long auid, long wbuid);

	public IDto findWiner(long auid);

}
