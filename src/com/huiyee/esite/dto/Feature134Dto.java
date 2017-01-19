package com.huiyee.esite.dto;

import java.io.Serializable;
import java.util.List;

import com.huiyee.esite.model.InteractSpread;

public class Feature134Dto implements IDto, Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2451113855031072720L;
	private long fid;
	private List<InteractSpread> list;
	private long spreadid;
	private long relationid;
	
	private String type;
	private long pageid;
	
	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public long getPageid()
	{
		return pageid;
	}

	public void setPageid(long pageid)
	{
		this.pageid = pageid;
	}

	public long getRelationid()
	{
		return relationid;
	}

	public void setRelationid(long relationid)
	{
		this.relationid = relationid;
	}

	public long getFid()
	{
		return fid;
	}

	public void setFid(long fid)
	{
		this.fid = fid;
	}

	public List<InteractSpread> getList()
	{
		return list;
	}

	public void setList(List<InteractSpread> list)
	{
		this.list = list;
	}

	public long getSpreadid()
	{
		return spreadid;
	}

	public void setSpreadid(long spreadid)
	{
		this.spreadid = spreadid;
	}
}
