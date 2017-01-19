package com.huiyee.interact.leader.mgr;

import java.util.Date;
import java.util.List;

import com.huiyee.esite.dto.Pager;
import com.huiyee.interact.leader.dao.ILeaderDao;
import com.huiyee.interact.leader.dto.LeaderDto;
import com.huiyee.interact.leader.model.Leader;
import com.huiyee.interact.leader.model.LeaderLog;

public class LeaderMgrImpl implements ILeaderMgr {
	
	private ILeaderDao leaderDao;

	public void setLeaderDao(ILeaderDao leaderDao) {
		this.leaderDao = leaderDao;
	}

	@Override
	public LeaderDto findLeaderListByOwnerid(long ownerid,int pageId,String status,Date startTime,Date endTime) {
		LeaderDto dto = new LeaderDto();
		int total = leaderDao.findTotalLeaderByOwnerid(ownerid,status,startTime,endTime);
		int rows = 10;
		int start = (pageId - 1) * rows;
		if(total > 0){
			List<Leader> leaderList =	leaderDao.findLeaderListByOwnerid(ownerid,status,startTime,endTime,start,rows);
			dto.setLeaderList(leaderList);
		}
		Pager pager = new Pager(pageId, total, rows);
		dto.setPager(pager);
		return dto;
	}

	@Override
	public LeaderDto findLeaderById(long id) {
		LeaderDto dto = new LeaderDto();
		Leader leader = leaderDao.findLeaderById(id);
		dto.setLeader(leader);
		return dto;
	}

	@Override
	public int updateLeader(LeaderDto dto) {
		return leaderDao.updateLeader(dto);
	}

	@Override
	public LeaderDto findLeaderLogListByHyuid(long hyuid,int pageId) {
		LeaderDto dto = new LeaderDto();
		int total = leaderDao.findTotalLeaderLogByHyuid(hyuid);
		int rows = 10;
		int start = (pageId - 1) * rows;
		if(total > 0){
			List<LeaderLog> leaderLogList =	leaderDao.findLeaderLogListByHyuid(hyuid,start,rows);
			dto.setLeaderLogList(leaderLogList);
		}
		Pager pager = new Pager(pageId, total, rows);
		dto.setPager(pager);
		return dto;
	}

	@Override
	public int delLeader(long id) {
		return leaderDao.delLeader(id);
	}
	
	
}
