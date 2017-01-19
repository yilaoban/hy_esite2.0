package com.huiyee.interact.cb.mgr;

import com.huiyee.interact.cb.dto.CbActivityDto;


public interface ICbActivityJlMgr
{
	public CbActivityDto findCbActivityJlRecordList(long sender,int pageId);
}
