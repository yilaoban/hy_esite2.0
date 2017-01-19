package com.huiyee.esite.dto.showdto;

import java.io.Serializable;

import com.huiyee.esite.dto.IDto;
import com.huiyee.esite.model.JournalModel;

public class Show140Dto implements IDto, Serializable{

	private static final long serialVersionUID = 5599750938161888136L;

	private long fid;
	private JournalModel journal;
	public long getFid()
	{
		return fid;
	}
	public void setFid(long fid)
	{
		this.fid = fid;
	}
	public JournalModel getJournal()
	{
		return journal;
	}
	public void setJournal(JournalModel journal)
	{
		this.journal = journal;
	}
	
}
