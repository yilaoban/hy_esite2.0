package com.huiyee.interact.cb.mgr;

import java.util.Map;

import com.huiyee.esite.dto.HdRsDto;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.model.AppointmentRecord;
import com.huiyee.esite.model.VisitUser;
import com.huiyee.interact.appointment.model.AppointmentModel;
import com.huiyee.interact.cb.model.InteractCb;


public interface IInteractCbMgr
{
	public InteractCb findCbbyOwner(long owner);
	
	public InteractCb findCbbyid(Account account, long cbid);
	
	public InteractCb findOnlyCbbyid( long cbid);

	public AppointmentModel findAppointById(long aptid);

	public HdRsDto saveCustomCommentRepotr(VisitUser visit, AppointmentRecord aptRecord, String ip, String mediaDevice, String source, long relationid, AppointmentModel apt, long owner);

	public int updateAptAcpt(Account account, int status);

	public int updateUsedSiteGroup(long owner, Map<String, Long> sql);

}
