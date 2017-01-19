package com.huiyee.interact.research.dto;

import java.util.List;
import com.huiyee.interact.research.model.Pager;
import com.huiyee.interact.research.model.ResearchModel;

public class ResearchDto implements IDto{
	
	private List<ResearchModel>  researchList;
	private Pager pager;
	private ResearchModel research;
	private String type;
	private long zlotteryid;
	private long llotteryid;
	private long glotteryid;
	private long ylotteryid;
	
	public Pager getPager(){
		return pager;
	}

	public void setPager(Pager pager){
		this.pager = pager;
	}

	public List<ResearchModel> getResearchList()
	{
		return researchList;
	}

	public void setResearchList(List<ResearchModel> researchList)
	{
		this.researchList = researchList;
	}

	public ResearchModel getResearch()
	{
		return research;
	}

	public void setResearch(ResearchModel research)
	{
		this.research = research;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

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

	public long getYlotteryid()
	{
		return ylotteryid;
	}

	public void setYlotteryid(long ylotteryid)
	{
		this.ylotteryid = ylotteryid;
	}
	
}

