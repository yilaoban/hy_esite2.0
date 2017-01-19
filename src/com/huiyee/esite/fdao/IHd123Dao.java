package com.huiyee.esite.fdao;

import java.util.List;

import com.huiyee.esite.model.Module;
import com.huiyee.esite.model.PageBlockRelation;
import com.huiyee.interact.vote.model.InteractVote;
import com.huiyee.interact.vote.model.VoteOption;

public interface IHd123Dao {
	
	public long saveFeatureInteractVote(final long pageid);
	
	public Module findVoteidByFid(long fid);
	
	public List<InteractVote> findInteractVoteByOwner(long ownerid); 
	
	public int findTotalOptionById(long id);
	
	public int updateFeatureIneractVote(long voteid,long fid,int start,int end);
	
	public InteractVote findFeatureInteractVoteById(long fid);
	
	public List<VoteOption> findFeatureInteractVoteOptionById(long fid,int start,int end);
	
	public PageBlockRelation findPageBlockRelationByRelationid(long relationid);
	
	public int updatePageBlockRelationByRelationid(long relationid,String json);
	
}
