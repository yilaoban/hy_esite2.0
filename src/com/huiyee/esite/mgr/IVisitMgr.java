package com.huiyee.esite.mgr;

import java.util.List;

import com.huiyee.esite.model.VisitLog;
import com.huiyee.esite.model.VisitUser;

public interface IVisitMgr
{
    public void insertVisitLogAnonymous(long siteid,long pageid,String ip,String agent,String term,String key);
	
	public void insertVisitLog(long siteid,long pageid,VisitUser vu,String ip,String agent,String term,String key);

	public List<VisitLog> findVistLogBySiteGidAndtype(long siteid, String type, int page);

	public int findTotalVistLogBySiteidAndtype(long siteid, String type);
	
	public List<VisitLog> findVistLogBySiteAndtype(long siteid, String type, int page);
	
	public int findTotalVistLogBySiteAndtype(long siteid, String type);

	public List<VisitLog> findVistLogExport(long sitegroupid, String type);
	
	public List<VisitLog> findVisitLogNotProcess(int size);
}
