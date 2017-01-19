package com.huiyee.esite.mgr.imp;

import java.util.Date;
import java.util.List;

import com.google.gson.Gson;
import com.huiyee.esite.dao.IGrCenterDao;
import com.huiyee.esite.dao.IHyUserDao;
import com.huiyee.esite.dao.IJfDesignDao;
import com.huiyee.esite.dao.IRmbRuleDao;
import com.huiyee.esite.dto.BalancePageDto;
import com.huiyee.esite.dto.GrCenterDto;
import com.huiyee.esite.dto.IDto;
import com.huiyee.esite.mgr.IGrCenterMgr;
import com.huiyee.esite.model.BalancePay;
import com.huiyee.esite.model.BalanceRule;
import com.huiyee.esite.model.BalanceSet;
import com.huiyee.esite.model.HyUser;
import com.huiyee.esite.model.VisitUser;
import com.huiyee.esite.util.DateUtil;
import com.huiyee.esite.util.JsonStringUtil;
import com.huiyee.interact.setting.dao.IHyUserLevelCodeDao;
import com.huiyee.interact.setting.dao.IHyUserLevelDao;
import com.huiyee.interact.setting.dao.IUserJfDao;
import com.huiyee.interact.setting.model.HyUserDz;
import com.huiyee.interact.setting.model.HyUserLevel;
import com.huiyee.interact.setting.model.HyUserLevelCode;
import com.huiyee.interact.template.model.WxTemplate;
import com.huiyee.interact.template.model.WxTemplateJob;
import com.huiyee.weixin.model.template.HYCZCGTZ;

public class GrCenterMgrImpl extends AbstractMgr implements IGrCenterMgr {
	private IGrCenterDao grCenterDao;
	private IJfDesignDao jfDesignDao;
	private IRmbRuleDao rmbRuleDao;
	private IHyUserLevelDao hyUserLevelDao;
	private IHyUserLevelCodeDao hyUserLevelCodeDao;
	private IUserJfDao userJfDao; 
	private IHyUserDao hyUserDao;
	
	public void setHyUserLevelCodeDao(IHyUserLevelCodeDao hyUserLevelCodeDao) {
		this.hyUserLevelCodeDao = hyUserLevelCodeDao;
	}

	public void setHyUserDao(IHyUserDao hyUserDao) {
		this.hyUserDao = hyUserDao;
	}

	public void setUserJfDao(IUserJfDao userJfDao) {
		this.userJfDao = userJfDao;
	}

	public void setHyUserLevelDao(IHyUserLevelDao hyUserLevelDao) {
		this.hyUserLevelDao = hyUserLevelDao;
	}

	public void setRmbRuleDao(IRmbRuleDao rmbRuleDao) {
		this.rmbRuleDao = rmbRuleDao;
	}

	public void setGrCenterDao(IGrCenterDao grCenterDao) {
		this.grCenterDao = grCenterDao;
	}

	public void setJfDesignDao(IJfDesignDao jfDesignDao) {
		this.jfDesignDao = jfDesignDao;
	}

	@Override
	public IDto findGrCenterData(long hyuid,long owner,HyUser hyUser) {
		GrCenterDto dto = new GrCenterDto();
		int rmb = this.findRmb(hyuid);
		int jf = this.findJFen(hyuid);
		int kqCount = grCenterDao.findTotalUnusedKQ(hyuid,"CMP");
		BalanceSet balanceSet = jfDesignDao.findBalanceSetByOwner(owner);
		if(balanceSet != null){
			String str = balanceSet.getPageset();
			Gson gson = new Gson();
			BalancePageDto pagedto = gson.fromJson(str, BalancePageDto.class);
			dto.setPagedto(pagedto);
			if(hyUser != null && hyUser.getLevelid() > 0){
				HyUserLevel hyUserLevel = hyUserLevelDao.findHyUserLevelById( hyUser.getLevelid(), owner);
				if(hyUserLevel != null){
					balanceSet.setHykimg(hyUserLevel.getImg());
					dto.setHyUserLevel(hyUserLevel);
				}
			}
		}
		dto.setJf(jf);
		dto.setKqCount(kqCount);
		dto.setRmb(rmb);
		dto.setBalanceSet(balanceSet);
		return dto;
	}
	
	@Override
	public IDto findMemberData(long owner,HyUser hyUser){
		GrCenterDto dto = new GrCenterDto();
		BalanceSet balanceSet = jfDesignDao.findBalanceSetByOwner(owner);
		if(balanceSet != null){
			String str = balanceSet.getPageset();
			Gson gson = new Gson();
			BalancePageDto pagedto = gson.fromJson(str, BalancePageDto.class);
			dto.setPagedto(pagedto);
			dto.setBalanceSet(balanceSet);
		}
		if(hyUser != null && hyUser.getLevelid() > 0){
			HyUserLevel hyUserLevel = hyUserLevelDao.findHyUserLevelById( hyUser.getLevelid(), owner);
			dto.setHyUserLevel(hyUserLevel);
		}
		
		return dto;
	}

