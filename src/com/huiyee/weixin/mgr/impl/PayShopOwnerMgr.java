package com.huiyee.weixin.mgr.impl;

import com.huiyee.esite.mgr.imp.AbstractMgr;
import com.huiyee.weixin.dao.IPayShopOwnerDao;
import com.huiyee.weixin.mgr.IPayShopOwnerMgr;
import com.huiyee.weixin.model.Wkq;

public class PayShopOwnerMgr extends AbstractMgr implements IPayShopOwnerMgr
{
private IPayShopOwnerDao payShopOwnerDao;

public void setPayShopOwnerDao(IPayShopOwnerDao payShopOwnerDao)
{
	this.payShopOwnerDao = payShopOwnerDao;
}

@Override
public Wkq findWkq(long owner,int type)
{
	return payShopOwnerDao.findWkq(owner,type);
}

}
