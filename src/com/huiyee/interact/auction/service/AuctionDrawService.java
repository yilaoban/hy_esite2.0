package com.huiyee.interact.auction.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.huiyee.esite.dto.HdRsDto;
import com.huiyee.esite.model.VisitUser;
import com.huiyee.interact.auction.mgr.IAuctionRecordMgr;
import com.huiyee.interact.auction.model.Auction;

public class AuctionDrawService implements IAuctionDrawService
{
	private IAuctionRecordMgr auctionRecordMgr;
	private static Map<String, String> uids = new HashMap<String, String>();
	private static Map<Long, String> auids = new HashMap<Long, String>();

	public void setAuctionRecordMgr(IAuctionRecordMgr auctionRecordMgr)
	{
		this.auctionRecordMgr = auctionRecordMgr;
	}

	public static String getUids(String uid)
	{
		String str = uids.get(uid);
		if (str == null)
		{
			str = new String("");
			uids.put(uid, str);
		}
		return str;

	}

	public static String getAuids(long auid)
	{
		String str = auids.get(auid);
		if (str == null)
		{
			str = new String("");
			auids.put(auid, str);
		}
		return str;

	}

	@Override
	public HdRsDto bidOneAuction(VisitUser vu, Auction au, int addnum, long pageid, String ip, String terminal, String source)
	{
		HdRsDto rs = new HdRsDto();
		long uid = vu.getUid();
		int type = vu.getCd();
		long ownerwbuid = 0;
		if (vu.getWxuid() > 0)
		{
			ownerwbuid = vu.getWxUser().getMpid();
		}
		else
		{
			ownerwbuid = vu.getCid();
		}
		synchronized (getAuids(au.getId()))
		{
			synchronized (getUids(uid + "," + type))
			{
				if (au.getProendTime().getTime() > System.currentTimeMillis())
				{
					if (addnum > au.getCurrentmax())
					{
						//int jf = ownerBalanceMgr.findJFByUser(vu);
						if (0 >= addnum)
						{
							if (au.getAddsecond() > 0 && au.getProendTime().getTime() <= (System.currentTimeMillis() + au.getLastsecond() * 1000))
							{
								auctionRecordMgr.addAuctionRecord(ownerwbuid,uid, type, au.getId(), pageid, addnum, ip, terminal, source, new Date(System.currentTimeMillis() + au.getAddsecond() * 1000));
							}
							else
							{
								auctionRecordMgr.addAuctionRecord(ownerwbuid,uid, type, au.getId(), pageid, addnum, ip, terminal, source);
							}
							rs.setStatus(1);
							rs.setHydesc("竞拍成功");
						}
						else
						{
							rs.setStatus(-1);
							rs.setHydesc("积分不够");
						}
					}
					else
					{
						rs.setStatus(-2);
						rs.setHydesc("竞拍价格必须大于当前最高竞拍价格");
					}
				}
				else
				{
					rs.setStatus(-3);
					rs.setHydesc("竞拍时间结束");
				}
			}
		}
		return rs;
	}

}
