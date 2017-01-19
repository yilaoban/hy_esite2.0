package com.huiyee.individual.dto;

import java.util.List;

import com.huiyee.individual.model.SecurityCodeRecord;

public class SecurityCodeRecordDto implements IDto{
	private List<SecurityCodeRecord> SCodeRecord;
	private List<SecurityCodeRecord> List;

	public List<SecurityCodeRecord> getSCodeRecord()
	{
		return SCodeRecord;
	}

	public void setSCodeRecord(List<SecurityCodeRecord> codeRecord)
	{
		SCodeRecord = codeRecord;
	}

	public List<SecurityCodeRecord> getList()
	{
		return List;
	}

	public void setList(List<SecurityCodeRecord> list)
	{
		List = list;
	}
	
}
