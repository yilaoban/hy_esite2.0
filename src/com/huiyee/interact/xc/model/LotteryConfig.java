package com.huiyee.interact.xc.model;

import java.io.Serializable;

public class LotteryConfig implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -821026699749790008L;
	private int started;
	private int startnum = 1;
	private int num;// 取出人数
	private String type = "J";// J:参与抽奖 C:参与并且提高中奖率 P:排序抽奖
	private String atype = "N";// 指定抽奖 Q:签到 N:不需要
	private String unique = "N";//Y:中奖唯一, N:可重复中奖

	public int getStarted() {
		return started;
	}

	public void setStarted(int started) {
		this.started = started;
	}

	public int getStartnum() {
		return startnum;
	}

	public void setStartnum(int startnum) {
		this.startnum = startnum;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAtype() {
		return atype;
	}

	public void setAtype(String atype) {
		this.atype = atype;
	}

	public String getUnique() {
		return unique;
	}

	public void setUnique(String unique) {
		this.unique = unique;
	}

}
