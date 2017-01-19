package com.huiyee.esite.dto;

import java.util.Date;

public class ExportDto
{
	private String startTime;
	private String endTime;
	private long pageId;

	public String getStartTime()
	{
		return startTime;
	}

	public void setStartTime(String startTime)
	{
		this.startTime = startTime;
	}

	public String getEndTime()
	{
		return endTime;
	}

	public void setEndTime(String endTime)
	{
		this.endTime = endTime;
	}

	public long getPageId()
	{
		return pageId;
	}

	public void setPageId(long pageId)
	{
		this.pageId = pageId;
	}
}
