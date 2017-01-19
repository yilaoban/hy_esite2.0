package com.huiyee.esite.dto;

import net.sf.json.JSONObject;

import com.huiyee.esite.model.Page;
import com.huiyee.esite.model.PageHtml;

public class PageEditDto implements IDto
{
	private long pageid;
	private Page page;
	private String stype;
	private PageHtml pageHtml;
	private JSONObject css;
	private JSONObject js;
	public long getPageid()
	{
		return pageid;
	}
	public void setPageid(long pageid)
	{
		this.pageid = pageid;
	}
	public Page getPage()
	{
		return page;
	}
	public void setPage(Page page)
	{
		this.page = page;
	}
	public String getStype()
	{
		return stype;
	}
	public void setStype(String stype)
	{
		this.stype = stype;
	}
	public PageHtml getPageHtml()
	{
		return pageHtml;
	}
	public void setPageHtml(PageHtml pageHtml)
	{
		this.pageHtml = pageHtml;
	}
	public JSONObject getCss()
	{
		return css;
	}
	public void setCss(JSONObject css)
	{
		this.css = css;
	}
	public JSONObject getJs()
	{
		return js;
	}
	public void setJs(JSONObject js)
	{
		this.js = js;
	}
	
	
}
