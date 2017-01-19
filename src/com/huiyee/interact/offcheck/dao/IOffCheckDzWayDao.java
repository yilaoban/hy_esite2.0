package com.huiyee.interact.offcheck.dao;

import com.huiyee.interact.offcheck.model.OffCheckDzWay;

public interface IOffCheckDzWayDao
{
   public OffCheckDzWay findDzWay(long  id);
   public long findDzWayId(long wxuid,long csid,int rmb);
   public void updateDzWay(long id,long wxuid,int jf);
   public void addDzWay(long wxuid,long csid,int rmb);
}
