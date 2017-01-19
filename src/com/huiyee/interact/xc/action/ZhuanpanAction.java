package com.huiyee.interact.xc.action;

import org.apache.struts2.ServletActionContext;

import com.huiyee.esite.action.InteractModelAction;
import com.huiyee.esite.model.Account;
import com.huiyee.interact.xc.mgr.IZhuanpanMgr;

public class ZhuanpanAction extends InteractModelAction{
	private IZhuanpanMgr zhuanpanMgr;
	
	@Override
	public String execute() throws Exception {
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		return SUCCESS;
	}

	public void setZhuanpanMgr(IZhuanpanMgr zhuanpanMgr)
	{
		this.zhuanpanMgr = zhuanpanMgr;
	}
	

}
