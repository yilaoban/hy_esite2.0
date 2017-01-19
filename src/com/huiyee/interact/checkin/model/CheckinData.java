package com.huiyee.interact.checkin.model;

import java.io.Serializable;
import java.util.Date;

public class CheckinData implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -246827046715129645L;
	/**
	 * 
	 */
	private long wbuid;
	private String nickName;
	private int total;
	private Date createtime;

	public long getWbuid()
	{
		return wbuid;
	}

	public void setWbuid(long wbuid)
	{
		this.wbuid = wbuid;
	}

	public String getNickName()
	{
		return nickName;
	}

	public void setNickName(String nickName)
	{
		this.nickName = nickName;
	}

	public int getTotal()
	{
		return total;
	}

	public void setTotal(int total)
	{
		this.total = total;
	}

	public Date getCreatetime()
	{
		return createtime;
	}

	public void setCreatetime(Date createtime)
	{
		this.createtime = createtime;
	}

}
