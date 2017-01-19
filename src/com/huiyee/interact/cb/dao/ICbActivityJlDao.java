package com.huiyee.interact.cb.dao;

import java.util.List;

import com.huiyee.interact.cb.model.CbActivityJlRecord;


public interface ICbActivityJlDao
{
	public int findTotalCbActivityJlRecordList(long sender);
	
	public List<CbActivityJlRecord> findCbActivityJlRecordList(long sender,int start,int size);
	
}
