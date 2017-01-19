package com.huiyee.interact.exam.dto;

import java.util.List;

import com.huiyee.interact.exam.model.Pager;
import com.huiyee.interact.exam.model.ExamQuestionModel;
import com.huiyee.interact.exam.model.ExamQuestionOption;

public class ExamQuestionDto implements IDto{
	
	List<ExamQuestionModel>  list;
	List<ExamQuestionOption>  rqo;
	private Pager pager;

	public Pager getPager()
	{
		return pager;
	}

	public void setPager(Pager pager)
	{
		this.pager = pager;
	}

	public List<ExamQuestionModel> getList()
	{
		return list;
	}

	public void setList(List<ExamQuestionModel> list)
	{
		this.list = list;
	}

	public List<ExamQuestionOption> getRqo()
	{
		return rqo;
	}

	public void setRqo(List<ExamQuestionOption> rqo)
	{
		this.rqo = rqo;
	}



	
	
	
}

