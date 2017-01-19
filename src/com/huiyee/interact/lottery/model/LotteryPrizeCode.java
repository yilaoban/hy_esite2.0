package com.huiyee.interact.lottery.model;

import java.io.Serializable;

public class LotteryPrizeCode implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7514411857554749652L;
	private long id;
	private long lpid;
	private String code;
	private String password="";
	private String used;
	private String status;
	
	private String name;
	private int jf;
	private String link;
	
	private String timeStr;
	private String nickname;
	private String slname;
	
	public String getSlname() {
		return slname;
	}

	public void setSlname(String slname) {
		this.slname = slname;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		if(nickname != null){
			int len = nickname.length();
			String str = "";
			if(len > len/2 + 1) {
				 str = nickname.substring(len/2, len/2 + 1);
			}else{
				 str = nickname.substring(len/2);
			}
			slname = nickname.replace(str, "*");
		}
		this.nickname = nickname;
	}

	public String getTimeStr()
	{
		return timeStr;
	}
	
	public void setTimeStr(String timeStr)
	{
		this.timeStr = timeStr;
	}
	public int getJf()
	{
		return jf;
	}
	public void setJf(int jf)
	{
		this.jf = jf;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getUsed()
	{
		return used;
	}
	public void setUsed(String used)
	{
		this.used = used;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getLpid() {
		return lpid;
	}
	public void setLpid(long lpid) {
		this.lpid = lpid;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		if(password!=null)
		this.password = password;
	}
	public String getStatus()
	{
		return status;
	}
	public void setStatus(String status)
	{
		this.status = status;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
	
}
