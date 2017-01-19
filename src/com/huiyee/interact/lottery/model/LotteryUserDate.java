package com.huiyee.interact.lottery.model;

import java.io.Serializable;
import java.util.Date;

public class LotteryUserDate implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2700262646940862979L;
	private long id;
	private long wbuid;
	private long lid;
	private Date hyday;
	private int num;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getWbuid() {
		return wbuid;
	}
	public void setWbuid(long wbuid) {
		this.wbuid = wbuid;
	}
	public long getLid() {
		return lid;
	}
	public void setLid(long lid) {
		this.lid = lid;
	}
	public Date getHyday() {
		return hyday;
	}
	public void setHyday(Date hyday) {
		this.hyday = hyday;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
}
