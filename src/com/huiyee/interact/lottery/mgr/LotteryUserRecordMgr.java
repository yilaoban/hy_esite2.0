
package com.huiyee.interact.lottery.mgr;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.huiyee.esite.constants.IInteractConstants;
import com.huiyee.esite.mgr.imp.AbstractMgr;
import com.huiyee.interact.lottery.dao.ILotteryPrizeCodeDao;
import com.huiyee.interact.lottery.dao.ILotteryPrizeDao;
import com.huiyee.interact.lottery.dao.ILotteryUserDao;
import com.huiyee.interact.lottery.dao.ILotteryUserDateDao;
import com.huiyee.interact.lottery.dao.ILotteryUserRecordDao;
import com.huiyee.interact.lottery.dao.IlotteryDao;
import com.huiyee.interact.lottery.dto.IDto;
import com.huiyee.interact.lottery.dto.LRSDto;
import com.huiyee.interact.lottery.dto.LotteryDataDto;
import com.huiyee.interact.lottery.model.Lottery;
import com.huiyee.interact.lottery.model.LotteryPrize;
import com.huiyee.interact.lottery.model.LotteryPrizeCode;
import com.huiyee.interact.lottery.model.LotteryUserRecord;
import com.huiyee.interact.lottery.model.LotteryUserShow;
import com.huiyee.interact.lottery.model.LotteryWinnerDetail;
import com.huiyee.interact.lottery.model.Pager;

public class LotteryUserRecordMgr extends AbstractMgr implements ILotteryUserRecordMgr
{

	private ILotteryUserRecordDao lotteryUserRecordDao;
	private ILotteryUserDateDao lotteryUserDateDao;
	private ILotteryUserDao lotteryUserDao;
	private ILotteryPrizeDao lotteryPrizeDao;
	private ILotteryPrizeCodeDao lotteryPrizeCodeDao;
	private IlotteryDao lotteryDao;

	
	public void setLotteryDao(IlotteryDao lotteryDao)
	{
		this.lotteryDao = lotteryDao;
	}

	public void setLotteryPrizeCodeDao(ILotteryPrizeCodeDao lotteryPrizeCodeDao)
	{
		this.lotteryPrizeCodeDao = lotteryPrizeCodeDao;
	}

	public void setLotteryUserDao(ILotteryUserDao lotteryUserDao)
	{
		this.lotteryUserDao = lotteryUserDao;
	}

	public void setLotteryPrizeDao(ILotteryPrizeDao lotteryPrizeDao)
	{
		this.lotteryPrizeDao = lotteryPrizeDao;
	}

	public void setLotteryUserRecordDao(ILotteryUserRecordDao lotteryUserRecordDao)
	{
		this.lotteryUserRecordDao = lotteryUserRecordDao;
	}

	@Override
	public IDto findRecord(long lid, long wbuid, int pageId, String type, int media)
	{
		LotteryDataDto dto = new LotteryDataDto();
		dto.setLur(lotteryUserRecordDao.findRecord(lid, wbuid, type, media, (pageId - 1) * 20, 20));
		int total = lotteryUserRecordDao.findRecordTotal(lid, wbuid, type, media);
		dto.setPager(new Pager(pageId, total, 20));
		return dto;
	}

	@Override
	public IDto findRecordDetail(long lpid, String nickName, int pageId)
	{
		LotteryDataDto dto = new LotteryDataDto();
		dto.setLotteryPrize(lotteryPrizeDao.findPrizeByLpid(lpid));
		List<LotteryWinnerDetail> list = lotteryUserRecordDao.findRecordDetail(lpid, nickName, (pageId - 1) * IInteractConstants.LOTTERY_USER_RECORD_LIMIT,
				IInteractConstants.LOTTERY_USER_RECORD_LIMIT);
		for (LotteryWinnerDetail detail : list)
		{
			int type = detail.getType();
			LotteryUserRecord lur = detail.getRecord();
			if (type == 0)
			{
				lur.setNickName(lotteryUserDao.findWbNickName(lur.getWbuid()));
			} else if (type == 1)
			{
				lur.setNickName(lotteryUserDao.findWxNickName(lur.getWbuid()));
			}
			if (detail.getRecord().getStatus() == 2)
			{
				long lpcid = detail.getRecord().getLpcid();
				LotteryPrizeCode lpc = lotteryPrizeCodeDao.findLotteryCodeByLpcid(lpcid);
				detail.getRecord().setLpc(lpc);
			}
		}
		dto.setRecord(list);
		int total = lotteryUserRecordDao.findRecordDetailTotal(lpid, nickName);
		dto.setPager(new Pager(pageId, total, IInteractConstants.LOTTERY_USER_RECORD_LIMIT));
		return dto;
	}

