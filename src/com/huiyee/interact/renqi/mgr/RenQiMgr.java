package com.huiyee.interact.renqi.mgr;

import java.util.List;

import com.huiyee.esite.constants.IInteractConstants;
import com.huiyee.interact.lottery.dao.IlotteryDao;
import com.huiyee.interact.lottery.dao.LotteryDao;
import com.huiyee.interact.lottery.model.Lottery;
import com.huiyee.interact.renqi.dao.IRenQiDao;
import com.huiyee.interact.renqi.dto.IDto;
import com.huiyee.interact.renqi.dto.RqDto;
import com.huiyee.interact.renqi.model.RenQi;

public class RenQiMgr implements IRenQiMgr
{
	private IRenQiDao renQiDao;
	private IlotteryDao lotteryDao;

	@Override
	public RenQi findRenQiById(long id)
	{
		return renQiDao.findRenQiById(id);
	}

	@Override
	public IDto findOwnerRenqi(long ownerid, int pageId)
	{
		RqDto dto = new RqDto();
		int total = renQiDao.findTotalByOwnerId(ownerid);
		if (total > 0)
		{
			List<RenQi> list = renQiDao.findRenQiListByOwner(ownerid, (pageId - 1) * IInteractConstants.INTERACT_RENQI_LIMIT, IInteractConstants.INTERACT_RENQI_LIMIT);
			dto.setList(list);
		}
		return dto;
	}

	@Override
	public long saveRenQi(RenQi rq, long ownerid)
	{
		lotteryDao.updateLotteryGuanLian(rq.getLotteryid());
		return renQiDao.saveRenQi(rq, ownerid);
	}

	@Override
	public IDto addPre(long ownerid)
	{
		RqDto dto = new RqDto();
		List<Lottery> lottery = lotteryDao.findLotteryByOwner(ownerid);
		dto.setLottery(lottery);
		return dto;
	}

	@Override
	public int updateRenQi(RenQi rq, long id, long ownerid)
	{
		lotteryDao.updateLotteryGuanLian(rq.getLotteryid());
		return renQiDao.updateRenQi(rq, id, ownerid);
	}

	@Override
	public IDto editPre(long ownerid, long id)
	{
		RqDto dto = new RqDto();
		List<Lottery> lottery = lotteryDao.findLotteryByOwner(ownerid);
		dto.setLottery(lottery);
		dto.setRenqi(renQiDao.findRenQiById(id));
		return dto;
	}
	
	@Override
	public int updateStatus(long id, String status, long owner)
	{
		return renQiDao.updateStatus(id,status,owner);
	}

	public void setRenQiDao(IRenQiDao renQiDao)
	{
		this.renQiDao = renQiDao;
	}

	public void setLotteryDao(IlotteryDao lotteryDao)
	{
		this.lotteryDao = lotteryDao;
	}

}
