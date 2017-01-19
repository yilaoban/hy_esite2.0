package com.huiyee.interact.renqi.mgr;

import com.huiyee.interact.renqi.model.FUidJf;


public interface IRenQiRecordMgr
{
	
   public void addRqDuidDraw(long rqid,long duid,long fuid,int utype,int addjf, String ip,String terminal,String source);
   
   public int findDuidDraw(long rqid,long duid,long fuid,int utype);

   public FUidJf findFuidJf(long rqid,long fuid,int utype);
   
   public void updateJf2Lottery(long rqid,long fuid,int utype,int cnum,long lid);
   
   public void addFuidShare(long rqid,long fuid,int utype,String content, String ip,String terminal,String source);
}
