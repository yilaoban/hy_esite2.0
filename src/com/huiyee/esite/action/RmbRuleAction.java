package com.huiyee.esite.action;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.huiyee.esite.mgr.IRmbRuleMgr;
import com.huiyee.esite.model.BalanceRule;
import com.huiyee.esite.model.BalanceSet;
import com.huiyee.interact.setting.mgr.IHyUserLevelMgr;
import com.huiyee.interact.setting.mgr.IUserJfMgr;

public class RmbRuleAction extends AbstractAction{
	private static final long serialVersionUID = -7386173130801083360L;
	private IRmbRuleMgr rmbRuleMgr;
	private List<BalanceRule> ruleList; 
	private long ruleid;
	private BalanceRule rule;
	private int lightType = 2;
	private BalanceSet balanceSet;
	private IUserJfMgr userJfMgr;
	
	
	public void setRmbRuleMgr(IRmbRuleMgr rmbRuleMgr) {
		this.rmbRuleMgr = rmbRuleMgr;
	}
	
	@Override
	public String execute() throws Exception {
		balanceSet = userJfMgr.findBalanceSetByOwner(this.getOwner());
		ruleList = rmbRuleMgr.findBalanceRuleListByOwner(this.getOwner());
		return SUCCESS;
	}
	
	public String addRmbRule() throws Exception{
		return SUCCESS;
	}
	
	public String saveRmbRule() throws Exception{
		rmbRuleMgr.saveRmbRule(rule,this.getOwner());
		return SUCCESS;
	}
	
	public String editRmbRule() throws Exception{
		rule = rmbRuleMgr.findRuleById(ruleid,this.getOwner());
		return SUCCESS;
	}
	
	public String updateRmbRule() throws Exception{
		rmbRuleMgr.updateRmbRule(rule,this.getOwner());
		return SUCCESS;
	}
	
	public void delRmbRule() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		int res = rmbRuleMgr.delRmbRule(ruleid,this.getOwner());
		out.print(res);
		out.flush();
		out.close();
	}
	
	public List<BalanceRule> getRuleList() {
		return ruleList;
	}

	public void setRuleList(List<BalanceRule> ruleList) {
		this.ruleList = ruleList;
	}
	public long getRuleid() {
		return ruleid;
	}

	public void setRuleid(long ruleid) {
		this.ruleid = ruleid;
	}

	public BalanceRule getRule() {
		return rule;
	}

	public void setRule(BalanceRule rule) {
		this.rule = rule;
	}

	public int getLightType() {
		return lightType;
	}

	public void setLightType(int lightType) {
		this.lightType = lightType;
	}

	
	public BalanceSet getBalanceSet()
	{
		return balanceSet;
	}

	
	public void setBalanceSet(BalanceSet balanceSet)
	{
		this.balanceSet = balanceSet;
	}

	
	public void setUserJfMgr(IUserJfMgr userJfMgr)
	{
		this.userJfMgr = userJfMgr;
	}

	
}
