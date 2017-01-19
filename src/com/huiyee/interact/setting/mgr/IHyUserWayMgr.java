package com.huiyee.interact.setting.mgr;

import com.huiyee.esite.model.VisitUser;
import com.huiyee.interact.setting.dto.CashierDto;
import com.huiyee.interact.setting.dto.CashierRs;
import com.huiyee.interact.setting.model.UWay;


public interface IHyUserWayMgr
{
  CashierRs updateUserWay(VisitUser vu,long owner,String starturl);
  
  CashierRs updateWayStatus(VisitUser vu,long owner,String starturl,int rmb,long id,long endtime,String hydesc,long xfid);
  
  public CashierDto findWay(long id,long endtime,int status);
  
  public UWay findUWayById(long id);
  
  public boolean findHyUserIdentity(long owner, String type,long hyuid);
}
