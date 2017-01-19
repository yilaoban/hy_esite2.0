package com.huiyee.interact.vote.dao;

import java.util.List;
import java.util.Map;

import com.huiyee.interact.lottery.model.Lottery;
import com.huiyee.interact.vote.dto.VoteDto;
import com.huiyee.interact.vote.dto.VoteRecordQueryDto;
import com.huiyee.interact.vote.model.InteractVote;
import com.huiyee.interact.vote.model.VoteOption;
import com.huiyee.interact.vote.model.VoteRecord;

public interface IVoteDao {

	public List<InteractVote> findVoteList(long ownerid,int start,int size, long omid);
	
	public int findVoteListTotal(long ownerid, long omid);
	
	public long saveVoteDesign(long ownerid,VoteDto dto, long omid);
	
	public int deleteVote(long id);
	
	public InteractVote findVoteManageModelById(long voteid);
	
	public long updateVoteDesign(long ownerid,VoteDto dto,long voteid);
	
	public List<VoteOption> findVoteOptionList(long voteid);
	
	public int findVoteOptionTotalByVoteid(long voteid);
	
	public List<VoteRecord> findVoteRecordList(long voteid,VoteRecordQueryDto queryDto, int start, int size, int type, long owner) ;
    
    public int findVoteRecordTotal(long voteid,VoteRecordQueryDto queryDto, int type, long owner);
	
	public int saveVoteContent(long voteid,String content,String description,String img,String vediourl,String tags,String linked,String linkurl);
	
	public int updateVoteContent(long id,String content,String img);
	
	public int savaVoteType(long voteid,String type);
	
	public List<VoteOption> searchVoteOptionList(long id,int start,int size);
    
    public List<Lottery> findLotteryByType(long ownerid,String type);
    
    public Lottery findLotteryById(long lotteryid);
    
    public long saveVoteRecord(long wbuid,int type,long voteid, String ip,String terminal,String source,long pageid);
    
    public int saveVoteOptionRecord(long id,long optionsid,long uid,int type);
    
    public int findUserDaySub(long uid,int type,long optionsid);
    
    public int addOptionCount(long optionid);
    
    public Map findIndx(long id);
    
    public int deleteIndx(int idx,long voteid);
    
    public int delVoteOption(long id);
    
    public int updateVoteOption(long id,String content,String img,String vediourl,String tags,String description,String linked,String linkurl);
    
    public VoteOption findOneOption(long id);
    
    public int addLotteryChance(long wbuid,long lid,int chance);
    
    public int findVoteRecordCountByNickname(long voteid,String nickname, int type);
    
    public int findVoteOptionTotal(long id);

	public int findVoteJoinTotal(long voteid, long entityid, int type);
    
	public int updateRuletypeByLotteryid(long lotteryid);
	
	public long addVote(long ownerid,String title);

	public List<VoteOption> findVoteOptionListByVoteid(long voteid);
	
	public List<VoteOption> findVoteOptionCountPC(long pageid);
	
	public int updateContnetIdx(long optionid, long voteid, int oldIdx, int newIdx);
	
}
