package com.huiyee.esite.model;

import java.io.Serializable;

public class Source implements Serializable{
	private String name;
	private long count;
	private long anoncount;
	private long countSum;
	
	public long getCountSum() {
		return countSum;
	}
	public void setCountSum(long countSum) {
		this.countSum = countSum;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}
	public long getAnoncount() {
		return anoncount;
	}
	public void setAnoncount(long anoncount) {
		this.anoncount = anoncount;
	}
	
}
