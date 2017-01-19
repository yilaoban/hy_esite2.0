package com.huiyee.esite.dto.showdto;

import java.io.Serializable;

import com.huiyee.esite.dto.IDto;
import com.huiyee.interact.exam.model.ExamModel;

public class Show153Dto implements IDto, Serializable{

	private static final long serialVersionUID = 3753168535074282209L;
	
	private long fid;
	private ExamModel examModel;
	
	public long getFid()
	{
		return fid;
	}
	public void setFid(long fid)
	{
		this.fid = fid;
	}
	public ExamModel getExamModel()
	{
		return examModel;
	}
	public void setExamModel(ExamModel examModel)
	{
		this.examModel = examModel;
	}
	
	
	
}