	@Override
	public IDto findMyBalanceByHyUid(long hyuid, long owner) {
		GrCenterDto dto = new GrCenterDto();
		int rmb = this.findRmb(hyuid);
		List<BalanceRule> ruleList =  rmbRuleDao.findBalanceRuleListByOwner(owner);
		BalanceSet balanceSet = jfDesignDao.findBalanceSetByOwner(owner);
		if(balanceSet != null){
			String str = balanceSet.getPageset();
			Gson gson = new Gson();
			BalancePageDto pagedto = gson.fromJson(str, BalancePageDto.class);
			dto.setPagedto(pagedto);
		}
		dto.setBalanceSet(balanceSet);
		dto.setRuleList(ruleList);
		dto.setRmb(rmb);
		return dto;
	}

	@Override
	public long saveBalancePay(String ip, long hyuid, long owner,long ruleid) {
		BalanceRule rule = rmbRuleDao.findRuleById(ruleid, owner);
		if(rule != null){
			int price = rule.getRmb();
			return grCenterDao.saveBalancePay(ip,price,hyuid,owner,ruleid);
		}
		return 0;
	}

	@Override
	public BalanceRule findBalanceRuleById(long ruleid,long owner) {
		return rmbRuleDao.findRuleById(ruleid, owner);
	}

	@Override
	public void saveBalanceRecord(long ruleid,String total_fee,String mediaorder,long id) {
		BalancePay balancePay = grCenterDao.findBalancePayById(id);
		long hyuid = 0; long owner = 0;
		if(balancePay != null){
			hyuid = balancePay.getHyuid();
			owner = balancePay.getOwner();
			BalanceRule balanceRule = rmbRuleDao.findRuleById(ruleid, owner);
			if(balanceRule != null){
				int balance = balanceRule.getRmb() + balanceRule.getGetrmb();
				String rr=Double.valueOf(balance)/100+"元";//充值金额
				String ye=Double.valueOf(this.findRmb(hyuid)+balance)/100+"元";//当前余额
				long rid = this.updateRmbBalance(hyuid, balance, "充值"+rr , "RCG", 0);
				this.updateBalance(hyuid, balanceRule.getGetjf(), "充值赠送" + balanceRule.getGetjf() + "积分", "NEW", "CHZ", 0);
				grCenterDao.updateBalancePayById(id,mediaorder,hyuid,rid,Integer.parseInt(total_fee));
				if (owner > 0)
				{
					this.updateHyUserLevel(owner,hyuid,Integer.parseInt(total_fee),null); //提升会员等级
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
			}
		}
	}
	
	@Override
	public void updateHyUserLevel(long owner,long hyuid,int total_fee,VisitUser vu){
		BalanceSet balanceSet = userJfDao.findBalanceSetByOwner(owner);
		if(balanceSet != null && balanceSet.getHylevel() > 0){
			int totalRmb = ownerBalanceDao.findTotalRmbByUser(hyuid);
			totalRmb = totalRmb + total_fee;
			HyUserLevel level = hyUserLevelDao.findHyUserLevelByOwnerAndRMB(owner,total_fee,balanceSet.getHylevel(),totalRmb,0);
			if(level != null){
				hyUserDao.updateHyUserLevelidById(level.getId(), hyuid);
				if(vu != null && vu.getHyUser() != null){
					this.updateSession(vu);
				}
				
			}
		}
	}
	
	@Override
	public BalancePageDto findBalanceSetByOwner(long owner) {
		BalanceSet balanceSet = jfDesignDao.findBalanceSetByOwner(owner);
		if(balanceSet != null){
			String str = balanceSet.getPageset();
			Gson gson = new Gson();
			BalancePageDto pagedto = gson.fromJson(str, BalancePageDto.class);
			return pagedto;
		}
		return null;
	}

	@Override
	public BalancePay findBalancePayById(long payid) {
		return grCenterDao.findBalancePayById(payid);
	}

	@Override
	public int updateSession(VisitUser vu) {
		long hyuid = vu.getHyUserId();
		if(hyuid > 0){
			HyUser hyUser = hyUserDao.findHyUserById(hyuid);
			if(hyUser != null){
				long levelid = hyUser.getLevelid();
				if(levelid != vu.getHyUser().getLevelid()){
					vu.getHyUser().setLevelid(levelid);
					return 1;
				}
			}
		}
		return 0;
	}

	@Override
	public int updateLevelBycheckCode(long owner,long hyuid,String code,VisitUser vu) {
		BalanceSet balanceSet = userJfDao.findBalanceSetByOwner(owner);
		if(balanceSet != null && balanceSet.getHylevel() == 4){
			HyUserLevelCode levelCode = hyUserLevelCodeDao.findCode(owner,code);
			if(levelCode != null){
				if(levelCode.getStatus() == 0){
					hyUserLevelCodeDao.updateStatus(levelCode.getId());
					hyUserDao.updateHyUserLevelidById(levelCode.getLevelid(), hyuid);
					this.updateSession(vu);
					return 1;//使用成功
				}else{
					return 4;//该验证码已使用!
				}
			}else{
				return 2;//验证码不正确
			}
		}else{
			return 3;//规则不对，不能验证码提升会员等级
		}
	}
	
}
