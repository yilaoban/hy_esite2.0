package com.huiyee.interact.setting.mgr.impl;

import java.util.Date;
import java.util.List;

import com.huiyee.esite.dao.IGrCenterDao;
import com.huiyee.esite.dao.IHyUserDao;
import com.huiyee.esite.mgr.imp.AbstractMgr;
import com.huiyee.esite.model.BalanceSet;
import com.huiyee.esite.util.DateUtil;
import com.huiyee.esite.util.JsonStringUtil;
import com.huiyee.interact.checkin.model.Checkin;
import com.huiyee.interact.setting.dao.IHyUserLevelDao;
import com.huiyee.interact.setting.dao.IUserJfDao;
import com.huiyee.interact.setting.mgr.IUserJfMgr;
import com.huiyee.interact.setting.model.HyUserDz;
import com.huiyee.interact.setting.model.HyUserLevel;
import com.huiyee.interact.template.model.WxTemplate;
import com.huiyee.interact.template.model.WxTemplateJob;
import com.huiyee.weixin.model.template.HYCZCGTZ;

public class UserJfMgrImpl extends AbstractMgr implements IUserJfMgr {
	private IUserJfDao userJfDao;
	private IGrCenterDao grCenterDao;
	private IHyUserLevelDao hyUserLevelDao;
	private IHyUserDao hyUserDao;
	
	public void setHyUserLevelDao(IHyUserLevelDao hyUserLevelDao) {
		this.hyUserLevelDao = hyUserLevelDao;
	}

	public void setHyUserDao(IHyUserDao hyUserDao) {
		this.hyUserDao = hyUserDao;
	}

	public void setGrCenterDao(IGrCenterDao grCenterDao) {
		this.grCenterDao = grCenterDao;
	}

	public void setUserJfDao(IUserJfDao userJfDao) {
		this.userJfDao = userJfDao;
	}

	@Override
	public BalanceSet findBalanceSetByOwner(long owner) {
		return userJfDao.findBalanceSetByOwner(owner);
	}

	@Override
	public int savezfjfDesign(long owner, BalanceSet balanceSet) {
		return userJfDao.savezfjfDesign(owner, balanceSet);
	}

	@Override
	public int savesqjfDesign(long owner, BalanceSet balanceSet) {
		return userJfDao.savesqjfDesign(owner, balanceSet);
	}

	@Override
	public int savezsjfDesign(long owner, BalanceSet balanceSet) {
		return userJfDao.savezsjfDesign(owner, balanceSet);
	}

	@Override
	public int savexqjfDesign(long owner, BalanceSet balanceSet) {
		return userJfDao.savexqjfDesign(owner, balanceSet);
	}

	@Override
	public int saveyyjfDesign(long owner, BalanceSet balanceSet) {
		return userJfDao.saveyyjfDesign(owner, balanceSet);
	}

	@Override
	public int savepjjfDesign(long owner, BalanceSet balanceSet) {
		return userJfDao.savepjjfDesign(owner, balanceSet);
	}

	@Override
	public int saveczDesign(long owner, BalanceSet balanceSet) {
		return userJfDao.saveczDesign(owner, balanceSet);
	}

	@Override
	public int saveqdjfDesign(long owner, Checkin checkin) {
		return userJfDao.saveqdjfDesign(owner, checkin);
	}

	@Override
	public int addUserJf(long hyuid, int jf) {
		this.updateBalance(hyuid, jf, "充值"+jf+"积分", "HUD", "RCG", 0);
		return 1; 
	}

	@Override
	public long addUserRmb(long hyuid, int balance,String ip,long owner) {
		long payid = grCenterDao.saveBalancePay(ip,balance,hyuid,owner,0);
		
		String rr=Double.valueOf(balance)+"元";//充值金额
		String ye=Double.valueOf(this.findRmb(hyuid)+balance * 100)/100+"元";//当前余额
		long rid = this.updateRmbBalance(hyuid, balance * 100, "充值"+rr , "RCG", 0);
		int res = grCenterDao.updateBalancePayById(payid,"后台手动充值",hyuid,rid,balance * 100);
		if (owner > 0)
		{
			this.updateHyUserLevel(owner,hyuid,balance * 100); //提升会员等级
			List<WxTemplate> wts = this.findWxTemplate(owner,"CZU",0);	
			if (wts != null&&wts.size()>0)
			{
				for(WxTemplate wt:wts)
				{
				HYCZCGTZ dd = (HYCZCGTZ) JsonStringUtil.String2Obj(wt.getData(), HYCZCGTZ.class);
				dd.setKeyword1("NO."+hyuid);//会员卡号
				dd.setKeyword2(rr);//充值金额
				dd.setKeyword3(ye);//当前余额
				dd.setKeyword5(DateUtil.getDateString6(new Date()));
				WxTemplateJob wj = new WxTemplateJob();
				wj.setMpid(wt.getMpid());
				wj.setType("CZU");
				wj.setEntityid(wt.getEntityid());
				wj.setRemark(wt.getRemark());
				wj.setTemplate_id(wt.getTemplate_id());
				wj.setHyuid(hyuid);
				wj.setData(dd.toData());
				wj.setUrl(wt.getUrl());
				this.addTmplMsg(wj);
				}
			}
			List<WxTemplate> wts2 = this.findWxTemplate(owner,"CZD",0);	
			if (wts2 != null&&wts2.size()>0)
			{
				for (WxTemplate wt : wts2)
				{
					HYCZCGTZ dd = (HYCZCGTZ) JsonStringUtil.String2Obj(wt.getData(), HYCZCGTZ.class);
					dd.setKeyword1("NO."+hyuid);//会员卡号
					dd.setKeyword2(rr);//充值金额
					dd.setKeyword3(ye);//当前余额
					dd.setKeyword5(DateUtil.getDateString6(new Date()));
					List<HyUserDz> dzs = hyUserDzDao.findDzList(owner, "t6", 0, 100);
					for (HyUserDz dz : dzs)
					{
						WxTemplateJob wj = new WxTemplateJob();
						wj.setMpid(wt.getMpid());
						wj.setType("CZD");
						wj.setEntityid(wt.getEntityid());
						wj.setRemark(wt.getRemark());
						wj.setTemplate_id(wt.getTemplate_id());
						wj.setTouser(dz.getOpenid());
						wj.setData(dd.toData());
						wj.setUrl(wt.getUrl());
						this.addTmplMsg(wj);
					}
				}
			}
			
		}
		return res;
	}

	@Override
	public int updateRmbRule(long owner, BalanceSet balanceSet)
	{
		return userJfDao.updateRmbRule(owner, balanceSet);
	}
	
	public void updateHyUserLevel(long owner,long hyuid,int total_fee){
		BalanceSet balanceSet = userJfDao.findBalanceSetByOwner(owner);
		if(balanceSet != null && balanceSet.getHylevel() > 0){
			int totalRmb = ownerBalanceDao.findTotalRmbByUser(hyuid);
			totalRmb = totalRmb + total_fee;
			HyUserLevel level = hyUserLevelDao.findHyUserLevelByOwnerAndRMB(owner,total_fee,balanceSet.getHylevel(),totalRmb,0);
			if(level != null){
				hyUserDao.updateHyUserLevelidById(level.getId(), hyuid);
			}
		}
	}

	@Override
	public int saveOcxfnum(long owner, BalanceSet balanceSet) {
		return userJfDao.saveOcxfnum(owner, balanceSet);
	}
}
