package com.huiyee.esite.mgr.imp;

import java.util.List;

import com.huiyee.esite.dao.IRmbRuleDao;
import com.huiyee.esite.mgr.IRmbRuleMgr;
import com.huiyee.esite.model.BalanceRule;

public class RmbRuleMgrImpl implements IRmbRuleMgr {
	private IRmbRuleDao rmbRuleDao;

	public void setRmbRuleDao(IRmbRuleDao rmbRuleDao) {
		this.rmbRuleDao = rmbRuleDao;
	}

	@Override
	public List<BalanceRule> findBalanceRuleListByOwner(long owner) {
		return rmbRuleDao.findBalanceRuleListByOwner(owner);
	}

	@Override
	public int delRmbRule(long ruleid,long owner) {
		return rmbRuleDao.delRmbRule(ruleid,owner);
	}

	@Override
	public int saveRmbRule(BalanceRule rule, long owner) {
		return rmbRuleDao.saveRmbRule(rule,owner);
	}

	@Override
	public BalanceRule findRuleById(long ruleid,long owner) {
		return rmbRuleDao.findRuleById(ruleid,owner);
	}

	@Override
	public int updateRmbRule(BalanceRule rule,long owner) {
		return rmbRuleDao.updateRmbRule(rule,owner);
	}
	
	
	
}
