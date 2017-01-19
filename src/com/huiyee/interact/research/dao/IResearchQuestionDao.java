package com.huiyee.interact.research.dao;

import java.util.List;
import java.util.Map;

import com.huiyee.interact.research.dto.ResearchQuestionDataDto;
import com.huiyee.interact.research.model.OptionIdx;
import com.huiyee.interact.research.model.ResearchQuestionModel;
import com.huiyee.interact.research.model.ResearchQuestionOption;
import com.huiyee.interact.research.model.ResearchQuestionOptionVO;
import com.huiyee.interact.research.model.ResearchRecordAnswer;

public interface IResearchQuestionDao
{

	public List<ResearchQuestionModel> findResearchQuestionList(long searchid,int start,int size);
	
	public int findResearchQuestionTotal(long id);
	
	public int findMaxIndx(long id);
	
	public Map findIndx(long id);
	
	public int deleteIndx(int idx,long searchid);
	
	public int updateUpResearchQuestion(long id);
	
	public int updateDownResearchQuestion(long id);
	
	public long saveResearchQuestion(long searchid,String type,String title,String pic,String isreq);
	
	public int delResearchQuestion(long id);
	
	public int updateResearchQuestion(long id,String title,String pic,String isreq);
	
	public List<ResearchQuestionModel> findOneResearchQuestion(long id);
	
	public ResearchQuestionModel findTypeIds(long id);
	
	public int saveQuestionContent(String content,String pic,long questionid);
	
	public int delResearchQuestionOption(long questionid);
	
	public int updateResearchQuestionOption(long questionid,String content,String pic);
	
	public List<ResearchQuestionDataDto> findResearchQuestionListOrderByIdex(long searchid);
	
	public List<ResearchQuestionOption> findQuestionOptionsByQuestionid(long questionid);
	
	public int findResearchOptionTotalByQuestionid(long questionid);
	
	public int deleteResearchOptions(long id);
	
	public List<ResearchQuestionOption> findoptionbyqid(long questionid);
	
	public List<ResearchQuestionModel> findquestionbyqid(long questionid,long searchid,int idx);
	
	public int updateinTarget(long id,long target);
	
	public ResearchQuestionOptionVO findselisTar(long id);
	
	public List<ResearchRecordAnswer> findAnswerByQuestionid(long questionid);
	
	public int countByQuestionid(long questionid);
	
	public List<OptionIdx> countIdx(long questionid);
	
	public int findResearchOptionCountsByQuestionid(long questionid);
}
