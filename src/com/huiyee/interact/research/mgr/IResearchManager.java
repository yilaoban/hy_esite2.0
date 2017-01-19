package com.huiyee.interact.research.mgr;

import java.util.List;
import com.huiyee.esite.dto.HdRsDto;
import com.huiyee.esite.model.VisitUser;
import com.huiyee.interact.lottery.model.Lottery;
import com.huiyee.interact.research.dto.IDto;
import com.huiyee.interact.research.dto.ResearchDto;
import com.huiyee.interact.research.dto.ResearchSubDto;
import com.huiyee.interact.research.model.ResearchModel;

public interface IResearchManager{
	
	public int findResearchListTotal(long ownerid, long omid);
	
	public IDto findResearchList(long ownerid,int start,int size, long omid);
	
	public ResearchModel findResearchModelById(long id,long ownerid);
	
	public List<Lottery> findLotteryByType(long ownerid,String type);
	
	public long saveResearchDesign(long ownerid,ResearchDto dto, long omid);
	
	public int deleteResearch(long id);
	
	public IDto findResearchModelById(long researchid);
	
	public Lottery findLotteryById(long lotteryid);
	
	public long updateResearchDesign(long ownerid,ResearchDto dto,long researchid);
	
    public IDto findResearchRecordList(long searchid,int pageId, String nickname, String source, int type, long owner);
    
    public HdRsDto saveResearchReocrd(ResearchSubDto dto,long pageid,VisitUser user,String ip,String terminal,long relationid, ResearchModel research);
    
	public int updateRuletypeByLotteryid(long lotteryid);

	public ResearchModel findModelByIdAndPageId(long researchid, long pageid);
	
	public long addResearch(long ownerid,String title);

	public int updateResearchClean(long searchid, long id);
	
}
