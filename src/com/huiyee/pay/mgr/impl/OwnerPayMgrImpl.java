package com.huiyee.pay.mgr.impl;

import org.apache.struts2.ServletActionContext;

import com.huiyee.esite.model.Account;
import com.huiyee.pay.dao.IOwnerPayDao;
import com.huiyee.pay.mgr.IOwnerPayMgr;
import com.huiyee.pay.model.AliPay;


public class OwnerPayMgrImpl implements IOwnerPayMgr
{
	private IOwnerPayDao ownerPayDao;
	
	public void setOwnerPayDao(IOwnerPayDao ownerPayDao)
	{
		this.ownerPayDao = ownerPayDao;
	}
	
	@Override
	public AliPay findAliPay(String type){
		Account account=(Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		long ownerid=account.getOwner().getId();
		return ownerPayDao.findAliPay(ownerid,type);
	}
	
	
}
