package com.huiyee.interact.exam.mgr;

import java.util.List;

import com.huiyee.interact.exam.dto.ExamQuestionDataDto;
import com.huiyee.interact.exam.model.ExamQuestionListModel;
import com.huiyee.interact.exam.model.ExamQuestionModel;
import com.huiyee.interact.exam.model.ExamQuestionOption;
import com.huiyee.interact.exam.model.ExamQuestionOptionModel;
import com.huiyee.interact.exam.model.ExamQuestionOptionVO;

public interface IExamQuestionMgr
{

	public List<ExamQuestionModel> findExamQuestionList(long searchid,int start,int size);
	
	public int findExamQuestionTotal(long id);
	
	public long saveExamQuestion(ExamQuestionListModel rqlmodel,ExamQuestionOptionModel rqomodel);
	
	public int delExamQuestion(long id);
	
	public int updateExamQuestion(long id,ExamQuestionListModel rqlmodel,long questionid,ExamQuestionOptionModel rqomodel);
	
	public List<ExamQuestionModel> findOneExamQuestion(long questionid);
	
	public List<ExamQuestionOption> findoptionbyqid(long questionid);
	
	public List<ExamQuestionModel> findquestionbyqid(long questionid,long searchid,int idx);
	
	public ExamQuestionModel findTypeIds(long id);
	
	public List<ExamQuestionDataDto> findExamData(long examid);
	
	public int updateDownExamQuestion(long id);
	
	public int updateUpExamQuestion(long id);
	
	public int deleteExamOptions(long id);
	
	public int updateinTarget(long id,long target);
	
	public ExamQuestionOptionVO selisTar(long id);
}
