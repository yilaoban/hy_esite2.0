package com.huiyee.interact.leader.mgr;

import java.util.Date;

import com.huiyee.interact.leader.dto.LeaderDto;

public interface ILeaderMgr {
	
	public LeaderDto findLeaderListByOwnerid(long ownerid,int pageId,String status,Date startTime,Date endTime);
	
	public LeaderDto findLeaderById(long id);
	
	public int updateLeader(LeaderDto dto);
	
	public LeaderDto findLeaderLogListByHyuid(long hyuid,int pageId);
	
	public int delLeader(long id);
	
}
