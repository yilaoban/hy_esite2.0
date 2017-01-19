package com.huiyee.interact.auction.mgr;

import java.util.Date;
import java.util.List;

import com.huiyee.esite.constants.IInteractConstants;
import com.huiyee.esite.dao.ISinaUserDao;
import com.huiyee.esite.dao.IWeiXinDao;
import com.huiyee.esite.mgr.imp.AbstractMgr;
import com.huiyee.interact.auction.dao.IAuctionDao;
import com.huiyee.interact.auction.dao.IAuctionRecordDao;
import com.huiyee.interact.auction.dto.AuctionRecordDto;
import com.huiyee.interact.auction.dto.IDto;
import com.huiyee.interact.auction.model.AuctionRecord;
import com.huiyee.interact.auction.model.AuctionUserRecord;
import com.huiyee.interact.auction.model.Pager;

public class AuctionRecordMgr extends AbstractMgr implements IAuctionRecordMgr
{
	private IAuctionRecordDao auctionRecordDao;
	private IAuctionDao auctionDao;
	private ISinaUserDao sinaUserDao;
	private IWeiXinDao weiXinDao;

	public void setAuctionRecordDao(IAuctionRecordDao auctionRecordDao)
	{
		this.auctionRecordDao = auctionRecordDao;
	}

	@Override
	public IDto findAuctionJoin(long auid, String nickName, int pageId, int type)
	{
		AuctionRecordDto dto = new AuctionRecordDto();
		int total = auctionRecordDao.findAuctionJoinTotal(auid, type);
		dto.setPager(new Pager(pageId, total, IInteractConstants.AUCTION_RECORD_LIMIT));
		if (total > 0)
		{
			List<AuctionRecord> list = auctionRecordDao.findAuctionJoin(auid, nickName, (pageId - 1) * IInteractConstants.AUCTION_RECORD_LIMIT, IInteractConstants.AUCTION_RECORD_LIMIT,type);
			dto.setList(list);
			if (list != null)
			{
				if (type == 0)
				{
					for (AuctionRecord record : list)
					{
						record.setNickName(sinaUserDao.findNickNameById(record.getWbuid()));
					}

				}
				else if (type == 1)
				{
					for (AuctionRecord record : list)
					{
						record.setNickName(weiXinDao.findNickNameById(record.getWbuid()));
					}
				}

			}

		}
		return dto;
	}

	@Override
	public void addAuctionRecord(long ownerwbuid, long wbuid, int utype, long auid, long pageid, int bidnum, String ip, String terminal, String source)
	{
		unpass(auid, utype);
		long urid = auctionRecordDao.addAuctionRecord(ownerwbuid,wbuid, utype, auid, pageid, bidnum, ip, terminal, source);
		this.updatePreUsedByUser(wbuid, ownerwbuid, utype, bidnum,"竞拍消耗");
		auctionDao.updateUrid(auid, urid, bidnum);
	}

	@Override
	public void addAuctionRecord(long ownerwbuid, long wbuid, int utype, long auid, long pageid, int bidnum, String ip, String terminal, String source, Date proendtime)
	{
		// 需要延迟的竞拍,把原来冻结的积分还原.重新冻结积分.
		unpass(auid, utype);
		long urid = auctionRecordDao.addAuctionRecord(ownerwbuid,wbuid, utype, auid, pageid, bidnum, ip, terminal, source);
		this.updatePreUsedByUser(wbuid, ownerwbuid, utype, bidnum,"竞拍消耗");
		auctionDao.updateProEndtime(auid, urid, bidnum, proendtime);
	}

	private void unpass(long auid, int utype)
	{
		List<AuctionRecord> ls = auctionRecordDao.findAuctionPssed(auid, utype);
		if (ls != null && ls.size() > 0)
		{
			for (AuctionRecord ar : ls)
			{
				this.updatePreUsedByUser(ar.getWbuid(), ar.getOwnerwbuid(), ar.getType(), -ar.getBidnum(),"竞拍失败返还");
				auctionRecordDao.updatePssed(ar.getId());
			}
		}
	}

	@Override
	public AuctionUserRecord findRecord(long auid, long wbuid, int utype)
	{
		return auctionRecordDao.findRecord(auid, wbuid, utype);
	}

	@Override
	public IDto findJoinDetail(long auid, long wbuid)
	{
		AuctionRecordDto dto = new AuctionRecordDto();
		dto.setList(auctionRecordDao.findJoinDetail(auid, wbuid));
		return dto;
	}

	@Override
	public AuctionRecordDto findWiner(long auid)
	{
		AuctionRecordDto dto = new AuctionRecordDto();
		dto.setRecord(auctionRecordDao.findAuctionWinner(auid));
		return dto;
	}

	public void setAuctionDao(IAuctionDao auctionDao)
	{
		this.auctionDao = auctionDao;
	}

	public void setSinaUserDao(ISinaUserDao sinaUserDao)
	{
		this.sinaUserDao = sinaUserDao;
	}

	public void setWeiXinDao(IWeiXinDao weiXinDao)
	{
		this.weiXinDao = weiXinDao;
	}
}
