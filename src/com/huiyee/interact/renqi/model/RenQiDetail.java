package com.huiyee.interact.renqi.model;

import java.util.Date;

public class RenQiDetail
{
	private long wbuid;
	private int utype;
	private String name;
	private Date createtime;
	private int addjf;
	private String ip;

	public long getWbuid()
	{
		return wbuid;
	}

	public void setWbuid(long wbuid)
	{
		this.wbuid = wbuid;
	}

	public int getUtype()
	{
		return utype;
	}

	public void setUtype(int utype)
	{
		this.utype = utype;
	}

	public Date getCreatetime()
	{
		return createtime;
	}

	public void setCreatetime(Date createtime)
	{
		this.createtime = createtime;
	}

	public int getAddjf()
	{
		return addjf;
	}

	public void setAddjf(int addjf)
	{
		this.addjf = addjf;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
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
