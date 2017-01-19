package com.huiyee.interact.spread.action;

import org.apache.struts2.ServletActionContext;

import com.huiyee.esite.action.InteractModelAction;
import com.huiyee.esite.model.Account;
import com.huiyee.interact.spread.mgr.ISpreadManager;
import com.huiyee.interact.spread.model.SpreadModel;

public class SpreadPreviewAction  extends InteractModelAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7682088271654052113L;
	private long spreadid;
	private SpreadModel spread;
	private long mid = 10010;
	
	private ISpreadManager spreadManager;
	
	public long getSpreadid() {
		return spreadid;
	}

	public void setSpreadid(long spreadid) {
		this.spreadid = spreadid;
	}

	@Override
	public String execute() throws Exception {
		long ownerid = ((Account)ServletActionContext.getRequest().getSession().getAttribute("account")).getOwner().getId();
		spread = spreadManager.previewSpread(spreadid, ownerid);
		return SUCCESS;
	}

	public void setSpreadManager(ISpreadManager spreadManager) {
		this.spreadManager = spreadManager;
	}

	public SpreadModel getSpread() {
		return spread;
	}

	public void setSpread(SpreadModel spread) {
		this.spread = spread;
	}

	public long getMid()
	{
		return mid;
	}

	public void setMid(long mid)
	{
		this.mid = mid;
	}

}
