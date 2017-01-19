package com.huiyee.esite.fdao;

import java.util.List;

import com.huiyee.esite.model.Module;
import com.huiyee.esite.model.PageBlockRelation;
import com.huiyee.interact.auction.model.Auction;

public interface IHd126Dao {

	public long saveFeatureInteractAuction(final long pageid);
	
	public List<Auction> findInteractAuctionByOwner(long ownerid);
	
	public Module findAuctionidByFid(long fid);
	
	public int updateFeatureIneractAuction(long auctionid,long fid);
	
	public Auction findFeatureInteractAuctionById(long fid);
	
	public PageBlockRelation findPageBlockRelationByRelationid(long relationid);
	
	public int updatePageBlockRelationByRelationid(long relationid,String json);

	public List<Long> findAuctionWinner(long id);

	/**
	 * 中奖者是否已填写信息
	 * @param long1
	 * @return
	 */
	public int findAuctionSub(long  aurid);
}
