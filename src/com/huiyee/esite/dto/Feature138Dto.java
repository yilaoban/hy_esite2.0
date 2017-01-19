package com.huiyee.esite.dto;

import java.io.Serializable;
import java.util.List;

import com.huiyee.esite.model.Redirect;
import com.huiyee.interact.lottery.model.Lottery;

public class Feature138Dto implements IDto , Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6120553043155843967L;
	private List<Lottery> lotteryList;
	private long lotteryid;
	private long fid;
	private long relationid;
	private Redirect redirect;
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
	public List<Lottery> getLotteryList()
	{
		return lotteryList;
	}
	public void setLotteryList(List<Lottery> lotteryList)
	{
		this.lotteryList = lotteryList;
	}
	public long getLotteryid()
	{
		return lotteryid;
	}
	public void setLotteryid(long lotteryid)
	{
		this.lotteryid = lotteryid;
	}
	public long getFid()
	{
		return fid;
	}
	public void setFid(long fid)
	{
		this.fid = fid;
	}
	
	public Redirect getRedirect()
	{
		return redirect;
	}
	
	public void setRedirect(Redirect redirect)
	{
		this.redirect = redirect;
	}
	
	
}
