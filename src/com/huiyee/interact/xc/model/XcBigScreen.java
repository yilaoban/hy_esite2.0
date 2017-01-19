package com.huiyee.interact.xc.model;

import java.io.Serializable;

public class XcBigScreen implements Serializable{

	private static final long serialVersionUID = 7744130789077924558L;
	private long id;
	private String name;
	private String pageName;
	private long pageid;
	private long xcid;
	private String imgurl;
	
	public String getImgurl()
	{
		return imgurl;
	}
	public void setImgurl(String imgurl)
	{
		this.imgurl = imgurl;
	}
	public long getId()
	{
		return id;
	}
	public void setId(long id)
	{
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
	public long getPageid()
	{
		return pageid;
	}
	public void setPageid(long pageid)
	{
		this.pageid = pageid;
	}
	public long getXcid()
	{
		return xcid;
	}
	public void setXcid(long xcid)
	{
		this.xcid = xcid;
	}
	public String getPageName()
	{
		return pageName;
	}
	public void setPageName(String pageName)
	{
		this.pageName = pageName;
	}
	
}
