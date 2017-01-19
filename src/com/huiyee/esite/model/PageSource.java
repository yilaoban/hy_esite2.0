package com.huiyee.esite.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
public class PageSource implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2028652610441434514L;
	private long id;
	private long pageid;
	private String vjson;
	private SiteSource source;
	private String html;
	private String top;
	private String left;
	private String right;
	private String bottom;
	
	
	public String getRight()
	{
		return right;
	}

	
	public void setRight(String right)
	{
		this.right = right;
	}

	
	public String getBottom()
	{
		return bottom;
	}

	
	public void setBottom(String bottom)
	{
		this.bottom = bottom;
	}

	public SiteSource getSource()
	{
		return source;
	}
	
	public void setSource(SiteSource source)
	{
		this.source = source;
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
	
	public String getHtml()
	{
		return html;
	}
	
	public void setHtml(String html)
	{
		this.html = html;
	}
	
	public String getTop()
	{
		return top;
	}
	
	public void setTop(String top)
	{
		this.top = top;
	}
	
	public String getLeft()
	{
		return left;
	}
	
	public void setLeft(String left)
	{
		this.left = left;
	}
	
	public String getVjson()
	{
		return vjson;
	}

	
	public void setVjson(String vjson)
	{
		this.vjson = vjson;
	}
	
}
