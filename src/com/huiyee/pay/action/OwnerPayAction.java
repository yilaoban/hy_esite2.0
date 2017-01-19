package com.huiyee.pay.action;

import com.huiyee.esite.action.InteractModelAction;
import com.huiyee.pay.mgr.IOwnerPayMgr;


public class OwnerPayAction extends InteractModelAction
{
	private IOwnerPayMgr ownerPayMgr;
	private Boolean doWxPay = false;
	
	public void setOwnerPayMgr(IOwnerPayMgr ownerPayMgr)
	{
		this.ownerPayMgr = ownerPayMgr;
	}
	
	@Override
	public String execute() throws Exception
	{
		return SUCCESS;
	}

	public Boolean getDoWxPay()
	{
		return doWxPay;
	}

	
	public void setDoWxPay(Boolean doWxPay)
	{
		this.doWxPay = doWxPay;
	}
	
}
