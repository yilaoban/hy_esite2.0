package com.huiyee.interact.lottery.dao;

import java.util.List;

import com.huiyee.interact.lottery.model.SinaGroup;

public interface ISinaGroupDao
{
	public List<SinaGroup> findWbGroupListByOwner(long owner);
	
	public int findWbuidInGroup(long gid, long owner,long wbuid);
}