	@Override
	public long addLotteryCmp(String nickname,int xhjf, int jf,long hyuid, Long wbuid, Long lid, Long lpid, Long lpcid, Long pageid, String ip, String terminal, String source, int status,
			int type,int userdaynum)
	{
		this.updateBalance(hyuid, -xhjf, "抽奖消耗积分","HUD" , "LOT",lid);
		long id = lotteryUserRecordDao.addLotteryCmp(nickname,wbuid, lid, lpid, lpcid, pageid, ip, terminal, source, status, type);
		if(userdaynum>0)
		{
		 lotteryUserDateDao.addUserDate(lid, wbuid, type);
		}
		lotteryUserDao.updateLotteryUsed(wbuid, lid, type);
		if (lpid != null)
		{
			lotteryPrizeDao.updateUsed(lpid);
			this.updateBalance(hyuid, jf, "抽奖获得积分","HUD" , "LOT",lid);
		}
		return id;
	}

	public void setLotteryUserDateDao(ILotteryUserDateDao lotteryUserDateDao)
	{
		this.lotteryUserDateDao = lotteryUserDateDao;
	}

	@Override
	public List<LotteryWinnerDetail> exportWb(long lpid)
	{
		return lotteryUserRecordDao.findRecordDetail(lpid);
	}

	@Override
	public List<LotteryWinnerDetail> exportWx(long lpid)
	{
		return lotteryUserRecordDao.findWxRecordDetail(lpid);
	}

	@Override
	public IDto findRecord(long lid, long maxRecordid)
	{
		LRSDto dto = new LRSDto();
		long mr = lotteryUserRecordDao.findMaxRecordid(lid);
		List<LotteryUserShow> list = new ArrayList<LotteryUserShow>();
		dto.setMaxRecordid(mr);
		dto.setTotalnum(lotteryUserRecordDao.findLotteryTotal(lid));
		dto.setWinnum(lotteryUserRecordDao.findWinTotal(lid));
		List<LotteryPrize> plist = lotteryPrizeDao.findPrizeOrdByPrice(lid);
		if (plist.size() > 0)
		{
			Map<LotteryPrize, LotteryUserShow> map = new HashMap<LotteryPrize, LotteryUserShow>();
			for (LotteryPrize lotteryPrize : plist)
			{
				List<LotteryUserShow> rlist = lotteryUserRecordDao.findRecordToScreent(lid, lotteryPrize.getId(), maxRecordid, mr);
				for (LotteryUserShow user : rlist)
				{
					int utype = user.getUtype();
					if (utype == -1)
					{
						user.setName("匿名");
					} else if (utype == 0)
					{
						user.setName(lotteryUserDao.findWbNickName(user.getUid()));
					} else if (utype == 1)
					{
						Map<String, String> umap = lotteryUserDao.findWxUserInfo(user.getUid());
						user.setName(umap.get("nickname"));
						user.setImg(umap.get("headimgurl"));
					} else if (utype == 2)
					{
						Map<String, String> umap = lotteryUserDao.findHyUserInfo(user.getUid());
						user.setName(umap.get("username"));
					}
					list.add(user);
				}
			}
		}
		dto.setList(list);
		return dto;
	}
	
	@Override
	public int updateLotteryClean(long lid, long owner)
	{
		Lottery l= lotteryDao.findLotteryById(lid);
		if(l!=null&&l.getOwner()==owner){
			return lotteryUserRecordDao.updateLotteryClean(lid);
		}
		return 0;
	}

	@Override
	public IDto findLotteryUserRecord(long lpid, String processstatus, int pageId)
	{
		LotteryDataDto dto = new LotteryDataDto();
		List<LotteryWinnerDetail> list = lotteryUserRecordDao.findLotteryUserRecord(lpid, processstatus, (pageId - 1) * IInteractConstants.LOTTERY_USER_RECORD_LIMIT,
				IInteractConstants.LOTTERY_USER_RECORD_LIMIT);
		for (LotteryWinnerDetail detail : list)
		{
			int type = detail.getType();
			LotteryUserRecord lur = detail.getRecord();
			if (type == 0)
			{
				lur.setNickName(lotteryUserDao.findWbNickName(lur.getWbuid()));
			} else if (type == 1)
			{
				lur.setNickName(lotteryUserDao.findWxNickName(lur.getWbuid()));
			}
		}
		dto.setRecord(list);
		int total = lotteryUserRecordDao.findLotteryUserRecordTotal(lpid, processstatus);
		dto.setPager(new Pager(pageId, total, IInteractConstants.LOTTERY_USER_RECORD_LIMIT));
		return dto;
	}

	@Override
	public int findUserZjTotal(long lid, long uid, int type)
	{
		return lotteryUserRecordDao.findUserZjTotal(lid, uid, type);
	}

}
