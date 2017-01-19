package com.huiyee.interact.exam.dto;

import java.util.List;

import com.huiyee.interact.exam.model.ExamModel;
import com.huiyee.interact.exam.model.ExamQuestionModel;
import com.huiyee.interact.exam.model.ExamQuestionOption;
import com.huiyee.interact.exam.model.ExamVO;


public class ExamQuestionOptionDto implements IDto{

	private List<ExamQuestionModel> questions;
	
	private List<ExamQuestionOption> options;
	
	private List<ExamModel> exams;
	
	private List<ExamVO> examVO;
	
	private List<ExamVO> ordVO;
	

	public List<ExamQuestionModel> getQuestions()
	{
		return questions;
	}

	public void setQuestions(List<ExamQuestionModel> questions)
	{
		this.questions = questions;
	}

	public List<ExamQuestionOption> getOptions()
	{
		return options;
	}

	public void setOptions(List<ExamQuestionOption> options)
	{
		this.options = options;
	}

	public List<ExamModel> getExams()
	{
		return exams;
	}

	public void setExams(List<ExamModel> exams)
	{
		this.exams = exams;
	}

	public List<ExamVO> getExamVO()
	{
		return examVO;
	}

	public void setExamVO(List<ExamVO> examVO)
	{
		this.examVO = examVO;
	}

	public List<ExamVO> getOrdVO()
	{
		return ordVO;
	}

	public void setOrdVO(List<ExamVO> ordVO)
	{
		this.ordVO = ordVO;
	}
	
	
	
	
}

