
package com.huiyee.interact.ad.model;

import java.util.Date;

public class Adwd
{

	private long id;
	private String name;
	private long owner;
	private Date createtime;
	private String idStr;
	/**
	 * °æ±¾
	 */
	public static String WD_BBN = "BBN";
	/**
	 * ÆÚºÅ
	 */
	public static String WD_QHO = "QHO";

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		if(id > 0){
			idStr = String.format("%07d", id);
		}
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public long getOwner()
	{
		return owner;
	}

	public void setOwner(long owner)
	{
		this.owner = owner;
	}

	public Date getCreatetime()
	{
		return createtime;
	}

	public void setCreatetime(Date createtime)
	{
		this.createtime = createtime;
	}

	
	public String getIdStr()
	{
		return idStr;
	}

	
	public void setIdStr(String idStr)
	{
		this.idStr = idStr;
	}

}
