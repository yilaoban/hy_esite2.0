package com.huiyee.weixin.dao;

import com.huiyee.weixin.model.Wkq;


public interface IPayShopOwnerDao
{
	 public Wkq findWkq(long owner,int type);
}
