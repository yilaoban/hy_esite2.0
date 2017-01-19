package com.huiyee.interact.setting.mgr.impl;

import java.util.List;

import com.huiyee.esite.dao.IHyUserDao;
import com.huiyee.esite.dao.IOwnerBalanceDao;
import com.huiyee.esite.model.BalanceSet;
import com.huiyee.esite.model.HyUser;
import com.huiyee.esite.model.VisitUser;
import com.huiyee.interact.setting.dao.IHyUserLevelDao;
import com.huiyee.interact.setting.dao.IUserJfDao;
import com.huiyee.interact.setting.mgr.IHyUserLevelMgr;
import com.huiyee.interact.setting.model.HyUserLevel;

public class HyUserLevelMgrImpl implements IHyUserLevelMgr {
	private IHyUserLevelDao hyUserLevelDao;
	private IHyUserDao hyUserDao;
	private IUserJfDao userJfDao;
	protected IOwnerBalanceDao ownerBalanceDao;
	
	public void setOwnerBalanceDao(IOwnerBalanceDao ownerBalanceDao) {
		this.ownerBalanceDao = ownerBalanceDao;
	}

	public void setUserJfDao(IUserJfDao userJfDao) {
		this.userJfDao = userJfDao;
	}

	public void setHyUserDao(IHyUserDao hyUserDao) {
		this.hyUserDao = hyUserDao;
	}

	public void setHyUserLevelDao(IHyUserLevelDao hyUserLevelDao) {
		this.hyUserLevelDao = hyUserLevelDao;
	}

	@Override
	public List<HyUserLevel> findHyUserLevelByOwner(long owner) {
		return hyUserLevelDao.findHyUserLevelByOwner(owner);
	}

	@Override
	public int saveUserLevel(HyUserLevel level,long owner,int hylevel) {
		return hyUserLevelDao.saveUserLevel(level,owner,hylevel);
	}

	@Override
	public HyUserLevel findHyUserLevelById(long id, long owner) {
		return hyUserLevelDao.findHyUserLevelById(id,owner);
	}

	@Override
	public int updateUserLevelById(HyUserLevel level,int hylevel) {
		return hyUserLevelDao.updateUserLevelById(level,hylevel);
	}

	@Override
	public int delUserLevel(long id, long owner) {
		return hyUserLevelDao.delUserLevel(id,owner);
	}

	@Override
	public int updateHyUserByHyuid(long hyuid, long levelid) {
		return hyUserLevelDao.updateHyUserByHyuid(hyuid,levelid);
	}
	
	/**
	 * 消费会员 满多少提升会员等级
	 * @param vu
	 */
	@Override
	public void updateUserLevel(VisitUser vu){
		long hyuid = vu.getHyUserId();
		if(hyuid > 0){
			HyUser hyUser = hyUserDao.findHyUserById(hyuid);
			if(hyUser != null){
				long levelid = hyUser.getLevelid();
				BalanceSet balanceSet = userJfDao.findBalanceSetByOwner(hyUser.getOwner());
				if(balanceSet != null && balanceSet.getHylevel() == 3){
					int rmbused = ownerBalanceDao.findRmbusedByUser(hyuid);
					HyUserLevel level = hyUserLevelDao.findHyUserLevelByOwnerAndRMB(hyUser.getOwner(),0,balanceSet.getHylevel(),0,rmbused);
					if(level != null && levelid != level.getId()){
						hyUserDao.updateHyUserLevelidById(level.getId(), hyuid);
						vu.getHyUser().setLevelid(level.getId());
					}
				}
			}
		}
	}
	
	/**
	 * 消费会员 单次满多少提升会员等级
	 * @param vu
	 */
	@Override
	public void updateUserLevel(VisitUser vu,int rmbused){
		long hyuid = vu.getHyUserId();
		if(hyuid > 0){
			HyUser hyUser = hyUserDao.findHyUserById(hyuid);
			if(hyUser != null){
				long levelid = hyUser.getLevelid();
				BalanceSet balanceSet = userJfDao.findBalanceSetByOwner(hyUser.getOwner());
				if(balanceSet != null && balanceSet.getHylevel() == 3){
					HyUserLevel level = hyUserLevelDao.findHyUserLevelByOwnerAndRMB(hyUser.getOwner(),0,balanceSet.getHylevel(),0,rmbused);
					if(level != null && levelid != level.getId()){
						hyUserDao.updateHyUserLevelidById(level.getId(), hyuid);
						vu.getHyUser().setLevelid(level.getId());
					}
				}
			}
		}
	}
	
}
