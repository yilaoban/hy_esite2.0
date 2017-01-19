package com.huiyee.interact.cb.mgr;

import com.huiyee.interact.appointment.model.AppointmentDataModel;
import com.huiyee.interact.cb.dto.HbRecordDto;

public interface ICbUserCenterMgr {
	public HbRecordDto findCbSenderByHyuid(long hyuid,long owner);
	
	public HbRecordDto findHbCbSendByHyuid(long hyuid);
	
	public HbRecordDto findMySender(long hyuid,long pageid,long owner);
	
	public HbRecordDto findSenderlist(long owner);
	
	public int saveCbSenderAndRecord(long hyuid,long owner,long wxuid,AppointmentDataModel apt,long fatherid);
	
	public int saveCbHongbaoSend(long owner,long hyuid,String openid,long mpid,int rmb);
	
	public HbRecordDto findMyHuobi(long hyuid,long owner);
}
