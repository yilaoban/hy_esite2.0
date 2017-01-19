package project.caidan.mgr.impl;

import java.util.List;

import com.huiyee.esite.constants.IInteractConstants;
import com.huiyee.interact.lottery.dto.IDto;
import com.huiyee.interact.lottery.dto.LotteryDto;
import com.huiyee.interact.lottery.model.Lottery;
import com.huiyee.interact.lottery.model.Pager;

import project.caidan.dao.ICaiDanLotteryDao;
import project.caidan.mgr.ICaiDanLotteryMgr;
import project.caidan.model.CDLottery;
import project.caidan.model.CDSet;
import project.caidan.model.TfGG;


public class CaiDanLotteryMgrImpl implements ICaiDanLotteryMgr
{
	private ICaiDanLotteryDao cdLotteryDao;

	public void setCdLotteryDao(ICaiDanLotteryDao cdLotteryDao)
	{
		this.cdLotteryDao = cdLotteryDao;
	}

	@Override
	public List<CDSet> findCdSetByType(String type)
	{
		return cdLotteryDao.findCdSetByType(type);
	}

	@Override
	public long saveCdLottery(long lid, String img, long enid,long owner,String htype)
	{
		int idx = cdLotteryDao.findMaxIdx(owner,htype);
		return cdLotteryDao.saveCdLottery(lid,img,enid,idx + 1);
	}

	@Override
	public IDto findAllLottery(int pageId, long owner, String type, long omid)
	{
		LotteryDto dto = new LotteryDto();
		int total = cdLotteryDao.findLotteryTotal(owner, type, omid);
		if (total > 0)
		{
			List<Lottery> list = cdLotteryDao.findAllLottery((pageId - 1) * IInteractConstants.LOTTERY_LIST_LIMIT, IInteractConstants.LOTTERY_LIST_LIMIT, owner, type, omid);
			dto.setList(list);
			dto.setPager(new Pager(pageId, total, IInteractConstants.LOTTERY_LIST_LIMIT));
		}
		return dto;
	}

	@Override
	public IDto findAllLottery(long owner,long omid,int size)
	{
		LotteryDto dto = new LotteryDto();
		if(size == 0){
			List<Lottery> list = cdLotteryDao.findAllLottery(owner, omid);
			dto.setList(list);
		}else{
			List<Lottery> list = cdLotteryDao.findAllLottery((size - 1) * IInteractConstants.LOTTERY_LIST_LIMIT, IInteractConstants.LOTTERY_LIST_LIMIT, owner, null, omid);
			dto.setList(list);
		}
		return dto;
	}
	
	@Override
	public int updateCdLottery(long lid, String img, long enid)
	{
		return cdLotteryDao.updateCdLottery(lid,img,enid);
	}

	@Override
	public CDLottery findCdLotteryById(long lid)
	{
		return cdLotteryDao.findCdLotteryById(lid);
	}

	@Override
	public int updatelotteryUp(long lid, long owner, String type)
	{
		Lottery current = cdLotteryDao.findLotteryById(lid,owner,type);
		Lottery front = cdLotteryDao.findFrontLottery(lid,owner,type);
		if (current != null && front != null)
		{
			int rs = cdLotteryDao.updateLotteryIdx(current.getId(), front.getIdx());
			int rs2 = cdLotteryDao.updateLotteryIdx(front.getId(), current.getIdx());
			return rs + rs2;
		}
		return 0;
	}

	@Override
	public int updatelotteryDown(long lid, long owner, String type)
	{
		Lottery current = cdLotteryDao.findLotteryById(lid,owner,type);
		Lottery next = cdLotteryDao.findNextLottery(lid,owner,type);
		if (current != null && next != null)
		{
			int rs = cdLotteryDao.updateLotteryIdx(current.getId(), next.getIdx());
			int rs2 = cdLotteryDao.updateLotteryIdx(next.getId(), current.getIdx());
			return rs + rs2;
		}
		return 0;
	}

	
	
}
