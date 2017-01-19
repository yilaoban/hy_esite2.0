package com.huiyee.interact.setting.mgr.impl;

import java.util.List;

import com.huiyee.esite.dao.IHyUserDao;
import com.huiyee.esite.dao.IWeiXinDao;
import com.huiyee.esite.mgr.imp.AbstractMgr;
import com.huiyee.esite.model.HyUser;
import com.huiyee.esite.model.OwnerBalanceSet;
import com.huiyee.esite.model.VisitUser;
import com.huiyee.esite.model.WxUser;
import com.huiyee.esite.util.Arith;
import com.huiyee.esite.util.JsonStringUtil;
import com.huiyee.interact.setting.dao.IHyUserLevelDao;
import com.huiyee.interact.setting.dao.IHyUserWayDao;
import com.huiyee.interact.setting.dao.IHyUserXfDescDao;
import com.huiyee.interact.setting.dto.CashierDto;
import com.huiyee.interact.setting.dto.CashierRs;
import com.huiyee.interact.setting.mgr.IHyUserWayMgr;
import com.huiyee.interact.setting.model.HyUserDz;
import com.huiyee.interact.setting.model.HyUserLevel;
import com.huiyee.interact.setting.model.HyUserXfDesc;
import com.huiyee.interact.setting.model.HyUserXfZk;
import com.huiyee.interact.setting.model.UWay;
import com.huiyee.interact.template.model.WxTemplate;
import com.huiyee.interact.template.model.WxTemplateJob;
import com.huiyee.weixin.model.template.HYXFTX;

public class HyUserWayMgr extends AbstractMgr implements IHyUserWayMgr
{
	private IHyUserWayDao hyUserWayDao;
	private IWeiXinDao weiXinDao;
	private IHyUserDao hyUserDao;
	private IHyUserLevelDao hyUserLevelDao;
	private IHyUserXfDescDao hyUserXfDescDao;
	
	public void setHyUserXfDescDao(IHyUserXfDescDao hyUserXfDescDao) {
		this.hyUserXfDescDao = hyUserXfDescDao;
	}

	public void setHyUserLevelDao(IHyUserLevelDao hyUserLevelDao) {
		this.hyUserLevelDao = hyUserLevelDao;
	}

	public void setHyUserDao(IHyUserDao hyUserDao) {
		this.hyUserDao = hyUserDao;
	}

	public void setWeiXinDao(IWeiXinDao weiXinDao) {
		this.weiXinDao = weiXinDao;
	}

	public void setHyUserWayDao(IHyUserWayDao hyUserWayDao)
	{
		this.hyUserWayDao = hyUserWayDao;
	}

	@Override
	public CashierRs updateUserWay(VisitUser vu, long owner,String starturl)
	{ 
		CashierRs rs=new CashierRs();
		if(vu==null||vu.getHyUser()==null){
			rs.setStatus(-1);
			rs.setHydesc("用户不存在！");
			return rs;	
		}
		UWay uw=hyUserWayDao.findWay(vu.getHyUserId());
		if(uw==null){
			hyUserWayDao.addway(vu.getHyUserId(), owner);
		}
		uw=hyUserWayDao.findWay(vu.getHyUserId());
		rs.setStatus(1);
		rs.setUway(uw.getId());
		rs.setUrl(starturl+"/user/wxshowp/uiway/"+uw.getId()+"-"+uw.getEndtime()+".html");
		return rs;
	}

