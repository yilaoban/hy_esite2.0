package com.huiyee.interact.checkin.dao;

import java.util.List;

import com.huiyee.interact.checkin.model.Checkin;
import com.huiyee.interact.checkin.model.CheckinData;
import com.huiyee.interact.checkin.model.CheckinRecord;

public interface ICheckinRecordDao
{
	public void addCheckIn(long wbuid, long ownerwbuid, int addnum, int daynum, long pageid, String ip, String terminal, String source,int utype);

	public CheckinRecord findLastCheckIn(long wbuid, long ownerwbuid,int utype);

	public int findCheckinDataTotal(long ownerwbuid,int utype);

	public List<CheckinData> findCheckinData(long ownerwbuid,int utype, int i, int checkDataLimit);
	
	public CheckinRecord findLastCheckInByHyuid(long hyuid);
	
	public void addCheckIn(long hyuid, int addnum, int daynum, String ip, String terminal, String source);
}
