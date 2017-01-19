package com.huiyee.esite.dto;

import java.io.Serializable;
import java.util.List;

import com.huiyee.interact.cs.model.Cs;

public class Feature145Dto implements IDto, Serializable {

	private static final long serialVersionUID = -2901200215237667501L;
	private long fid;
	private long csid;
	private List<Cs> csList; 
	
	public List<Cs> getCsList()
	{
		return csList;
	}
	public void setCsList(List<Cs> csList)
	{
		this.csList = csList;
	}
	public long getFid()
	{
		return fid;
	}
	public void setFid(long fid)
	{
		this.fid = fid;
	}
	public long getCsid()
	{
		return csid;
	}
	public void setCsid(long csid)
	{
		this.csid = csid;
	}

	
}
