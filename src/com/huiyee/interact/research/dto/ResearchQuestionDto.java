package com.huiyee.interact.research.dto;

import java.util.List;

import com.huiyee.interact.research.model.Pager;
import com.huiyee.interact.research.model.ResearchQuestionModel;
import com.huiyee.interact.research.model.ResearchQuestionOption;

public class ResearchQuestionDto implements IDto{
	
	List<ResearchQuestionModel>  list;
	List<ResearchQuestionOption>  rqo;
	private Pager pager;

	public Pager getPager()
	{
		return pager;
	}

	public void setPager(Pager pager)
	{
		this.pager = pager;
	}

	public List<ResearchQuestionModel> getList()
	{
		return list;
	}

	public void setList(List<ResearchQuestionModel> list)
	{
		this.list = list;
	}

	public List<ResearchQuestionOption> getRqo()
	{
		return rqo;
	}

	public void setRqo(List<ResearchQuestionOption> rqo)
	{
		this.rqo = rqo;
	}



	
	
	
}

