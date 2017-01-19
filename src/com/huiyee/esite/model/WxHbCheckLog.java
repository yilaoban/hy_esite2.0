package com.huiyee.esite.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class WxHbCheckLog {

	private long id;
	private long ckid;
	private int total;
	private long account;
	private String accountname;
	private String reason;
	private String ip;
	private String createtime;
	private int type;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getCkid() {
		return ckid;
	}

	public void setCkid(long ckid) {
		this.ckid = ckid;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public long getAccount() {
		return account;
	}

	public void setAccount(long account) {
		this.account = account;
	}

	public String getAccountname() {
		return accountname;
	}

	public void setAccountname(String accountname) {
		this.accountname = accountname;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.createtime = sdf.format(createtime);
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

}
