package com.huiyee.interact.spread.dto;

import java.util.List;

import com.huiyee.interact.spread.model.Pager;
import com.huiyee.interact.spread.model.SpreadModel;

public class SpreadDto implements IDto{
	
	private List<SpreadModel> spreadList;
	private Pager pager;
	private SpreadModel spread;
	private String type;
	private long zlotteryid;
	private long llotteryid;
	private long glotteryid;
	private long ylotteryid;
	
	public long getZlotteryid()
	{
		return zlotteryid;
	}

	public void setZlotteryid(long zlotteryid)
	{
		this.zlotteryid = zlotteryid;
	}

	public long getLlotteryid()
	{
		return llotteryid;
	}

	public void setLlotteryid(long llotteryid)
	{
		this.llotteryid = llotteryid;
	}

	public long getGlotteryid()
	{
		return glotteryid;
	}

	public void setGlotteryid(long glotteryid)
	{
		this.glotteryid = glotteryid;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public SpreadModel getSpread()
	{
		return spread;
	}

	public void setSpread(SpreadModel spread)
	{
		this.spread = spread;
	}

	public Pager getPager()
	{
		return pager;
	}

	public void setPager(Pager pager)
	{
		this.pager = pager;
	}

	public List<SpreadModel> getSpreadList()
	{
		return spreadList;
	}

	public void setSpreadList(List<SpreadModel> spreadList)
	{
		this.spreadList = spreadList;
	}

	public long getYlotteryid()
	{
		return ylotteryid;
	}

	public void setYlotteryid(long ylotteryid)
	{
		this.ylotteryid = ylotteryid;
	}
	
}
