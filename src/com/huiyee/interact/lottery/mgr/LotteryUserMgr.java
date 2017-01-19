package com.huiyee.interact.lottery.mgr;

import com.huiyee.esite.constants.IInteractConstants;
import com.huiyee.esite.mgr.imp.AbstractMgr;
import com.huiyee.interact.lottery.dao.ILotteryUserDao;
import com.huiyee.interact.lottery.dto.IDto;
import com.huiyee.interact.lottery.dto.LotteryDataDto;
import com.huiyee.interact.lottery.model.LotteryUser;
import com.huiyee.interact.lottery.model.Pager;

public class LotteryUserMgr extends AbstractMgr implements ILotteryUserMgr
{
	private ILotteryUserDao lotteryUserDao;

	public void setLotteryUserDao(ILotteryUserDao lotteryUserDao)
	{
		this.lotteryUserDao = lotteryUserDao;
	}

	@Override
	public LotteryUser findLotteryUser(long wbuid, long lid, int type)
	{
		return lotteryUserDao.findLotteryUser(wbuid, lid, type);
	}

	@Override
	public void addLotteryUser(long wbuid, long lid, int total, int used, int type)
	{
		lotteryUserDao.addLotteryUser(wbuid, lid, total, used, type);
	}

	@Override
	public IDto findLotteryData(int pageId, String nickName, long lid, long ownerid, int type)
	{
		if (type == 0)
		{
			LotteryDataDto dto = new LotteryDataDto();
			int total = lotteryUserDao.findAllLotteryUserTotal(nickName, lid, ownerid);
			if (total > 0)
			{
				dto.setUser(lotteryUserDao.findAllLotteryUser(nickName, (pageId - 1) * IInteractConstants.LOTTERY_USER_LIMIT, IInteractConstants.LOTTERY_USER_LIMIT, lid, ownerid));
			}
			dto.setPager(new Pager(pageId, total, IInteractConstants.LOTTERY_USER_LIMIT));
			return dto;
		}
		else
		{
			LotteryDataDto dto = new LotteryDataDto();
			int total = lotteryUserDao.findAllWxUserTotal(nickName, lid, ownerid,type);
			if (total > 0)
			{
				dto.setUser(lotteryUserDao.findAllWxUser(nickName, (pageId - 1) * IInteractConstants.LOTTERY_USER_LIMIT, IInteractConstants.LOTTERY_USER_LIMIT, lid, ownerid,type));
			}
			dto.setPager(new Pager(pageId, total, IInteractConstants.LOTTERY_USER_LIMIT));
			return dto;
		}
	}

	@Override
	public void updateLotteryUser(long wbuid, long lid, int total, int type)
	{
		lotteryUserDao.updateLotteryUser(wbuid, lid, total, type);	
	}

}
