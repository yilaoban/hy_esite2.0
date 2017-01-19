
package com.huiyee.interact.ad.model;

import java.util.Date;

import com.huiyee.esite.util.DateUtil;

public class AdMedia
{

	private long id;
	private long owner;
	private String name;
	private Date createtime;
	private String hydesc;
	private String idStr;
	private String hztime;// 合作时间
	private String area;

	public String getHztime()
	{
		return hztime;
	}

	public void setHztime(String hztime)
	{
		if (hztime != null)
		{
			createtime = DateUtil.getDateTime(hztime);
		}
		this.hztime = hztime;
	}

	public String getIdStr()
	{
		return idStr;
	}

	public void setIdStr(String idStr)
	{
		this.idStr = idStr;
	}

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		if (id > 0)
		{
			idStr = String.format("%07d", id);
		}
		this.id = id;
	}

	public long getOwner()
	{
		return owner;
	}

	public void setOwner(long owner)
	{
		this.owner = owner;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public Date getCreatetime()
	{
		return createtime;
	}

	public void setCreatetime(Date createtime)
	{
		this.createtime = createtime;
	}

	public String getHydesc()
	{
		return hydesc;
	}

	public void setHydesc(String hydesc)
	{
		this.hydesc = hydesc;
	}

	public String getArea()
	{
		return area;
	}

	public void setArea(String area)
	{
		this.area = area;
	}

}
