package com.huiyee.interact.checkin.dto;

import java.util.List;

import com.huiyee.interact.checkin.model.Checkin;
import com.huiyee.interact.checkin.model.CheckinData;
import com.huiyee.interact.checkin.model.CheckinRecord;

public class CheckinDto implements IDto
{
	private List<Checkin> list;
	private Checkin checkin;
	private List<CheckinData> record;
	private Pager pager;
	private String type;
	private List<CheckinRecord> checkinRecordList;
	
	public List<CheckinRecord> getCheckinRecordList()
	{
		return checkinRecordList;
	}

	public void setCheckinRecordList(List<CheckinRecord> checkinRecordList)
	{
		this.checkinRecordList = checkinRecordList;
	}

	public Checkin getCheckin()
	{
		return checkin;
	}

	public void setCheckin(Checkin checkin)
	{
		this.checkin = checkin;
	}

	public List<Checkin> getList()
	{
		return list;
	}

	public void setList(List<Checkin> list)
	{
		this.list = list;
	}

	public List<CheckinData> getRecord()
	{
		return record;
	}

	public void setRecord(List<CheckinData> record)
	{
		this.record = record;
	}

	public Pager getPager()
	{
		return pager;
	}

	public void setPager(Pager pager)
	{
		this.pager = pager;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}
	
}
