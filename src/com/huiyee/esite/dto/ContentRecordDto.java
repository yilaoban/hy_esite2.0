package com.huiyee.esite.dto;

import java.util.List;

import com.huiyee.esite.model.ContentRecord;


public class ContentRecordDto implements IDto
{
	private Pager pager;
	private List<ContentRecord> contentRecordList;
	
	public Pager getPager()
	{
		return pager;
	}

	public void setPager(Pager pager)
	{
		this.pager = pager;
	}
	
	public List<ContentRecord> getContentRecordList()
	{
		return contentRecordList;
	}
	
	public void setContentRecordList(List<ContentRecord> contentRecordList)
	{
		this.contentRecordList = contentRecordList;
	}
	
}
