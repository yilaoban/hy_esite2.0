package com.huiyee.esite.dto;

import java.io.Serializable;

import com.huiyee.interact.lottery.model.Lottery;

public class Feature161Dto implements IDto, Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7577554666463910983L;
	private long fid;
	private long relationid;
	private Lottery lottery;
	
	public long getFid()
	{
		return fid;
	}
	
	public void setFid(long fid)
	{
		this.fid = fid;
	}
	
	public long getRelationid()
	{
		return relationid;
	}
	
	public void setRelationid(long relationid)
	{
		this.relationid = relationid;
	}
	
	public Lottery getLottery()
	{
		return lottery;
	}
	
	public void setLottery(Lottery lottery)
	{
		this.lottery = lottery;
	}
	
}
