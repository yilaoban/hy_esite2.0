package com.huiyee.interact.leader.dto;

import java.util.List;

import com.huiyee.esite.dto.Pager;
import com.huiyee.interact.leader.model.Leader;
import com.huiyee.interact.leader.model.LeaderLog;

public class LeaderDto {
	
	private List<Leader> leaderList;
	private Pager pager;
	private Leader leader;
	private List<LeaderLog> leaderLogList;
	
	public List<LeaderLog> getLeaderLogList() {
		return leaderLogList;
	}

	public void setLeaderLogList(List<LeaderLog> leaderLogList) {
		this.leaderLogList = leaderLogList;
	}

	public List<Leader> getLeaderList() {
		return leaderList;
	}

	public void setLeaderList(List<Leader> leaderList) {
		this.leaderList = leaderList;
	}

	public Pager getPager() {
		return pager;
	}

	public void setPager(Pager pager) {
		this.pager = pager;
	}

	public Leader getLeader() {
		return leader;
	}

	public void setLeader(Leader leader) {
		this.leader = leader;
	}
	
	
}
