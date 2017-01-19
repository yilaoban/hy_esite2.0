package com.huiyee.esite.model;

import java.io.Serializable;

public class PageCache implements Serializable {

	private static final long serialVersionUID = 2744083598620461087L;
	private long id;
	private long pageid;
	private String html;
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
	public String getHtml()
	{
		return html;
	}
	public void setHtml(String html)
	{
		this.html = html;
	}
	
	
}
