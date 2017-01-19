package com.huiyee.interact.leader.dao;

import java.util.Date;
import java.util.List;

import com.huiyee.interact.leader.dto.LeaderDto;
import com.huiyee.interact.leader.model.Leader;
import com.huiyee.interact.leader.model.LeaderLog;

public interface ILeaderDao {

	public int findTotalLeaderByOwnerid(long ownerid,String status,Date startTime,Date endTime);
	
	public List<Leader> findLeaderListByOwnerid(long ownerid,String status,Date startTime,Date endTime,int start,int size);

	public Leader findLeaderById(long id);
	
	public int updateLeader(LeaderDto dto);
	
	public int findTotalLeaderLogByHyuid(long hyuid);
	
	public List<LeaderLog> findLeaderLogListByHyuid(long hyuid,int start,int size);
	
	public int delLeader(long id);
}
