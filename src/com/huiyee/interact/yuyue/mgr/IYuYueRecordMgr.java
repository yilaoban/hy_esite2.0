package com.huiyee.interact.yuyue.mgr;

import java.util.Date;

import com.huiyee.esite.model.VisitUser;
import com.huiyee.interact.appointment.model.AppointmentDataModel;
import com.huiyee.interact.yuyue.dto.IDto;


public interface IYuYueRecordMgr
{
	public IDto saveYuYueRecord(VisitUser visit,long owner,AppointmentDataModel aptRecord,long catid,long serid,Date yytime,String ip,String hydesc,long pageid,long serviceid,String type,String onameurl,String tag1,String tag2);
}
