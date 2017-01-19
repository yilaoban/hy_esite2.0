package com.huiyee.interact.research.dto;

import java.util.List;
import com.huiyee.interact.research.model.ResearchRecord;
import com.huiyee.interact.vote.model.Pager;
public class ResearceRecordDto implements IDto {

	private List<ResearchRecord> list;
	private Pager pager;
	private String type;
	private List<ResearchRecord> sourceList;
	
	public List<ResearchRecord> getSourceList()
	{
		return sourceList;
	}

	public void setSourceList(List<ResearchRecord> sourceList)
	{
		this.sourceList = sourceList;
	}

	public Pager getPager() {
		return pager;
	}

	public void setPager(Pager pager) {
		this.pager = pager;
	}

	public List<ResearchRecord> getList()
	{
		return list;
	}

	public void setList(List<ResearchRecord> list)
	{
		this.list = list;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}
}
