package com.huiyee.interact.cb.mgr;

import com.huiyee.esite.model.VisitUser;
import com.huiyee.interact.cb.dto.ActivityMatterDto;
import com.huiyee.interact.cb.model.CbSender;


public interface IActivityMatterMgr
{
	public ActivityMatterDto findActivityMatterList(long cbid,int pageId);
	
	public ActivityMatterDto findActivityMatterChildList(long cbid,long aid,long pageid);
	
	public ActivityMatterDto findActivityMatterDetail(long owenr,long id,long pageid,long wxuid);

	public CbSender findSenderStatus(VisitUser vu, long owner);
}
