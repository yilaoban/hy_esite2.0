
package com.huiyee.interact.exam.model;

import java.util.Date;

public class ExamResult
{

	private long id;
	private long examid;
	private String content;
	private int start;// 最小值
	private int end;// 最大值
	private Date createtime;

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public String getContent()
	{
		return content;
	}

	public void setContent(String content)
	{
		this.content = content;
	}

	public int getStart()
	{
		return start;
	}

	public void setStart(int start)
	{
		this.start = start;
	}

	public int getEnd()
	{
		return end;
	}

	public void setEnd(int end)
	{
		this.end = end;
	}

	public Date getCreatetime()
	{
		return createtime;
	}

	public void setCreatetime(Date createtime)
	{
		this.createtime = createtime;
	}

	public long getExamid()
	{
		return examid;
	}

	public void setExamid(long examid)
	{
		this.examid = examid;
	}
}
