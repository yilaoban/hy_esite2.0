package com.huiyee.interact.journal.dto;

import java.util.List;

import com.huiyee.interact.journal.model.JournalContent;
import com.huiyee.interact.journal.model.JournalModel;
import com.huiyee.interact.journal.model.Pager;

public class JournalDto implements IDto
{

	private List<JournalModel> list;
	private Pager pager;
	private List<JournalContent> journalContentList;
	private JournalContent journal;
	
	public List<JournalModel> getList()
	{
		return list;
	}
	public void setList(List<JournalModel> list)
	{
		this.list = list;
	}
	public Pager getPager()
	{
		return pager;
	}
	public void setPager(Pager pager)
	{
		this.pager = pager;
	}
	public List<JournalContent> getJournalContentList()
	{
		return journalContentList;
	}
	public void setJournalContentList(List<JournalContent> journalContentList)
	{
		this.journalContentList = journalContentList;
	}
	public JournalContent getJournal()
	{
		return journal;
	}
	public void setJournal(JournalContent journal)
	{
		this.journal = journal;
	}
	
}
