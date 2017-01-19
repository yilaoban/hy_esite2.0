package com.huiyee.interact.offcheck.mgr;

import com.huiyee.esite.model.VisitUser;
import com.huiyee.interact.offcheck.dto.CheckRs;


public interface IOffCheckDzWayMgr
{
   public CheckRs updateDzWay(VisitUser vu,int rmb,long owner,long csid);
}
