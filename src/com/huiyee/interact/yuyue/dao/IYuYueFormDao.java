package com.huiyee.interact.yuyue.dao;

import java.util.List;

import com.huiyee.interact.appointment.model.AppointmentDataModel;
import com.huiyee.interact.yuyue.model.YuYueCatalog;


public interface IYuYueFormDao
{
	public AppointmentDataModel findAptRecordByWxuid(long aptid, long uid, int cd);
	
	public List<YuYueCatalog> findYuYueCatalog(long owner,String type);
}
