package com.huiyee.interact.auction.action;

import com.huiyee.esite.action.InteractModelAction;
import com.huiyee.interact.auction.dto.AuctionRecordDto;
import com.huiyee.interact.auction.mgr.IAuctionRecordMgr;

public class AuctionDataAction extends InteractModelAction
{
	private String nickName;
	private long auid;
	private long wbuid;
	private int pageId = 1;
	private AuctionRecordDto dto;
	private IAuctionRecordMgr auctionRecordMgr;
	private String auName;
	private long mid = 10007;
	private int type = 0;

	/**
	 * 全部数据
	 */
	@Override
	public String execute() throws Exception
	{
		dto = (AuctionRecordDto) auctionRecordMgr.findAuctionJoin(auid, nickName, pageId,type);
		return SUCCESS;
	}

	public String joinDetail() throws Exception
	{
		dto = (AuctionRecordDto) auctionRecordMgr.findJoinDetail(auid, wbuid);
		return SUCCESS;
	}

	public String winList() throws Exception
	{
		dto = (AuctionRecordDto) auctionRecordMgr.findWiner(auid);
		return SUCCESS;
	}

	public String getNickName()
	{
		return nickName;
	}

	public void setNickName(String nickName)
	{
		this.nickName = nickName;
	}

	public long getAuid()
	{
		return auid;
	}

	public void setAuid(long auid)
	{
		this.auid = auid;
	}

	public long getWbuid()
	{
		return wbuid;
	}

	public void setWbuid(long wbuid)
	{
		this.wbuid = wbuid;
	}

	public int getPageId()
	{
		return pageId;
	}

	public void setPageId(int pageId)
	{
		this.pageId = pageId;
	}

	public AuctionRecordDto getDto()
	{
		return dto;
	}

	public void setDto(AuctionRecordDto dto)
	{
		this.dto = dto;
	}

	public String getAuName()
	{
		return auName;
	}

	public void setAuName(String auName)
	{
		this.auName = auName;
	}

	public void setAuctionRecordMgr(IAuctionRecordMgr auctionRecordMgr)
	{
		this.auctionRecordMgr = auctionRecordMgr;
	}

	public long getMid()
	{
		return mid;
	}

	public void setMid(long mid)
	{
		this.mid = mid;
	}

	public int getType()
	{
		return type;
	}

	public void setType(int type)
	{
		this.type = type;
	}

}
