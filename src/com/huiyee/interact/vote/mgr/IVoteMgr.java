package com.huiyee.interact.vote.mgr;

import java.util.List;
import com.huiyee.esite.dto.HdRsDto;
import com.huiyee.esite.model.VisitUser;
import com.huiyee.interact.lottery.model.Lottery;
import com.huiyee.interact.vote.dto.IDto;
import com.huiyee.interact.vote.dto.VoteDto;
import com.huiyee.interact.vote.dto.VoteOptionDto;
import com.huiyee.interact.vote.dto.VoteRecordQueryDto;
import com.huiyee.interact.vote.model.InteractVote;
import com.huiyee.interact.vote.model.VoteOption;
import com.huiyee.interact.vote.model.VoteOptionModel;

public interface IVoteMgr {

	public List<InteractVote> findVoteList(long ownerid,int start,int size, long omid);
	
	public int findVoteListTotal(long ownerid, long omid);
	
	public long saveVoteDesign(long ownerid,VoteDto dto, long omid);
	
	public int deleteVote(long id);
	
	public IDto findVoteManageModelById(long voteid,long ownerid);
	
	public long updateVoteDesign(long ownerid,VoteDto dto,long voteid);
	
	public VoteOptionDto findVoteData(long voteid);
	
	public IDto findVoteRecordData(long voteid, VoteRecordQueryDto queryDto,int pageId, int type, long owner);
	
	public int saveVoteContent(VoteOptionModel vom,long voteid); 
	
	public List<Lottery> findLotteryByType(long ownerid,String type);
	
	public List<VoteOption> searchVoteOptionList(long id,int start,int size);

	public InteractVote findVoteModelWithOptionsById(long voteid,long ownerid);
	
	public Lottery findLotteryById(long lotteryid);
	
	public HdRsDto saveVoteRecord(VisitUser visit,long cho,String ip,String terminal,long pageid,long relationid, InteractVote vote);
	
	public int delVoteOption(long id);
    
    public int updateVoteOption(long id,VoteOptionModel vom,long voteid);
    
    public VoteOption findOneOption(long id);
    
    public int findVoteOptionTotal(long id);
    
    public int updateRuletypeByLotteryid(long lotteryid);

	public InteractVote findVoteByIdAndPageid(long voteid);
	
	public long addVote(long ownerid,String title);

	public List<VoteOption> findVoteOptionCount(long voteid, VisitUser visit);
	
	public List<VoteOption> findVoteOptionCountPC(long espageid);
	
	public int updateContnetIdx(long optionid, long voteid, int oldIdx, int newIdx);

	public int updateVoteClean(long voteid, long id);
	
}
