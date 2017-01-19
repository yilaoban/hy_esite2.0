package com.huiyee.interact.vote.dao;

import java.util.List;

import com.huiyee.interact.vote.model.VoteOption;

public interface IVoteOptionDao {

	public List<VoteOption> findOptionsByVoteid(long voteid);

	public int findUserOptionCount(long voteid, long optionid, long entityid, int type);
}
