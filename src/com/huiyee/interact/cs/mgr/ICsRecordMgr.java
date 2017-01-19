package com.huiyee.interact.cs.mgr;

import com.huiyee.interact.cs.dto.CsDataDto;
import com.huiyee.interact.cs.dto.IDto;


public interface ICsRecordMgr
{
	 public long addCsFuidDraw(long csid,long fuid,int utype,String jcon, String ip,String terminal,String source);

	public IDto findCsRecord(long csid, int type, int pageId);	
}
