package com.huiyee.esite.model;

import java.io.Serializable;

public class PageRelation implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3696293359917834619L;
	private long id;
	private long fapageid;
	private long pageid;
	private int type;
	public long getId()
	{
		return id;
	}
	public void setId(long id)
	{
		this.id = id;
	}
	public long getFapageid()
	{
		return fapageid;
	}
	public void setFapageid(long fapageid)
	{
		this.fapageid = fapageid;
	}
	public long getPageid()
	{
		return pageid;
	}
	public void setPageid(long pageid)
	{
		this.pageid = pageid;
	}
	public int getType()
	{
		return type;
	}
	public void setType(int type)
	{
		this.type = type;
	}
	
	
}
