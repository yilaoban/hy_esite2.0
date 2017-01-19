package com.huiyee.interact.journal.dao;

import java.util.List;

import com.huiyee.interact.journal.model.JournalContent;
import com.huiyee.interact.journal.model.JournalShareRecord;



public interface IPeriodicalRecordDao {
	
	public List<JournalShareRecord> bindsource();
	
	public List<JournalContent> findJournalContentByJ(int start, int size,long jid);
	public int findPeriodicalRecordTotal(long jid);
	
	public List<JournalShareRecord> findJournalSR(int start, int size,long contentid);
	public int findJournalSRTotal(long jid);
	
	public List<JournalShareRecord> searchnickname(int start, int size,String nickname,long contentid);
	public int searchnicknameTotal(String nickname,long contentid);
	
	public List<JournalShareRecord> searchtimeorts(int start, int size,String begintime, String endtime, String terminal, String source,long jid);
	public int searchtimeortsTotal(String begintime, String endtime, String terminal, String source,long jid);
}
