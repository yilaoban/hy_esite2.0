package com.huiyee.interact.checkin.service;

import com.huiyee.esite.model.VisitUser;
import com.huiyee.interact.checkin.dto.CheckinRs;

public interface ICheckinDrawService
{
	public CheckinRs checkin(VisitUser vu,long pageid,String ip,String terminal,String source,long owner);
}
