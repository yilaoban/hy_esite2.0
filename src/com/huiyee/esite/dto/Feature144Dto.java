package com.huiyee.esite.dto;

import java.io.Serializable;
import java.util.List;

import com.huiyee.interact.renqi.model.RenQi;

public class Feature144Dto implements IDto, Serializable {

	private static final long serialVersionUID = 1144250361078691697L;
	private long fid;
	private long rqid;
	private List<RenQi> renQiList;
	
	public long getFid()
	{
		return fid;
	}
	public void setFid(long fid)
	{
		this.fid = fid;
	}
	public long getRqid()
	{
		return rqid;
	}
	public void setRqid(long rqid)
	{
		this.rqid = rqid;
	}
	public List<RenQi> getRenQiList()
	{
		return renQiList;
	}
	public void setRenQiList(List<RenQi> renQiList)
	{
		this.renQiList = renQiList;
	}
	
	
}
