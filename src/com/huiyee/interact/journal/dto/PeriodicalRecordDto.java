package com.huiyee.interact.journal.dto;

import java.util.List;

import com.huiyee.interact.journal.model.JournalContent;
import com.huiyee.interact.journal.model.JournalShareRecord;
import com.huiyee.interact.journal.model.Pager;



public class PeriodicalRecordDto implements IDto{

	private List<JournalContent> journalContent;
	private List<JournalShareRecord> journalShareRecord;
	private List<JournalShareRecord> bindsource;
	private Pager pager;

	public List<JournalContent> getJournalContent()
	{
		return journalContent;
	}

	public void setJournalContent(List<JournalContent> journalContent)
	{
		this.journalContent = journalContent;
	}

	public Pager getPager()
	{
		return pager;
	}

	public void setPager(Pager pager)
	{
		this.pager = pager;
	}

	public List<JournalShareRecord> getJournalShareRecord()
	{
		return journalShareRecord;
	}

	public void setJournalShareRecord(List<JournalShareRecord> journalShareRecord)
	{
		this.journalShareRecord = journalShareRecord;
	}

	public List<JournalShareRecord> getBindsource()
	{
		return bindsource;
	}

	public void setBindsource(List<JournalShareRecord> bindsource)
	{
		this.bindsource = bindsource;
	}
	
	
}
