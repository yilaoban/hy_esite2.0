package com.huiyee.esite.dto;

import java.io.Serializable;
import java.util.List;

import com.huiyee.esite.model.JournalModel;

public class Feature140Dto implements IDto , Serializable {

	private static final long serialVersionUID = 6340049420653292189L;
	
	private List<JournalModel> journalList;
	private long jid;
	private long fid;
	
	
	public List<JournalModel> getJournalList()
	{
		return journalList;
	}

	public void setJournalList(List<JournalModel> journalList)
	{
		this.journalList = journalList;
	}

	public long getJid()
	{
		return jid;
	}

	public void setJid(long jid)
	{
		this.jid = jid;
	}

	public long getFid()
	{
		return fid;
	}

	public void setFid(long fid)
	{
		this.fid = fid;
	}
	
	
}
