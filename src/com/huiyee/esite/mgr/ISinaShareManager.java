package com.huiyee.esite.mgr;

import java.util.List;

import com.huiyee.esite.model.SinaShare;

public interface ISinaShareManager {

	public List<SinaShare> findTask();

	public void updateStatus(long id, String status);

	public List<SinaShare> findSinaShareBySiteGroup(long sitegroupid, long owner);
	
	public int findSinaShareTotal(long shareid);
	
	public SinaShare findSinaShareById(long shareid);
}
