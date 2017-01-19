package com.huiyee.interact.exam.dto;

import java.util.List;
import com.huiyee.interact.exam.model.ExamRecord;
import com.huiyee.interact.vote.model.Pager;
public class ExamRecordDto implements IDto {

	private List<ExamRecord> list;
	private Pager pager;
	private String type;
	private List<ExamRecord> sourceList;
	
	public List<ExamRecord> getSourceList()
	{
		return sourceList;
	}

	public void setSourceList(List<ExamRecord> sourceList)
	{
		this.sourceList = sourceList;
	}

	public Pager getPager() {
		return pager;
	}

	public void setPager(Pager pager) {
		this.pager = pager;
	}

	public List<ExamRecord> getList()
	{
		return list;
	}

	public void setList(List<ExamRecord> list)
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
