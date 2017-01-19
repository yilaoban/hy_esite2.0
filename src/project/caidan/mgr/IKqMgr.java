package project.caidan.mgr;

import com.huiyee.esite.model.VisitUser;

import project.caidan.dto.DailiCpz;
import project.caidan.dto.ProductRMB;

public interface IKqMgr
{
   DailiCpz findChannelByWxuid(long wxuid);
   
   ProductRMB findPRMB(long pid);
   
   int findProductChannel(long pid,long clid);
   
   void updateRMB(VisitUser vu,DailiCpz dc,long orderid,long pogid,String productname,ProductRMB pr, String uuid, String onameurl,int ptype,long owner);
   
   
}
