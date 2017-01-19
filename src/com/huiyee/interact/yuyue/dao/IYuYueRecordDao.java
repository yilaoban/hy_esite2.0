package com.huiyee.interact.yuyue.dao;

import java.util.Date;

import com.huiyee.interact.appointment.model.AppointmentDataModel;
import com.huiyee.interact.yuyue.model.YuYueSSTimeUsed;


public interface IYuYueRecordDao
{
	public int updateInteractAptRecord(long wxuid,AppointmentDataModel aptRecord,int type,long id);
	
	public long saveInteractAptRecord(long wxuid,AppointmentDataModel aptRecord,int type,long pageid);
	
	public YuYueSSTimeUsed findYuYueTimeUsed(int dateday,long stid,long ssid,long owner);
	
	public long saveYuYueTimeUsed(int dateday,long stid,long ssid,long owner);

	public int updateYuYueTimeUsedById(long id);
	
	public int saveYuYueRecord(long wxuid,String ip,long stid,Date yytime,String hydesc,String servicename,String sername,long serid,long serviceid,long catid,String catname,String tag1,String tag2);
}
