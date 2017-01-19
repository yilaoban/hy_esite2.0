package com.huiyee.interact.checkin.mgr;

import com.huiyee.interact.checkin.dto.IDto;
import com.huiyee.interact.checkin.model.CheckinRecord;

public interface ICheckinRecordMgr
{
  public CheckinRecord findLastCheckIn(long wbuid,long ownerwbuid,int utype);
  
  public void addCheckIn(long wbuid,long ownerwbuid,int addnum,int daynum,long pageid, String ip, String terminal, String source,int utype);

	public IDto findCheckinData(long ownerwbuid,int utype, int pageid);
	
	public CheckinRecord findLastCheckInByHyuid(long hyuid);
	
	public void addCheckIn(long hyuid,int addnum,int daynum,String ip, String terminal, String source);
}
