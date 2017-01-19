package com.huiyee.esite.model;

import java.io.Serializable;

public class PageHtml implements Serializable{
	private long id;
	private long pageid;
	private String type;
	private String html;
	private String css;
	private String js;
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
	public String getType()
	{
		return type;
	}
	public void setType(String type)
	{
		this.type = type;
	}
	public String getHtml()
	{
		return html;
	}
	public void setHtml(String html)
	{
		this.html = html;
	}
	public String getCss()
	{
		return css;
	}
	public void setCss(String css)
	{
		this.css = css;
	}
	public String getJs()
	{
		return js;
	}
	public void setJs(String js)
	{
		this.js = js;
	}
	
	
}
