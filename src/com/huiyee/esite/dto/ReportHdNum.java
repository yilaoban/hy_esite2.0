package com.huiyee.esite.dto;

import java.util.Date;

public class ReportHdNum {
	private String nickname;
	private int hdnum;
	private Date lasthdtime;
	private String hdnumpercent;
	public String getNickname()
	{
		return nickname;
	}
	public void setNickname(String nickname)
	{
		this.nickname = nickname;
	}
	public int getHdnum()
	{
		return hdnum;
	}
	public void setHdnum(int hdnum)
	{
		this.hdnum = hdnum;
	}
	public Date getLasthdtime()
	{
		return lasthdtime;
	}
	public void setLasthdtime(Date lasthdtime)
	{
		this.lasthdtime = lasthdtime;
	}
	public String getHdnumpercent()
	{
		return hdnumpercent;
	}
	public void setHdnumpercent(String hdnumpercent)
	{
		this.hdnumpercent = hdnumpercent;
	}
}
