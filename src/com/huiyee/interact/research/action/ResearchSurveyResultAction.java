
package com.huiyee.interact.research.action;

import com.huiyee.esite.action.InteractModelAction;
import com.huiyee.interact.research.dto.ResearchQuestionOptionDto;
import com.huiyee.interact.research.mgr.IResearchQuestionOptionMgr;

public class ResearchSurveyResultAction extends InteractModelAction
{

	private IResearchQuestionOptionMgr researchQuestionOptionMgr;
	private ResearchQuestionOptionDto dto;
	private long searchid;
	private long wbuid;
	private long recordid;
	private long mid = 10006;
	private long mtype;

	@Override
	public String execute() throws Exception
	{
		dto = (ResearchQuestionOptionDto) researchQuestionOptionMgr.findSurveyResultList(recordid);
		return SUCCESS;
	}

	public void setResearchQuestionOptionMgr(IResearchQuestionOptionMgr researchQuestionOptionMgr)
	{
		this.researchQuestionOptionMgr = researchQuestionOptionMgr;
	}

	public long getSearchid()
	{
		return searchid;
	}

	public void setSearchid(long searchid)
	{
		this.searchid = searchid;
	}

	public ResearchQuestionOptionDto getDto()
	{
		return dto;
	}

	public void setDto(ResearchQuestionOptionDto dto)
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
