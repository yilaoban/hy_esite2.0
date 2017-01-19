package com.huiyee.esite.dto;

import java.util.Date;

public class HdType {
    private String type;
    private Date createtime;
    private int count;
    private long act;
    private String name;
    private long hdid;
	public long getAct()
	{
		return act;
	}
	public void setAct(long act)
	{
		this.act = act;
	}
	public String getType()
	{
		return type;
	}
	public void setType(String type)
	{
		this.type = type;
	}
	public Date getCreatetime()
	{
		return createtime;
	}
	public void setCreatetime(Date createtime)
	{
		this.createtime = createtime;
	}
	public int getCount()
	{
		return count;
	}
	public void setCount(int count)
	{
		this.count = count;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public long getHdid()
	{
		return hdid;
	}
	public void setHdid(long hdid)
	{
		this.hdid = hdid;
	}
}
