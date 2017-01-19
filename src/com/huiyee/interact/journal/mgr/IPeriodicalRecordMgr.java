package com.huiyee.interact.journal.mgr;

import com.huiyee.interact.journal.dto.IDto;



public interface IPeriodicalRecordMgr {
	
	public IDto findJournalContentByJ(long jid,int pageid);
	
	public IDto findJournalSR(long contentid,int pageid);
	
	public IDto searchnickname(String nickname,long contentid,int pageid);
	
	public IDto searchtimeorts(String begintime,String endtime,String terminal,String source,long contentid,int pageid);
}
