package project.caidan.dao;

import project.caidan.dto.DailiCpz;
import project.caidan.dto.ProductRMB;


public interface IKqDao
{
	   DailiCpz findChannelByWxuid(long wxuid);
	   
	   int findProductChannel(long pid,long clid);
	   
	   ProductRMB findPRMB(long pid);
	   
	   void updateRMB(long wxuid,int rmb);
	    
	   int findRMB(long wxuid);
	   
	   void updateRMBRecord(long wxuid,int rmb,String desc,int total,String source);

}
