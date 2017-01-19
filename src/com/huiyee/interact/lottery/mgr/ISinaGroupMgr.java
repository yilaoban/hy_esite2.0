package com.huiyee.interact.lottery.mgr;

import java.util.List;

import com.huiyee.interact.lottery.model.SinaGroup;

public interface ISinaGroupMgr {
	
	public List<SinaGroup> findWbGroupListByOwner(long owner);
	
	public int findWbuidInGroup(long gid, long owner,long wbuid);
}
