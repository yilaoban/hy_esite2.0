package com.huiyee.interact.research.dao;

import java.util.List;

import com.huiyee.interact.research.model.ResearchVO;


public interface IResearchQuestionOptionDao
{
	public List<ResearchVO> findAllSurveyresult(long recordid);
	
	public String setContentbyord(long questionid, long recordid);
	
}
