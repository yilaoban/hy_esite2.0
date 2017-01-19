package com.huiyee.interact.exam.dao;

import java.util.List;

import com.huiyee.interact.exam.model.ExamQuestionOption;
import com.huiyee.interact.exam.model.ExamVO;


public interface IExamQuestionOptionDao
{
	public List<ExamVO> findAllSurveyresult(long recordid);
	
	public String setContentbyord(long questionid, long recordid);

	public ExamQuestionOption findOptionById(long parseLong);
	
}
