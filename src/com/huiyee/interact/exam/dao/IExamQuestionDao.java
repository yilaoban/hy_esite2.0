
package com.huiyee.interact.exam.dao;

import java.util.List;
import java.util.Map;

import com.huiyee.interact.exam.dto.ExamQuestionDataDto;
import com.huiyee.interact.exam.model.OptionIdx;
import com.huiyee.interact.exam.model.ExamQuestionModel;
import com.huiyee.interact.exam.model.ExamQuestionOption;
import com.huiyee.interact.exam.model.ExamQuestionOptionVO;
import com.huiyee.interact.exam.model.ExamRecordAnswer;

public interface IExamQuestionDao
{

	public List<ExamQuestionModel> findExamQuestionList(long searchid, int start, int size);

	public int findExamQuestionTotal(long id);

	public int findMaxIndx(long id);

	public Map findIndx(long id);

	public int deleteIndx(int idx, long searchid);

	public int updateUpExamQuestion(long id);

	public int updateDownExamQuestion(long id);

	public long saveExamQuestion(long searchid, String type, String title, String pic, String isreq);

	public int delExamQuestion(long id);

	public int updateExamQuestion(long id, String title, String pic, String isreq);

	public List<ExamQuestionModel> findOneExamQuestion(long id);

	public ExamQuestionModel findTypeIds(long id);

	public int saveQuestionContent(String content, String pic, long questionid, String score);

	public int delExamQuestionOption(long questionid);

	public int updateExamQuestionOption(long questionid, String content, String pic, String score);

	public List<ExamQuestionDataDto> findExamQuestionListOrderByIdex(long searchid);

	public List<ExamQuestionOption> findQuestionOptionsByQuestionid(long questionid);

	public int findExamOptionTotalByQuestionid(long questionid);

	public int deleteExamOptions(long id);

	public List<ExamQuestionOption> findoptionbyqid(long questionid);

	public List<ExamQuestionModel> findquestionbyqid(long questionid, long searchid, int idx);

	public int updateinTarget(long id, long target);

	public ExamQuestionOptionVO findselisTar(long id);

	public List<ExamRecordAnswer> findAnswerByQuestionid(long questionid);

	public int countByQuestionid(long questionid);

	public List<OptionIdx> countIdx(long questionid);

	public int findExamOptionCountsByQuestionid(long questionid);

	public List<ExamQuestionModel> findExamQuestionList(long examid);

	public int findMaxScore(long id);

	public int findSumScore(long id);
}
