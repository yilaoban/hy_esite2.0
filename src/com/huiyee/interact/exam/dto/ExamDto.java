package com.huiyee.interact.exam.dto;

import java.util.List;
import com.huiyee.interact.exam.model.Pager;
import com.huiyee.interact.exam.model.ExamModel;

public class ExamDto implements IDto{
	
	private List<ExamModel>  examList;
	private Pager pager;
	private ExamModel exam;
	private String type;
	private long zlotteryid;
	private long llotteryid;
	private long glotteryid;
	private long ylotteryid;
	
	public Pager getPager(){
		return pager;
	}

	public void setPager(Pager pager){
		this.pager = pager;
	}

	public List<ExamModel> getExamList()
	{
		return examList;
	}

	public void setExamList(List<ExamModel> examList)
	{
		this.examList = examList;
	}

	public ExamModel getExam()
	{
		return exam;
	}

	public void setExam(ExamModel exam)
	{
		this.exam = exam;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public long getZlotteryid()
	{
		return zlotteryid;
	}

	public void setZlotteryid(long zlotteryid)
	{
		this.zlotteryid = zlotteryid;
	}

	public long getLlotteryid()
	{
		return llotteryid;
	}

	public void setLlotteryid(long llotteryid)
	{
		this.llotteryid = llotteryid;
	}

	public long getGlotteryid()
	{
		return glotteryid;
	}

	public void setGlotteryid(long glotteryid)
	{
		this.glotteryid = glotteryid;
	}

	public long getYlotteryid()
	{
		return ylotteryid;
	}

	public void setYlotteryid(long ylotteryid)
	{
		this.ylotteryid = ylotteryid;
	}
	
}

