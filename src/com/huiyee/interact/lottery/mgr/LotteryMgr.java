package com.huiyee.interact.lottery.mgr;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.google.gson.Gson;
import com.huiyee.esite.constants.IInteractConstants;
import com.huiyee.esite.model.SinaUser;
import com.huiyee.esite.model.VisitUser;
import com.huiyee.esite.model.WxUser;
import com.huiyee.interact.lottery.dao.IlotteryDao;
import com.huiyee.interact.lottery.dao.IlotteryPrizeInfoDao;
import com.huiyee.interact.lottery.dto.IDto;
import com.huiyee.interact.lottery.dto.LotteryDto;
import com.huiyee.interact.lottery.dto.LotteryJoin;
import com.huiyee.interact.lottery.dto.Usertype;
import com.huiyee.interact.lottery.model.Lottery;
import com.huiyee.interact.lottery.model.LotteryPrize;
import com.huiyee.interact.lottery.model.LotteryPrizeCode;
import com.huiyee.interact.lottery.model.LotteryUserRecord;
import com.huiyee.interact.lottery.model.LotteryUserSub;
import com.huiyee.interact.lottery.model.LotteryWinner;
import com.huiyee.interact.lottery.model.Pager;
import com.huiyee.interact.xc.dao.ISigninDao;
import com.huiyee.interact.xc.model.XcSendRecord;

public class LotteryMgr implements ILotteryMgr
{

	private IlotteryDao lotteryDao;
	private IlotteryPrizeInfoDao lotteryPrizeInfoDao;
	private ISigninDao signinDao;

	public void setSigninDao(ISigninDao signinDao)
	{
		this.signinDao = signinDao;
	}

	public IlotteryPrizeInfoDao getLotteryPrizeInfoDao()
	{
		return lotteryPrizeInfoDao;
	}

	public void setLotteryPrizeInfoDao(IlotteryPrizeInfoDao lotteryPrizeInfoDao)
	{
		this.lotteryPrizeInfoDao = lotteryPrizeInfoDao;
	}

	public void setLotteryDao(IlotteryDao lotteryDao)
	{
		this.lotteryDao = lotteryDao;
	}

	@Override
	public IDto findAllLottery(int pageId, long owner, String type, long omid)
	{
		LotteryDto dto = new LotteryDto();
		int total = lotteryDao.findLotteryTotal(owner, type, omid);
		if (total > 0)
		{
			List<Lottery> list = lotteryDao.findAllLottery((pageId - 1) * IInteractConstants.LOTTERY_LIST_LIMIT, IInteractConstants.LOTTERY_LIST_LIMIT, owner, type, omid);
			dto.setList(list);
			dto.setPager(new Pager(pageId, total, IInteractConstants.LOTTERY_LIST_LIMIT));
		}
		return dto;
	}

	@Override
	public Lottery findLotteryById(long id)
	{
		return lotteryDao.findLotteryById(id);
	}

	@Override
	public long saveLottery(Lottery l, long omid)
	{
		return lotteryDao.saveLottery(l, omid);
	}

	@Override
	public IDto findLotteryById(long lid, long ownerid)
	{
		LotteryDto dto = new LotteryDto();
		Lottery lottery = lotteryDao.findLotteryById(lid);
		if (!StringUtils.isEmpty(lottery.getAssignuser()))
		{
			Gson g = new Gson();
			Usertype u = g.fromJson(lottery.getAssignuser(), Usertype.class);
			dto.setUsertype(u);
		}
		dto.setLottery(lottery);
		return dto;
	}

	@Override
	public int updateLottery(Lottery l, long ownerid, long lid)
	{
		return lotteryDao.updateLottery(l, ownerid, lid);
	}

	@Override
	public int updateStatus(long lid, String status, long owner)
	{
		return lotteryDao.updateStatus(lid, status, owner);
	}

	@Override
	public long saveLotteryWinnerUser(LotteryUserSub lotteryuser)
	{
		return lotteryDao.saveLotteryWinnerUser(lotteryuser);
	}

