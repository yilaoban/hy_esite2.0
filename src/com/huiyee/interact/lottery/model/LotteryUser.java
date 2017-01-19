package com.huiyee.interact.lottery.model;

import java.io.Serializable;

public class LotteryUser implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5316010300049145211L;
	private long id;
	private long wbuid;
	private String nickName;
	private long lid;
	private int totalnum;
	private int usednum;
	private String ip;
	private String username;
	private String wxnickname;
	
	public String getWxnickname() {
		return wxnickname;
	}
	public void setWxnickname(String wxnickname) {
		this.wxnickname = wxnickname;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
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
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public long getLid() {
		return lid;
	}
	public void setLid(long lid) {
		this.lid = lid;
	}
	public int getTotalnum() {
		return totalnum;
	}
	public void setTotalnum(int totalnum) {
		this.totalnum = totalnum;
	}
	public int getUsednum() {
		return usednum;
	}
	public void setUsednum(int usednum) {
		this.usednum = usednum;
	}
	public String getIp()
	{
		return ip;
	}
	public void setIp(String ip)
	{
		this.ip = ip;
	}
	
}
