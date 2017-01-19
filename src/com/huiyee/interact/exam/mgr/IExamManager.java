package com.huiyee.interact.exam.mgr;

import java.util.List;

import com.huiyee.esite.dto.HdRsDto;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.model.VisitUser;
import com.huiyee.interact.lottery.model.Lottery;
import com.huiyee.interact.exam.dto.ExamResultDto;
import com.huiyee.interact.exam.dto.ExamRsDto;
import com.huiyee.interact.exam.dto.IDto;
import com.huiyee.interact.exam.dto.ExamDto;
import com.huiyee.interact.exam.dto.ExamSubDto;
import com.huiyee.interact.exam.model.ExamModel;
import com.huiyee.interact.exam.model.ExamResult;

public interface IExamManager{
	
	public int findExamListTotal(long ownerid, long omid);
	
	public IDto findExamList(long ownerid,int start,int size, long omid);
	
	public ExamModel findExamModelById(long id,long ownerid);
	
	public List<Lottery> findLotteryByType(long ownerid,String type);
	
	public long saveExamDesign(long ownerid,ExamDto dto, long omid);
	
	public int deleteExam(long id);
	
	public IDto findExamModelById(long examid);
	
	public Lottery findLotteryById(long lotteryid);
	
	public long updateExamDesign(long ownerid,ExamDto dto,long examid);
	
    public IDto findExamRecordList(long searchid,int pageId, String nickname, String source, int type, long owner);
    
    public ExamRsDto saveExamReocrd(ExamSubDto dto,long pageid,VisitUser user,String ip,String terminal,long relationid, ExamModel exam, long owner);
    
	public int updateRuletypeByLotteryid(long lotteryid);

	public ExamModel findModelByIdAndPageId(long examid, long pageid);
	
	public long addExam(long ownerid,String title);

	public int updateExamClean(long searchid, long id);

	public IDto findExamResultList(long examid, Account account);

	public long addExamResult(ExamResult examResult, Account account);

	public int delExamResult(long resultid, Account account);

	public int updateExamResult(ExamResult examResult, Account account);

	public ExamResult findExamResult(long resultid, long ownerid);
	
	public ExamResult findExamResult(long resultid);
}
