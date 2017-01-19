package com.huiyee.interact.journal.mgr;

import com.huiyee.interact.journal.dto.IDto;
import com.huiyee.interact.journal.dto.JournalDto;
import com.huiyee.interact.journal.model.JournalModel;

public interface IJournalMgr
{

	public IDto findJournalList(long ownerid, int start, int size);
	
	public int findJournalTotal(long ownerid);
	
	public JournalModel findJournalModelById(long id,long ownerid);
	
	public IDto findJournalContentList(long jid,int pageId);
	
	public long saveJournalContent(long jid,JournalDto dto);
	
	public IDto findJournalContentById(long id);
	
	public long updateJournalContent(long id,JournalDto dto);
	
	public long updateJournalShareContent(long id,JournalDto dto);
	
	public int saveJournal(long ownerid,JournalModel jm);
	
	public int updateJournal(long id,JournalModel jm);
	
	public JournalModel findOneJournal(long id);
	
	public int deleteJournal(long id);
	
	public int saveJournalRecrod(long pageid,long wbuid,long contentid,String content,String pic,String ip,String terminal,String source,long cid);
	
	public int deleteJournalContent(long id);

}
