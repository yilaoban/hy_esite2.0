
package com.huiyee.interact.exam.action;

import com.huiyee.esite.action.InteractModelAction;
import com.huiyee.interact.exam.dto.ExamQuestionOptionDto;
import com.huiyee.interact.exam.mgr.IExamQuestionOptionMgr;

public class ExamSurveyResultAction extends InteractModelAction
{

	private IExamQuestionOptionMgr examQuestionOptionMgr;
	private ExamQuestionOptionDto dto;
	private long searchid;
	private long wbuid;
	private long recordid;
	private long mid = 10006;
	private long mtype;

	@Override
	public String execute() throws Exception
	{
		dto = (ExamQuestionOptionDto) examQuestionOptionMgr.findSurveyResultList(recordid);
		return SUCCESS;
	}

	public void setExamQuestionOptionMgr(IExamQuestionOptionMgr examQuestionOptionMgr)
	{
		this.examQuestionOptionMgr = examQuestionOptionMgr;
	}

	public long getSearchid()
	{
		return searchid;
	}

	public void setSearchid(long searchid)
	{
		this.searchid = searchid;
	}

	public ExamQuestionOptionDto getDto()
	{
		return dto;
	}

	public void setDto(ExamQuestionOptionDto dto)
	{
		this.dto = dto;
	}

	public long getWbuid()
	{
		return wbuid;
	}

	public void setWbuid(long wbuid)
	{
		this.wbuid = wbuid;
	}

	public long getMid()
	{
		return mid;
	}

	public void setMid(long mid)
	{
		this.mid = mid;
	}

	public long getRecordid()
	{
		return recordid;
	}

	public void setRecordid(long recordid)
	{
		this.recordid = recordid;
	}

	
	public long getMtype()
	{
		return mtype;
	}

	
	public void setMtype(long mtype)
	{
		this.mtype = mtype;
	}

}
