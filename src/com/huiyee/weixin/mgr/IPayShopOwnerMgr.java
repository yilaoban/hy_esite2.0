package com.huiyee.weixin.mgr;

import com.huiyee.weixin.model.Wkq;


public interface IPayShopOwnerMgr
{
  public boolean isDz(long owner, String type, long hyuid);
  
  public Wkq findWkq(long owner,int type);
}
