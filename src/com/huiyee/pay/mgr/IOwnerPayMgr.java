package com.huiyee.pay.mgr;

import com.huiyee.pay.model.AliPay;


public interface IOwnerPayMgr {
	
	public AliPay findAliPay(String type);
	
}
