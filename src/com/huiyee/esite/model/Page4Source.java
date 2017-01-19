package com.huiyee.esite.model;

import java.io.Serializable;
import java.util.Date;
public class Page4Source implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2028652610441434514L;
	private long id;
	private long pageid;
	private long osid;
	private String type;
	private Date createtime;
	private Page page;
	private OwnerSource os;
	
	
	public String getType()
	{
		return type;
	}

	
	public void setType(String type)
	{
		this.type = type;
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
	
	public long getOsid()
	{
		return osid;
	}
	
	public void setOsid(long osid)
	{
		this.osid = osid;
	}
	
	public Date getCreatetime()
	{
		return createtime;
	}
	
	public void setCreatetime(Date createtime)
	{
		this.createtime = createtime;
	}
	
	public Page getPage()
	{
		return page;
	}
	
	public void setPage(Page page)
	{
		this.page = page;
	}
	
	public OwnerSource getOs()
	{
		return os;
	}
	
	public void setOs(OwnerSource os)
	{
		this.os = os;
	}
}
