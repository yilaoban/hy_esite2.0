package com.huiyee.interact.cb.dao;

import java.util.List;

import com.huiyee.interact.cb.model.CbHbRecord;
import com.huiyee.interact.cb.model.CbSender;

public interface IHbRecordDao
{
	public int findTotalHbRecordList(long sender);
	
	public List<CbHbRecord> findHbRecordList(long sender,int start,int size);
	
	public CbSender findHbSender(long sender);
	
	public int findTotalByCbid(long owner, long sender, String name, String starttime, String endtime);

	public List<CbHbRecord> findHbRecord(long cbid, long sender, String name, String starttime, String endtime, int start, int size);

	public int updateCbSendCheck(long id, int status);
}