	@Override
	public CashierRs updateWayStatus(VisitUser vu, long owner, String starturl, int rmb, long id, long endtime,String hydesc,long xfid)
	{
		CashierRs rs=new CashierRs();
		if(vu==null||vu.getHyUser()==null){
			rs.setStatus(-1);
			rs.setHydesc("用户不存在！");
			return rs;	
		}
		if(!this.isDz(owner, "t1", vu.getHyUserId()))
		{
			rs.setStatus(-10005);
			rs.setHydesc("没有收营员权限！");
			return rs;	
		}
		if(System.currentTimeMillis()>endtime){
			rs.setStatus(-10006);
			rs.setHydesc("二维码过期！");
			return rs;	
		}
		UWay uw=hyUserWayDao.findWayById(id, endtime, 0);
		if(uw==null)
		{
			rs.setStatus(-10007);
			rs.setHydesc("无效二维码！");
			return rs;	
		}
		long chyuid = uw.getChyuid();
		HyUser hu = hyUserDao.findHyUserById(chyuid);
		if(hu != null){
			HyUserXfZk xfZk = hyUserXfDescDao.findXfZkListByXfid(owner, hu.getLevelid(), xfid);
			if(xfZk != null){
				int zk = xfZk.getZk();
				rmb = rmb * zk / 100;
			}
		}
		hyUserWayDao.updateway(id, rmb, 0, vu.getHyUserId());
		String rr=Arith.div(rmb, 100, 2) + "元";
		String ds="会员卡支付"+rr;
		if(hydesc!=null){
			ds=hydesc;
		}
		this.updateRmbBalance(uw.getChyuid(), -rmb, ds, "SMQ", 0);//线下消费
		OwnerBalanceSet obs=this.findOBS(owner);
		String re="";
		if(obs.getOcxfnum()>0){
		int balance=rmb/100/obs.getOcxfnum();
		re=" 消费"+rr+"获得"+balance+"积分!";
		this.updateBalance(uw.getChyuid(), balance, re.trim(), "OFC", "XFJ", 0);
		}
		List<WxTemplate> wts2 = this.findWxTemplate(owner,"XFD",0);	
		if (wts2 != null&&wts2.size()>0)
		{
			for (WxTemplate wt : wts2)
			{
				HYXFTX dd = (HYXFTX) JsonStringUtil.String2Obj(wt.getData(), HYXFTX.class);
				dd.setKeyword1("NO."+uw.getChyuid());//会员卡号
				dd.setKeyword3(rr);//消费了多少钱
				dd.setRemark(dd.getRemark()+re);
				List<HyUserDz> dzs = hyUserDzDao.findDzList(owner, "t5", 0, 100);
				for (HyUserDz dz : dzs)
				{
					WxTemplateJob wj = new WxTemplateJob();
					wj.setMpid(wt.getMpid());
					wj.setType("XFD");
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
		
		List<WxTemplate> wts = this.findWxTemplate(owner,"XFU",0);	
		if (wts != null&&wts.size()>0)
		{
			for(WxTemplate wt:wts)
			{
			HYXFTX dd = (HYXFTX) JsonStringUtil.String2Obj(wt.getData(), HYXFTX.class);
			dd.setKeyword1("NO."+uw.getChyuid());//会员卡号
			dd.setKeyword3(rr);//消费了多少钱
			dd.setRemark(dd.getRemark()+re);
			WxTemplateJob wj = new WxTemplateJob();
			wj.setMpid(wt.getMpid());
			wj.setType("XFU");
			wj.setEntityid(wt.getEntityid());
			wj.setRemark(wt.getRemark());
			wj.setTemplate_id(wt.getTemplate_id());
			wj.setHyuid(uw.getChyuid());
			wj.setData(dd.toData());
			wj.setUrl(wt.getUrl());
			this.addTmplMsg(wj);
			}
		}
		rs.setStatus(1);
		return rs;
	}

	@Override
	public CashierDto findWay(long id, long endtime,int status) {
		CashierDto dto = new CashierDto();
		if(status == 0 && System.currentTimeMillis()>endtime){
			return dto;
		}
		UWay uw = hyUserWayDao.findWayById(id, endtime,status);
		if(uw!= null){
			HyUser hyUser = hyUserDao.findHyUserById(uw.getChyuid());
			if(hyUser != null){
				long levelid = hyUser.getLevelid();
				HyUserLevel hyUserLevel = new HyUserLevel();
				hyUserLevel.setName("未开通");
				if(levelid > 0){
					hyUserLevel = hyUserLevelDao.findHyUserLevelById(levelid, hyUser.getOwner());
					if(hyUserLevel == null){
						hyUserLevel = new HyUserLevel();
					}
				}
				dto.setHyUserLevel(hyUserLevel);
				dto.setHyUser(hyUser);
				WxUser wxUser = weiXinDao.getWxUserById(hyUser.getWxuid());// 1
				dto.setWxUser(wxUser);
				int rmb = this.findRmb(hyUser.getId());
				dto.setRmb(rmb);
				List<HyUserXfDesc> list = hyUserXfDescDao.findXfDescList(hyUser.getOwner());
				dto.setXfDescList(list);
			}
			dto.setUw(uw);
		}
		return dto;
	}

	@Override
	public UWay findUWayById(long id) {
		return hyUserWayDao.findUWayById(id);
	}

	@Override
	public boolean findHyUserIdentity(long owner, String type, long hyuid) {
		return this.isDz(owner, type, hyuid);
	}
  
  
}
