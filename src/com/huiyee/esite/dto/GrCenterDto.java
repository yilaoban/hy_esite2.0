package com.huiyee.esite.dto;

import java.util.List;

import com.huiyee.esite.model.BalanceRule;
import com.huiyee.esite.model.BalanceSet;
import com.huiyee.interact.setting.model.HyUserLevel;

public class GrCenterDto implements IDto{
	private int rmb;
	private int jf;
	private int kqCount;
	private BalanceSet balanceSet;
	private BalancePageDto pagedto;
	private List<BalanceRule> ruleList;
	private HyUserLevel hyUserLevel;
	
	public HyUserLevel getHyUserLevel() {
		return hyUserLevel;
	}
	public void setHyUserLevel(HyUserLevel hyUserLevel) {
		this.hyUserLevel = hyUserLevel;
	}
	public List<BalanceRule> getRuleList() {
		return ruleList;
	}
	public void setRuleList(List<BalanceRule> ruleList) {
		this.ruleList = ruleList;
	}
	public BalancePageDto getPagedto() {
		return pagedto;
	}
	public void setPagedto(BalancePageDto pagedto) {
		this.pagedto = pagedto;
	}
	public BalanceSet getBalanceSet() {
		return balanceSet;
	}
	public void setBalanceSet(BalanceSet balanceSet) {
		this.balanceSet = balanceSet;
	}
	public int getRmb() {
		return rmb;
	}
	public void setRmb(int rmb) {
		this.rmb = rmb;
	}
	public int getJf() {
		return jf;
	}
	public void setJf(int jf) {
		this.jf = jf;
	}
	public int getKqCount() {
		return kqCount;
	}
	public void setKqCount(int kqCount) {
		this.kqCount = kqCount;
	}
	
}
