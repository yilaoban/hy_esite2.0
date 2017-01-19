package com.huiyee.esite.model;

import java.io.Serializable;

public class PageFeature implements Serializable  {

	private static final long serialVersionUID = -8816618882140299889L;
	private long id;
	private long pageid;
	private long featureid;
	private long fid;
	private String name1;
	private String name2;
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
	public long getFeatureid()
	{
		return featureid;
	}
	public void setFeatureid(long featureid)
	{
		this.featureid = featureid;
	}
	public long getFid()
	{
		return fid;
	}
	public void setFid(long fid)
	{
		this.fid = fid;
	}
	public String getName1()
	{
		return name1;
	}
	public void setName1(String name1)
	{
		this.name1 = name1;
	}
	public String getName2()
	{
		return name2;
	}
	public void setName2(String name2)
	{
		this.name2 = name2;
	}
	
}
