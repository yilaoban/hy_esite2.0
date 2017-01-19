package com.huiyee.interact.checkin.model;

import java.io.Serializable;

public class Checkin implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4611535769844957687L;
	private long id;
	private long ownerwbuid;
	private int addnum;
	private int addaddnum;
	private int maxday;
	private int utype;
	private long wbuid;
	private String nickName;
	private long owner;
	
	public long getOwner()
	{
		return owner;
	}
	
	public void setOwner(long owner)
	{
		this.owner = owner;
	}

	public int getUtype()
	{
		return utype;
	}

	public void setUtype(int utype)
	{
		this.utype = utype;
	}

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public long getOwnerwbuid()
	{
		return ownerwbuid;
	}

	public void setOwnerwbuid(long ownerwbuid)
	{
		this.ownerwbuid = ownerwbuid;
	}

	public int getAddnum()
	{
		return addnum;
	}

	public void setAddnum(int addnum)
	{
		this.addnum = addnum;
	}

	public int getAddaddnum()
	{
		return addaddnum;
	}

	public void setAddaddnum(int addaddnum)
	{
		this.addaddnum = addaddnum;
	}

	public int getMaxday()
	{
		return maxday;
	}

	public void setMaxday(int maxday)
	{
		this.maxday = maxday;
	}

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

}
