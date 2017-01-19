package com.huiyee.interact.lottery.mgr;

import java.util.List;

import com.huiyee.interact.lottery.dao.IWxHbSendDao;
import com.huiyee.interact.lottery.dto.IDto;
import com.huiyee.interact.lottery.dto.LotteryDataDto;
import com.huiyee.interact.lottery.model.Pager;
import com.huiyee.interact.lottery.model.WxHbLotterySend;


public class WxHbSendMgrImpl implements IWxHbSendMgr
{
	private IWxHbSendDao wxHbSendDao;
	
	public void setWxHbSendDao(IWxHbSendDao wxHbSendDao)
	{
		this.wxHbSendDao = wxHbSendDao;
	}

	@Override
	public IDto findWxHbSendList(long lpid, Integer errcode, int pageId)
	{
		LotteryDataDto dto = new LotteryDataDto();
		int rows = 10;
		int start = (pageId - 1) * rows;
		int total = wxHbSendDao.findWxHbSendTotal(lpid, errcode);
		if(total > 0){
			List<WxHbLotterySend> sends = wxHbSendDao.findWxHbSendList(lpid, errcode, start, rows);
			dto.setSends(sends);
		}
		dto.setPager(new Pager(pageId, total, rows));
		return dto;
	}

	@Override
	public int addToResend(long rid)
	{
		return wxHbSendDao.addToResend(rid);
	}

	@Override
	public int updateSent(long id, String sent)
	{
		return wxHbSendDao.updateSent(id, sent);
	}

}
