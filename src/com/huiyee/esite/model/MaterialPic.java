package com.huiyee.esite.model;

import java.io.Serializable;
public class MaterialPic implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 601858953633986646L;
	private long id;
	private String name;
	private String path;
	private String type;
	private long fid;
	private long sid;
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
	public String getPath()
	{
		return path;
	}
	public void setPath(String path)
	{
		this.path = path;
	}
	public String getType()
	{
		return type;
	}
	public void setType(String type)
	{
		this.type = type;
	}
	public long getFid()
	{
		return fid;
	}
	public void setFid(long fid)
	{
		this.fid = fid;
	}
	public long getSid()
	{
		return sid;
	}
	public void setSid(long sid)
	{
		this.sid = sid;
	}
}
