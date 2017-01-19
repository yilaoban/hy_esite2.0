package com.huiyee.esite.model;

import java.io.Serializable;

public class Content implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2610484926348345566L;
	private String hyType;
	private long ccid;
	private long featureid;
	private long ownerid;
	private long pageid;
	
	public long getOwnerid()
	{
		return ownerid;
	}
	public void setOwnerid(long ownerid)
	{
		this.ownerid = ownerid;
	}
	public String getHyType()
	{
		return hyType;
	}
	public void setHyType(String hyType)
	{
		this.hyType = hyType;
	}
	public long getCcid()
	{
		return ccid;
	}
	public void setCcid(long ccid)
	{
		this.ccid = ccid;
	}
	public long getFeatureid()
	{
		return featureid;
	}
	public void setFeatureid(long featureid)
	{
		this.featureid = featureid;
	}
	
	public long getPageid()
	{
		return pageid;
	}
	
	public void setPageid(long pageid)
	{
		this.pageid = pageid;
	}
	
	
}
