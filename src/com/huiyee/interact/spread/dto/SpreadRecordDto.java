package com.huiyee.interact.spread.dto;

import java.util.List;

import com.huiyee.interact.spread.model.Pager;
import com.huiyee.interact.spread.model.SpreadRecord;

public class SpreadRecordDto implements IDto {
	
	private List<SpreadRecord> spreadRecord;
	private Pager pager;
	
	public List<SpreadRecord> getSpreadRecord()
	{
		return spreadRecord;
	}

	public void setSpreadRecord(List<SpreadRecord> spreadRecord)
	{
		this.spreadRecord = spreadRecord;
	}

	public Pager getPager()
	{
		return pager;
	}

	public void setPager(Pager pager)
	{
		this.pager = pager;
	}
	
	
}
