package com.huiyee.esite.model;

import java.util.Date;
import java.util.List;

public class JournalModel
{

	private long id;
	private long ownerid;
	private String title;
	private String isshare;
	private int balance;
	private Date createtime;
	
	public long getId()
	{
		return id;
	}
	public void setId(long id)
	{
		this.id = id;
	}
	public String getTitle()
	{
		return title;
	}
	public void setTitle(String title)
	{
		this.title = title;
	}
	public String getIsshare()
	{
		return isshare;
	}
	public void setIsshare(String isshare)
	{
		this.isshare = isshare;
	}
	public int getBalance()
	{
		return balance;
	}
	public void setBalance(int balance)
	{
		this.balance = balance;
	}
	public Date getCreatetime()
	{
		return createtime;
	}
	public void setCreatetime(Date createtime)
	{
		this.createtime = createtime;
	}
	public long getOwnerid()
	{
		return ownerid;
	}
	public void setOwnerid(long ownerid)
	{
		this.ownerid = ownerid;
	}
	
	
}
