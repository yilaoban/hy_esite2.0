package com.huiyee.interact.spread.dao;

import java.util.List;

import com.huiyee.interact.spread.model.SpreadRecord;



public interface ISpreadRecordDao
{
	
	public List<SpreadRecord> findSpreadrecordList(int start, int size,long spreadid, String begintime, String endtime);
	public int findspreadRecordTotal(long spreadid, String begintime, String endtime);
	
	
	public int findspreadRecordTotalWx(long spreadid, String begintime, String endtime);
	public List<SpreadRecord> findSpreadrecordListWx(int i, int spreadrecordLimit, long spreadid, String begintime, String endtime);
	
}
