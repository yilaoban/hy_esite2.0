package com.huiyee.esite.dao;

import java.util.List;

import com.huiyee.esite.model.SinaShare;

public interface ISinaShareDao {
	
	public List<SinaShare> findTask();

	public void updateStatus(long id, String status);
	
	public int updateShareCount(long shareid);

	public List<SinaShare> findSinaShareBySiteGroup(long sitegroupid, long owner);
	
	public int findSinaShareTotal(long shareid);
	
	public SinaShare findSinaShareById(long shareid);
	
}
