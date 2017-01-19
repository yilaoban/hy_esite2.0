package com.huiyee.esite.dao;

import java.util.List;

import com.huiyee.esite.model.BalanceRule;

public interface IRmbRuleDao {

	public List<BalanceRule> findBalanceRuleListByOwner(long owner);
	
	public int delRmbRule(long ruleid,long owner);
	
	public int saveRmbRule(BalanceRule rule, long owner);
	
	public BalanceRule findRuleById(long ruleid,long owner);
	
	public int updateRmbRule(BalanceRule rule,long owner); 
}
