package com.huiyee.interact.lottery.mgr;

import java.util.List;

import com.huiyee.esite.constants.IInteractConstants;
import com.huiyee.esite.dao.IContentProductDao;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.model.ContentProduct;
import com.huiyee.interact.lottery.dao.ILotteryPrizeCodeDao;
import com.huiyee.interact.lottery.dao.ILotteryPrizeDao;
import com.huiyee.interact.lottery.dto.IDto;
import com.huiyee.interact.lottery.dto.LotteryDataDto;
import com.huiyee.interact.lottery.dto.PrizeDto;
import com.huiyee.interact.lottery.model.LotteryLose;
import com.huiyee.interact.lottery.model.LotteryPrize;
import com.huiyee.interact.lottery.model.LotteryPrizeCode;
import com.huiyee.interact.lottery.model.Pager;

public class LotteryPrizeMgr implements ILotteryPrizeMgr
{
	private ILotteryPrizeDao lotteryPrizeDao;
	private ILotteryPrizeCodeDao lotteryPrizeCodeDao;
	private IContentProductDao contentProductDao;

	@Override
	public IDto findLotteryPrize(long lid, int pageId)
	{
		LotteryDataDto dto = new LotteryDataDto();
		int total = lotteryPrizeDao.findLotteryprizeTotal(lid);
		if (total > 0)
		{
			List<LotteryPrize> list = lotteryPrizeDao.findPrizeList((pageId - 1) * IInteractConstants.LOTTERY_LIST_LIMIT, IInteractConstants.LOTTERY_LIST_LIMIT, lid);
			dto.setPrize(list);
		}
		dto.setPager(new Pager(pageId, total, IInteractConstants.LOTTERY_LIST_LIMIT));
		return dto;
	}
	
	
	public void setLotteryPrizeDao(ILotteryPrizeDao lotteryPrizeDao)
	{
		this.lotteryPrizeDao = lotteryPrizeDao;
	}

	
	public void setContentProductDao(IContentProductDao contentProductDao)
	{
		this.contentProductDao = contentProductDao;
	}

	@Override
	public IDto findprizeList(long lid, int pageId)
	{
		PrizeDto prdto = new PrizeDto();
		int total = lotteryPrizeDao.findLotteryprizeTotal(lid);
		if (total > 0)
		{
			List<LotteryPrize> list = lotteryPrizeDao.findPrizeList((pageId - 1) * IInteractConstants.LOTTERYPRIZE_LIST_LIMIT, IInteractConstants.LOTTERYPRIZE_LIST_LIMIT, lid);
			prdto.setPrizelist(list);
			prdto.setPager(new Pager(pageId, total, IInteractConstants.LOTTERYPRIZE_LIST_LIMIT));
		}
		return prdto;
	}

	@Override
	public long addprize(LotteryPrize lp)
	{
		int result = 0;
		long id = lotteryPrizeDao.saveprice(lp);
		if (id > 0)
		{
			result = 1;
		}
		return result;
	}

	@Override
	public long delprize(long id)
	{
		int result = 0;
		long total = lotteryPrizeDao.delprice(id);
		if (total > 0)
		{
			result = 1;
		}
		return result;
	}
 
	@Override
	public List<LotteryPrize> findLotteryNPrize(long lid)
	{
		return lotteryPrizeDao.findLotteryNPrize(lid);
	}
	
	@Override
	public List<LotteryPrize> findLotteryYPrize(long lid)
	{
		return lotteryPrizeDao.findLotteryYPrize(lid);
	}

	@Override
	public long updateprize(LotteryPrize lp)
	{
		int result = 0;
		long size = lotteryPrizeDao.updateprice(lp);
		if (size > 0)
		{
			result = 1;
		}
		return result;
	}

	@Override
	public void updateUsed(long lpid)
	{
		lotteryPrizeDao.updateUsed(lpid);
	}

	@Override
	public LotteryPrize findPrizeByLpid(long id)
	{
		return lotteryPrizeDao.findPrizeByLpid(id);
	}

	@Override
	public int addLotteryPrizeCode(List<LotteryPrizeCode> list, long id)
	{
		int rs = lotteryPrizeCodeDao.addCouponsCode(list);
		int count = lotteryPrizeCodeDao.findCodeTotal(id);
		lotteryPrizeDao.updateTotal(count, id);
		return rs;
	}

	public void setLotteryPrizeCodeDao(ILotteryPrizeCodeDao lotteryPrizeCodeDao)
	{
		this.lotteryPrizeCodeDao = lotteryPrizeCodeDao;
	}

	@Override
	public IDto findLotteryCodeListByLpid(long lpid, int pageId)
	{
		PrizeDto dto = new PrizeDto();
		int total = lotteryPrizeCodeDao.findLotteryPrizeCodeTotal(lpid);
		if (total > 0)
		{
			List<LotteryPrizeCode> prizeCodeList = lotteryPrizeCodeDao.findLotteryCodeListByLpid(lpid, (pageId - 1) * IInteractConstants.LOTTERY_LIST_LIMIT, IInteractConstants.LOTTERY_LIST_LIMIT);
			dto.setPrizeCodeList(prizeCodeList);
			dto.setPager(new Pager(pageId, total, IInteractConstants.LOTTERY_LIST_LIMIT));
		}
		return dto;
	}
	
	@Override
	public IDto findLotteryCodeByLpidAndCode(long lpid, String code)
	{
		PrizeDto dto = new PrizeDto();
		List<LotteryPrizeCode> prizeCodeList = lotteryPrizeCodeDao.findLotteryCodeByLpidAndCode(lpid,code);
		dto.setPrizeCodeList(prizeCodeList);
		return dto;
	}
	
	@Override
	public int findLotteryTotalPrizeById(long lid)
	{
		int total = 0;
		List<LotteryPrize> list = lotteryPrizeDao.findLotteryTotalPrizeById(lid);
		if(list!= null && list.size()>0){
			for(int i=0;i<list.size();i++){
				total = total + list.get(i).getHprice() * list.get(i).getTotal();
			}
		}
		return total;
	}
	
	@Override
	public List<ContentProduct> findProductByTypeZ(Account account,String subtype)
	{
		return contentProductDao.findProductBySubtype(account.getOwner().getId(),subtype);
	}

	@Override
	public LotteryLose findLose(long uid, int type, long lid)
	{
		return lotteryPrizeDao.findLose(uid, type, lid);
	}

	@Override
	public void updateLose(long id)
	{
		lotteryPrizeDao.updateLose(id);
	}

	@Override
	public void updateWinLose(long id)
	{
		lotteryPrizeDao.updateWinLose(id);
	}

	@Override
	public void addLose(long uid, int type, long lid)
	{
		lotteryPrizeDao.addLose(uid, type, lid);
	}

	@Override
	public List<LotteryPrize> findLotteryPrize(long lid)
	{
		return lotteryPrizeDao.findLotteryPrize(lid);
	}


	@Override
	public int updateLotteryPrize(List<LotteryPrize> prizes)
	{
		return lotteryPrizeDao.updateLotteryPrize(prizes);
	}

}
