package com.huiyee.interact.research.dao;

import java.util.List;

import com.huiyee.interact.lottery.model.Lottery;
import com.huiyee.interact.research.dto.ResearchDto;
import com.huiyee.interact.research.model.ResearchModel;
import com.huiyee.interact.research.model.ResearchQuestionModel;
import com.huiyee.interact.research.model.ResearchQuestionOption;
import com.huiyee.interact.research.model.ResearchRecord;

public interface IResearchDao{
	
	public int findResearchListTotal(long ownerid, long omid);
	
	public List<ResearchModel> findVoteList(long ownerid,int start,int size, long omid);
	
	public List<Lottery> findLotteryByType(long ownerid,String type);
	
	public long saveResearchDesign(long ownerid,ResearchDto dto, long omid);
	
	public int deleteResearch(long id);
	
	public ResearchModel findResearchModelById(long researchid);
	
	public Lottery findLotteryById(long lotteryid);
	
	public long updateResearchDesign(long ownerid,ResearchDto dto,long researchid);
	
	public List<ResearchRecord> findResearchRecordList(long searchid,int start, int size, long owner);
	
	public List<ResearchRecord> findWxResearchRecordList(long searchid,int start, int size, String source, long owner );
	
	public List<ResearchRecord> findWxSourceByResearchRecord(long searchid);
	
	public int findWxResearchRecordListTotal(long searchid, String source, long owner);
	
	public int findResearchRecordTotal(long searchid, long owner);
	
	public List<ResearchQuestionModel> findQuestionsByResearchid(long researchid);
	
	public List<ResearchQuestionOption> findOptionsByQuestionid(long questionid);
	
	public long saveResearchReocrd(long pageid,long entityid,long researchid,String ip,String terminal,String source,int type);
	
	public int saveResearchAnswerXZ(long recordid,long questionid,long option);
	
	public int updateResearchQuestionOption(long option);
	
	public int saveResearchAnswerTK(long recordid,long questionid,String answer);
	
	public int findResearchCount(long wbuid,long researchid);
	
	public int findResearchCountByWxuid(long wxuid,long researchid);
	
	public int addLotteryChance(long wbuid,long lid,int chance);

	public int findNiResearchRecordTotal(long searchid,int type, long owner);

	public List<ResearchRecord> findNiResearchRecordList(long searchid,int type, int start, int voteLimit, long owner);
	
	public int updateRuletypeByLotteryid(long lotteryid);
	
	public long addResearch(long ownerid,String title);
	
	public String findResearchType(long questionid);
	
	public int saveResearchAnswerPx(long recordid,long questionid,long optionid,int index );

	public int updateResearchClean(long searchid);
	
}
