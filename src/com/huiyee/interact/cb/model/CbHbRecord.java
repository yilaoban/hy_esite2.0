package com.huiyee.interact.cb.model;

import java.util.Date;
import java.util.List;

import com.huiyee.esite.util.DateUtil;


public class CbHbRecord
{
	private long id;
	private int num;
	private long sender;
	private String senderName;
	private String ip;
	private String phoneagent;
	private String createtimeStr;
	private Date createtime;
	private double money;
	private int status;
	private long hyuid;
	private List<CbHbRecord> rlist;
	
	public List<CbHbRecord> getRlist() {
		return rlist;
	}


	public void setRlist(List<CbHbRecord> rlist) {
		this.rlist = rlist;
	}


	public long getHyuid() {
		return hyuid;
	}


	public void setHyuid(long hyuid) {
		this.hyuid = hyuid;
	}


	public int getStatus() {
		return status;
	}


	public void setStatus(int status) {
		this.status = status;
	}


	public double getMoney()
	{
		return money;
	}

	
	public void setMoney(double money)
	{
		this.money = money;
	}

	public long getId()
	{
		return id;
	}
	
	public void setId(long id)
	{
		this.id = id;
	}
	
	public int getNum()
	{
		return num;
	}
	
	public void setNum(int num)
	{
		this.num = num;
	}
	
	public long getSender()
	{
		return sender;
	}
	
	public void setSender(long sender)
	{
		this.sender = sender;
	}
	
	public String getIp()
	{
		return ip;
	}
	
	public void setIp(String ip)
	{
		this.ip = ip;
	}
	
	public String getPhoneagent()
	{
		return phoneagent;
	}
	
	public void setPhoneagent(String phoneagent)
	{
		this.phoneagent = phoneagent;
	}

	
	public String getCreatetimeStr()
	{
		return createtimeStr;
	}

	
	public void setCreatetimeStr(String createtimeStr)
	{
		this.createtimeStr = createtimeStr;
	}

	
	public Date getCreatetime()
	{
		return createtime;
	}

	
	public void setCreatetime(Date createtime)
	{
		if(createtime != null){
			setCreatetimeStr(DateUtil.getDateString(createtime));
		}
		this.createtime = createtime;
	}

	
	public String getSenderName()
	{
		return senderName;
	}

	
	public void setSenderName(String senderName)
	{
		this.senderName = senderName;
	}
	
	
}
