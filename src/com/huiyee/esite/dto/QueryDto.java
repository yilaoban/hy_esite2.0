package com.huiyee.esite.dto;

import java.io.Serializable;
import java.util.Date;

public class QueryDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6065409232110770510L;
	private String starttime;
	private String endtime;
	private String terminal = "-1"; // -1=全部 A=PAD P=PHONE C=PC
	private int pageId = 1;
	private String wbstatus = "ALL";// 用户微博审核状态
	private String orderType = "-1";

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public String getTerminal() {
		return terminal;
	}

	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}

	public int getPageId() {
		return pageId;
	}

	public void setPageId(int pageId) {
		this.pageId = pageId;
	}

	public String getWbstatus() {
		return wbstatus;
	}

	public void setWbstatus(String wbstatus) {
		this.wbstatus = wbstatus;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
}
