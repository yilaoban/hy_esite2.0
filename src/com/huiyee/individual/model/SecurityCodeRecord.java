package com.huiyee.individual.model;

import java.util.Date;

public class SecurityCodeRecord
{

	private long id;
	private long pageid;
	private long entityid;
	private int type;
	private String code1;
	private String code2;
	private String code3;
	private String code4;
	private String phone;
	private String address;
	private String status;
	private int payed;
	private Date createtime;
	private Date updatetime;
	
	public Date getCreatetime()
	{
		return createtime;
	}
	public void setCreatetime(Date createtime)
	{
		this.createtime = createtime;
	}

	public Date getUpdatetime()
	{
		return updatetime;
	}
	public void setUpdatetime(Date updatetime)
	{
		this.updatetime = updatetime;
	}
	public long getId()
	{
		return id;
	}
	public void setId(long id)
	{
		this.id = id;
	}
	public long getPageid()
	{
		return pageid;
	}
	public void setPageid(long pageid)
	{
		this.pageid = pageid;
	}
	public long getEntityid()
	{
		return entityid;
	}
	public void setEntityid(long entityid)
	{
		this.entityid = entityid;
	}
	public int getType()
	{
		return type;
	}
	public void setType(int type)
	{
		this.type = type;
	}
	public String getCode1()
	{
		return code1;
	}
	public void setCode1(String code1)
	{
		this.code1 = code1;
	}
	public String getCode2()
	{
		return code2;
	}
	public void setCode2(String code2)
	{
		this.code2 = code2;
	}
	public String getCode3()
	{
		return code3;
	}
	public void setCode3(String code3)
	{
		this.code3 = code3;
	}
	public String getCode4()
	{
		return code4;
	}
	public void setCode4(String code4)
	{
		this.code4 = code4;
	}
	public String getPhone()
	{
		return phone;
	}
	public void setPhone(String phone)
	{
		this.phone = phone;
	}
	public String getAddress()
	{
		return address;
	}
	public void setAddress(String address)
	{
		this.address = address;
	}
	public int getPayed()
	{
		return payed;
	}
	public void setPayed(int payed)
	{
		this.payed = payed;
	}
	public String getStatus()
	{
		return status;
	}
	public void setStatus(String status)
	{
		this.status = status;
	}
	
}
