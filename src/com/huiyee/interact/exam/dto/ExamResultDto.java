
package com.huiyee.interact.exam.dto;

import java.util.List;

import com.huiyee.interact.exam.model.ExamResult;

public class ExamResultDto implements IDto
{

	private List<ExamResult> list;
	private long total;// ×Ü·Ö

	public List<ExamResult> getList()
	{
		return list;
	}

	public void setList(List<ExamResult> list)
	{
		this.list = list;
	}

	public long getTotal()
	{
		return total;
	}

	public void setTotal(long total)
	{
		this.total = total;
	}
}
