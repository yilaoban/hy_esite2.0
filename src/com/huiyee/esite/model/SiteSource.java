package com.huiyee.esite.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
public class SiteSource implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5415586182116190060L;
	private long id;
	private long ownerid;
	private String title;
	private long siteid;
	private String path;
	private String json;
	private String vjson;
	private Date createtime;
	private String type;
	private long pid;
	
	public String getType()
	{
		return type;
	}

	
	public void setType(String type)
	{
		this.type = type;
	}

	
	public long getPid()
	{
		return pid;
	}
	
	public void setPid(long pid)
	{
		this.pid = pid;
	}

	public long getId()
	{
		return id;
	}
	
	public void setId(long id)
	{
		this.id = id;
	}
	
	public long getOwnerid()
	{
		return ownerid;
	}
	
	public void setOwnerid(long ownerid)
	{
		this.ownerid = ownerid;
	}
	
	public String getTitle()
	{
		return title;
	}
	
	public void setTitle(String title)
	{
		this.title = title;
	}
	
	public long getSiteid()
	{
		return siteid;
	}
	
	public void setSiteid(long siteid)
	{
		this.siteid = siteid;
	}
	
	public String getPath()
	{
		return path;
	}
	
	public void setPath(String path)
	{
		this.path = path;
	}
	
	public String getJson()
	{
		return json;
	}
	
	public void setJson(String json)
	{
		this.json = json;
	}
	
	public String getVjson()
	{
		return vjson;
	}
	
	public void setVjson(String vjson)
	{
		this.vjson = vjson;
	}
	
	public Date getCreatetime()
	{
		return createtime;
	}
	
	public void setCreatetime(Date createtime)
	{
		this.createtime = createtime;
	}
	
	
}
