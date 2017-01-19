package com.huiyee.interact.exam.dao;

import java.util.List;

import com.huiyee.interact.lottery.model.Lottery;
import com.huiyee.interact.exam.dto.ExamDto;
import com.huiyee.interact.exam.model.ExamModel;
import com.huiyee.interact.exam.model.ExamQuestionModel;
import com.huiyee.interact.exam.model.ExamQuestionOption;
import com.huiyee.interact.exam.model.ExamRecord;
import com.huiyee.interact.exam.model.ExamResult;

public interface IExamDao{
	
	public int findExamListTotal(long ownerid, long omid);
	
	public List<ExamModel> findVoteList(long ownerid,int start,int size, long omid);
	
	public List<Lottery> findLotteryByType(long ownerid,String type);
	
	public long saveExamDesign(long ownerid,ExamDto dto, long omid);
	
	public int deleteExam(long id);
	
	public ExamModel findExamModelById(long examid);
	
	public Lottery findLotteryById(long lotteryid);
	
	public long updateExamDesign(long ownerid,ExamDto dto,long examid);
	
	public List<ExamRecord> findExamRecordList(long searchid,int start, int size, long owner);
	
	public List<ExamRecord> findExamRecordList(long searchid,int start, int size, String source, long owner );
	
	public List<ExamRecord> findExamByRecord(long searchid);
	
	public int findWxExamRecordListTotal(long searchid, String source, long owner);
	
	public int findExamRecordTotal(long searchid, long owner);
	
	public List<ExamQuestionModel> findQuestionsByExamid(long examid);
	
	public List<ExamQuestionOption> findOptionsByQuestionid(long questionid);
	
	public long saveExamReocrd(long pageid,long entityid,long examid,String ip,String terminal,String source,int type);
	
	public int saveExamAnswerXZ(long recordid,long questionid,long option);
	
	public int updateExamQuestionOption(long option);
	
	public int saveExamAnswerTK(long recordid,long questionid,String answer);
	
	public int findExamCount(long wbuid,long examid);
	
	public int findExamCountByWxuid(long wxuid,long examid);
	
	public int addLotteryChance(long wbuid,long lid,int chance);

	public int findExamRecordTotal(long searchid,int type, long owner);

	public List<ExamRecord> findNiExamRecordList(long searchid,int type, int start, int voteLimit, long owner);
	
	public int updateRuletypeByLotteryid(long lotteryid);
	
	public long addExam(long ownerid,String title);
	
	public String findExamType(long questionid);
	
	public int saveExamAnswerPx(long recordid,long questionid,long optionid,int index );

	public int updateExamClean(long searchid);

	public List<ExamResult> findResultList(long examid, long id);

	public long addExamResult(ExamResult examResult, long id);

	public int delExamResult(long resultid, long id);

	public int updateExamResult(ExamResult examResult, long id);

	public ExamResult findExamResult(long resultid, long id);
	
	public ExamResult findExamResult(long resultid);
}
