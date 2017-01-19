package com.huiyee.interact.cb.mgr;

import com.huiyee.interact.cb.dto.HbRecordDto;
import com.huiyee.interact.cb.model.CbSender;


public interface IHbRecordMgr
{
	public HbRecordDto findHbRecordList(long sender,int pageId);
	
	public CbSender findHbSender(long sender);
}
