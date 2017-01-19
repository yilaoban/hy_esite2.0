package com.huiyee.interact.research.dto;

import java.util.List;

import com.huiyee.interact.research.model.ResearchModel;
import com.huiyee.interact.research.model.ResearchQuestionModel;
import com.huiyee.interact.research.model.ResearchQuestionOption;
import com.huiyee.interact.research.model.ResearchVO;


public class ResearchQuestionOptionDto implements IDto{

	private List<ResearchQuestionModel> questions;
	
	private List<ResearchQuestionOption> options;
	
	private List<ResearchModel> researchs;
	
	private List<ResearchVO> researchVO;
	
	private List<ResearchVO> ordVO;
	

	public List<ResearchQuestionModel> getQuestions()
	{
		return questions;
	}

	public void setQuestions(List<ResearchQuestionModel> questions)
	{
		this.questions = questions;
	}

	public List<ResearchQuestionOption> getOptions()
	{
		return options;
	}

	public void setOptions(List<ResearchQuestionOption> options)
	{
		this.options = options;
	}

	public List<ResearchModel> getResearchs()
	{
		return researchs;
	}

	public void setResearchs(List<ResearchModel> researchs)
	{
		this.researchs = researchs;
	}

	public List<ResearchVO> getResearchVO()
	{
		return researchVO;
	}

	public void setResearchVO(List<ResearchVO> researchVO)
	{
		this.researchVO = researchVO;
	}

	public List<ResearchVO> getOrdVO()
	{
		return ordVO;
	}

	public void setOrdVO(List<ResearchVO> ordVO)
	{
		this.ordVO = ordVO;
	}
	
	
	
	
}

