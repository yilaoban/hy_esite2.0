package com.huiyee.esite.mgr.imp;

import java.util.List;

import com.huiyee.esite.dao.ISiteDao;
import com.huiyee.esite.dao.IVisitDao;
import com.huiyee.esite.mgr.IVisitMgr;
import com.huiyee.esite.model.VisitLog;
import com.huiyee.esite.model.VisitUser;

public class VisitMgrImpl implements IVisitMgr
{
	private IVisitDao visitDao;
	private ISiteDao siteDao;

	public void setSiteDao(ISiteDao siteDao)
	{
		this.siteDao = siteDao;
	}

	public void setVisitDao(IVisitDao visitDao)
	{
		this.visitDao = visitDao;
	}
	
	@Override
	public void insertVisitLog(long siteid,long pageid, VisitUser vu, String ip,String agent,String term,String key)
	{
		visitDao.insertVisitLog(siteid, pageid,vu, ip,agent,term,key);
//		siteDao.updateSiteAction(siteid, "N");
	}

	@Override
	public void insertVisitLogAnonymous(long siteid, long pageid,String ip,String agent,String term,String key)
	{
		visitDao.insertVisitLogAnonymous(siteid,pageid, ip,agent,term,key);
//		siteDao.updateSiteAction(siteid, "A");
	}

	@Override
	public List<VisitLog> findVistLogBySiteGidAndtype(long sitegid, String type, int page)
	{
		return visitDao.findVistLogBySiteGidAndtype(sitegid, type, page);
	}
	   @Override
    public List<VisitLog> findVistLogBySiteAndtype(long siteid, String type, int page)
    {
        return visitDao.findVistLogBySiteAndtype(siteid, type, page);
    }

	@Override
	public int findTotalVistLogBySiteidAndtype(long siteid, String type)
	{
		return visitDao.findTotalVistLogBySiteidAndtype(siteid, type);
	}
	@Override
    public int findTotalVistLogBySiteAndtype(long siteid, String type)
    {
        return visitDao.findTotalVistLogBySiteAndtype(siteid, type);
    }
	
	@Override
	public List<VisitLog> findVistLogExport(long sitegroupid, String type) {
		return visitDao.findVistLogExport(sitegroupid,type);
	}

	@Override
	public List<VisitLog> findVisitLogNotProcess(int size) {
		return visitDao.findVisitLogNotProcess(size);
	}

}
