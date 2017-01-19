package com.huiyee.interact.journal.dao;

import java.util.List;

import com.huiyee.interact.journal.model.JournalContent;
import com.huiyee.interact.journal.dto.JournalDto;
import com.huiyee.interact.journal.model.JournalModel;

public interface IJournalDao
{
	
	public List<JournalModel> findJournalList(long ownerid,int start,int size);
	
	public int findJournalTotal(long ownerid);
	
	public JournalModel findJournalModelById(long id, long ownerid);
	
	public List<JournalContent> findJournalContentByJid(long jid);
	
	public List<JournalContent> findJournalContentList(long jid,int start,int size);
	
	public int findJournalContentTotal(long jid);
	
	public long saveJournalContent(long jid,JournalDto dto);
	
	public JournalContent findJournalContentById(long id);
	
	public long updateJournalContent(long id,JournalDto dto);
	
	public long updateJournalShareContent(long id,JournalDto dto);
	
	public int saveJournal(long ownerid,String title,String isshare,int balance);
	
	public int updateJournal(long id,String title,String isshare,int balance);
	
	public JournalModel findOneJournal(long id);
	
	public int deleteJournal(long id);
	
	public int saveJournalRecord(long pageid,long wbuid,long contentid,String wbid,String content,String pic,String ip,String terminal,String source);
	
	public int updateContentCount(long contentid);
	
	public int deleteJournalContent(long id);
	
	public int findBalanceByContentId(long contentid);

}