	@Override
	public IDto findPrizeWinnnerInformation(long lid, long wbuid, int type, long pageid)
	{
		LotteryDto dto = new LotteryDto();
		List<LotteryPrizeCode> list = lotteryPrizeInfoDao.findPrizeWinnnerInformation(lid, wbuid, type, pageid);
		dto.setLotteryPrizeList(list);
		return dto;
	}

	@Override
	public List<LotteryWinner> findLotteryWinner(long lid, String[] lpid, long start, int size, long xcid)
	{
		List<LotteryWinner> list = lotteryDao.findLotteryWinner(lid, start, size);
		List<LotteryWinner> list1 = new ArrayList<LotteryWinner>();
		List<String> arr = Arrays.asList(lpid);
		for (LotteryWinner w : list)
		{
			XcSendRecord sd = signinDao.findsdRecord(w.getUid(), w.getUtype(), xcid);
			if (arr.contains(w.getLpid() + ""))
			{
				if (w.getUtype() == 0)
				{
					SinaUser u = lotteryDao.findSinaUser(w.getUid());
					if (u != null)
					{
						if (sd == null || sd.getUsername() == null || "".equals(sd.getUsername()))
						{
							w.setNickname(u.getNickname());
						}
						else
						{
							w.setNickname(sd.getUsername());
						}
						w.setHeadimgurl(u.getImageurl());
					}
				}
				if (w.getUtype() == 1)
				{
					WxUser u = lotteryDao.findWxUser(w.getUid());
					if (u != null)
					{
						if (sd == null || sd.getUsername() == null || "".equals(sd.getUsername()))
						{
							w.setNickname(u.getNickname());
						}
						else
						{
							w.setNickname(sd.getUsername());
						}
						w.setHeadimgurl(u.getHeadimgurl());
					}
				}
				list1.add(w);
			}
		}
		return list1;
	}

	@Override
	public LotteryJoin findLotteryJoinDetail(long lid, VisitUser vu,String source)
	{
		long entity = vu.getUid();
		int type = vu.getCd();
		List<LotteryUserRecord> list = lotteryDao.findUserRecord(lid, entity, type);
		LotteryJoin lj = new LotteryJoin();
		lj.setJoinnum(list.size());
		int winnum = 0;
		int fail = 0;
		List<String> lpname = new ArrayList<String>();
		for (LotteryUserRecord lur : list)
		{
			if (lur.getStatus() > 0)
			{
				LotteryPrize lp=lotteryPrizeInfoDao.findPrizeNameById(lur.getLpid());
				if(source!=null){
					int pl=Integer.parseInt(source);
					if(lp.getPrice()<=pl){
						winnum++;
						lpname.add(lp.getName());
					}else{
						fail++;
					}
				}else{
					winnum++;
				}
			}
			else
			{
				fail++;
			}
		}
		lj.setLpname(lpname);
		lj.setWinnum(winnum);
		lj.setFailnum(fail);
		return lj;
	}

	@Override
	public long addLottery(long ownerid, String type, String title)
	{
		return lotteryDao.addLottery(ownerid, type, title);
	}
	
	@Override
	public List<LotteryUserRecord> findRecordByUser(long lid, VisitUser vu)
	{
		long entity = vu.getUid();
		int type = vu.getCd();
		return lotteryDao.findRecordByUser(lid,entity,type);
	}

	@Override
	public IDto findPrizeWinnnerInformation(long lid, int type, long pageid,int pageId,int size) {
		LotteryDto dto = new LotteryDto();
		int start = 0;
		if(size > 0){
			start = (pageId - 1) * size;
		}else{
			start = (pageId - 1) * IInteractConstants.LOTTERY_LIST_LIMIT;
			size = IInteractConstants.LOTTERY_LIST_LIMIT;
		}
		List<LotteryPrizeCode> list = lotteryPrizeInfoDao.findPrizeWinnnerInformation(lid, type, pageid,start,size);
		dto.setLotteryPrizeList(list);
		return dto;
	}

}
