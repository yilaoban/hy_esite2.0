package com.huiyee.interact.exam.dto;

import com.huiyee.esite.dto.HdRsDto;


public class ExamRsDto extends HdRsDto
{
	private long resultid;
	private String content;

	public long getResultid()
	{
		return resultid;
	}

	public void setResultid(long resultid)
	{
		this.resultid = resultid;
	}

	public String getContent()
	{
		return content;
	}

	public void setContent(String content)
	{
		this.content = content;
	}
}
