package com.huiyee.interact.offcheck.dao;

import java.util.List;

import com.huiyee.interact.offcheck.model.OffCheckLog;
import com.huiyee.interact.offcheck.model.OffCheckRecord;

import net.sf.json.JSONObject;


public interface IOffCheckRecordDao
{

	public int findRecordTotal(long owner, JSONObject sift);

	public List<OffCheckRecord> findRecordList(long owner, JSONObject sift, int start, int size);

	public int findLogTotal(long owner, long sourceid, long wxuid);

	public List<OffCheckLog> findLogs(long owner, long sourceid, long wxuid, int i, int j);
	
	public long findLogCurDate(long wxuid,long sourceid);
	
	public void updateLogCurDate(long id,String area,String ip);
	
	public void addRecord(long wxuid,String ip,long sid,String area,int num);
	
	public void addRecordLog(long wxuid,String ip,long sid,String area);

	public int findLogTotal(long owner, JSONObject sift);

	public List<OffCheckLog> findLogs(long owner, int start, int size, JSONObject sift);

}
