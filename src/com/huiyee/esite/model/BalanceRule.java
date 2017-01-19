package com.huiyee.esite.model;

public class BalanceRule {
	private long id;
	private long owner;
	private int rmb;
	private int getrmb;
	private int getjf;
	
	private double bilu;
	
	public double getBilu() {
		return bilu;
	}
	public void setBilu(double bilu) {
		this.bilu = bilu;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getOwner() {
		return owner;
	}
	public void setOwner(long owner) {
		this.owner = owner;
	}
	public int getRmb() {
		return rmb;
	}
	public void setRmb(int rmb) {
		this.rmb = rmb;
	}
	public int getGetrmb() {
		return getrmb;
	}
	public void setGetrmb(int getrmb) {
		this.getrmb = getrmb;
	}
	public int getGetjf() {
		return getjf;
	}
	public void setGetjf(int getjf) {
		this.getjf = getjf;
	}
	
	
}
