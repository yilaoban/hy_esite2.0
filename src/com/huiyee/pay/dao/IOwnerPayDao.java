package com.huiyee.pay.dao;

import com.huiyee.pay.model.AliPay;


public interface IOwnerPayDao
{
	public AliPay findAliPay(long ownerid,String type);
}
