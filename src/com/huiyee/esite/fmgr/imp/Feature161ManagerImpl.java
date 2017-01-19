package com.huiyee.esite.fmgr.imp;

import java.util.List;

import com.huiyee.esite.dao.IPageDao;
import com.huiyee.esite.dto.Feature161Dto;
import com.huiyee.esite.dto.IDto;
import com.huiyee.esite.dto.showdto.Show125Dto;
import com.huiyee.esite.dto.showdto.Show161Dto;
import com.huiyee.esite.fdao.IHd125Dao;
import com.huiyee.esite.fdao.IHd161Dao;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.model.Page;
import com.huiyee.esite.model.PageBlockRelation;
import com.huiyee.interact.lottery.dao.ILotteryPrizeDao;
import com.huiyee.interact.lottery.dao.IlotteryDao;
import com.huiyee.interact.lottery.model.Lottery;
import com.huiyee.interact.lottery.model.LotteryPrize;

import net.sf.json.JSONObject;

public class Feature161ManagerImpl extends AbstractFeatureManager{
	private IHd125Dao hd125Dao;//抽奖类
	private IHd161Dao hd161Dao;//九宫格
	private IPageDao pageDao;
	private IlotteryDao lotteryDao;
	private ILotteryPrizeDao lotteryPrizeDao;

	@Override
	public long add(long pageid, long featureid, String featurename) {
		Page page = pageDao.findPageById(pageid);
		long ownerid = pageDao.findOwnerByPageid(pageid);
		Lottery lottery = new Lottery();
		lottery.setName(page.getName());
		lottery.setOwner(ownerid);
		lottery.setType("N");
		lottery.setOmid(1);
		long lid = lotteryDao.saveLottery(lottery, 1);
		for(int i = 0;	i < 8; i++){
			LotteryPrize lp = new LotteryPrize();
			lp.setName("谢谢参与");
			lp.setLid(lid);
			lp.setHydefault("Y");
			lp.setType("J");
			lp.setPositionid(i);
			lotteryPrizeDao.saveprice(lp);
		}
		long fid = hd161Dao.saveFeatureInteractLottery(pageid, "N", lid);
		return getPageFeatureDao().addPageFeature(pageid, featureid, fid,featurename,"N");
	}

	@Override
	public IDto config(long fid, Account account) {
		Feature161Dto dto = new Feature161Dto();
		long lotteryid = hd161Dao.findLotteryIdByFid(fid);
		Lottery lottery = lotteryDao.findLotteryById(lotteryid);
		dto.setLottery(lottery);
        return dto;
	}

	@Override
	public IDto config(long fid) {
		Show125Dto d = new Show125Dto();
		Lottery l = hd125Dao.findFeatureInteractLotteryById(fid);
		if(l != null){
			List<LotteryPrize> lp = hd125Dao.findLotteryPriceByLotteryid(l.getId());
			l.setLotteryPrizes(lp);
		}
		d.setLottery(l);
		return d;
	}

	@Override
	public String configSub(long featureid, IDto dto, Account account) {
		return "Y";
	}
	
	@Override
	public IDto configNew(long fid, Account account, long relationid) {
		 return config(fid, account);
	}

	@Override
	public String configSubNew(long featureid, IDto dto, Account account) {
		Feature161Dto d = (Feature161Dto) dto;
		configSub(featureid, dto, account);
		JSONObject json = new JSONObject();
		json.element("fid", d.getFid());
		json.element("id", d.getLottery().getId());
		json.element("featureid", featureid);
		PageBlockRelation pbr = this.pageRelationDao.findPageBlockRelationByRelationid(d.getRelationid());
		String str = pbr.getJson();
		JSONObject jo = JSONObject.fromObject(str);
		jo.element("obj", json.toString());
		this.pageRelationDao.updatePageBlockRelationByRelationid(d.getRelationid(),jo.toString());
		hd161Dao.updateLottery(d.getLottery());
		return "Y";
	}
	
	
	public void setLotteryDao(IlotteryDao lotteryDao)
	{
		this.lotteryDao = lotteryDao;
	}

	public void setHd125Dao(IHd125Dao hd125Dao)
	{
		this.hd125Dao = hd125Dao;
	}

	public void setHd161Dao(IHd161Dao hd161Dao)
	{
		this.hd161Dao = hd161Dao;
	}

	public void setPageDao(IPageDao pageDao)
	{
		this.pageDao = pageDao;
	}
	
	public void setLotteryPrizeDao(ILotteryPrizeDao lotteryPrizeDao)
	{
		this.lotteryPrizeDao = lotteryPrizeDao;
	}
}
