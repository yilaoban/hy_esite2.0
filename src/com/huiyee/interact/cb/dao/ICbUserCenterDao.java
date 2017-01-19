package com.huiyee.interact.cb.dao;

import java.util.Date;
import java.util.List;

import com.huiyee.interact.appointment.model.AppointmentDataModel;
import com.huiyee.interact.cb.model.CbHbRecord;
import com.huiyee.interact.cb.model.CbSender;
import com.huiyee.interact.cb.model.InteractCb;

public interface ICbUserCenterDao {
	public CbSender findCbSenderByHyuid(long hyuid);
	
	public List<CbHbRecord> findHbCbSendByHyuid(long hyuid);
	
	public List<CbHbRecord> findHbCbSendByHyuidAndTime(long hyuid,Date createtime);
	
	public List<CbSender> findMySender(long hyuid);
	
	public List<CbSender> findSenderlist(long owner);
	
	public InteractCb findCbByOwner(long owner);
	
	public AppointmentDataModel findAptRecordByWxuid(long aptid, long uid, int cd);
	
	public int updateInteractAptRecord(long wxuid,AppointmentDataModel aptRecord,int type,long id);
	
	public long saveInteractAptRecord(long wxuid,AppointmentDataModel aptRecord,int type);
	
	public int saveCbHongbaoSend(long owner, long hyuid, String openid, long mpid, int rmb);
	
	public void updateCbUsed(long hyuid,int rmb);
	
	public int saveCbSender(long hyuid,long wxuid,long aptid,long recordid,String status,long owner,long fatherid);
}
