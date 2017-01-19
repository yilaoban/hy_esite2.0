package com.huiyee.esite.dto;

public class SitePage {
	private long siteid;
	private String sitename;
	private String stype;
	private String pagename;
	private long pageid;
	private int pnum;
	private long ownerid;
	public long getSiteid()
	{
		return siteid;
	}
	public void setSiteid(long siteid)
	{
		this.siteid = siteid;
	}
	public String getSitename()
	{
		return sitename;
	}
	public void setSitename(String sitename)
	{
		this.sitename = sitename;
	}
	public String getStype()
	{
		return stype;
	}
	public void setStype(String stype)
	{
		this.stype = stype;
	}
	public String getPagename()
	{
		return pagename;
	}
	public void setPagename(String pagename)
	{
		this.pagename = pagename;
	}
	public long getPageid()
	{
		return pageid;
	}
	public void setPageid(long pageid)
	{
		this.pageid = pageid;
	}
	public int getPnum()
	{
		return pnum;
	}
	public void setPnum(int pnum)
	{
		this.pnum = pnum;
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
