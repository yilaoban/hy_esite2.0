package com.huiyee.interact.research.mgr;

import java.util.List;

import com.huiyee.interact.research.dto.ResearchQuestionDataDto;
import com.huiyee.interact.research.model.ResearchQuestionListModel;
import com.huiyee.interact.research.model.ResearchQuestionModel;
import com.huiyee.interact.research.model.ResearchQuestionOption;
import com.huiyee.interact.research.model.ResearchQuestionOptionModel;
import com.huiyee.interact.research.model.ResearchQuestionOptionVO;

public interface IResearchQuestionMgr
{

	public List<ResearchQuestionModel> findResearchQuestionList(long searchid,int start,int size);
	
	public int findResearchQuestionTotal(long id);
	
	public long saveResearchQuestion(ResearchQuestionListModel rqlmodel,ResearchQuestionOptionModel rqomodel);
	
	public int delResearchQuestion(long id);
	
	public int updateResearchQuestion(long id,ResearchQuestionListModel rqlmodel,long questionid,ResearchQuestionOptionModel rqomodel);
	
	public List<ResearchQuestionModel> findOneResearchQuestion(long questionid);
	
	public List<ResearchQuestionOption> findoptionbyqid(long questionid);
	
	public List<ResearchQuestionModel> findquestionbyqid(long questionid,long searchid,int idx);
	
	public ResearchQuestionModel findTypeIds(long id);
	
	public List<ResearchQuestionDataDto> findResearchData(long researchid);
	
	public int updateDownResearchQuestion(long id);
	
	public int updateUpResearchQuestion(long id);
	
	public int deleteResearchOptions(long id);
	
	public int updateinTarget(long id,long target);
	
	public ResearchQuestionOptionVO selisTar(long id);
}
