package com.huiyee.interact.research.action;

import org.apache.struts2.ServletActionContext;

import com.huiyee.esite.action.InteractModelAction;
import com.huiyee.esite.model.Account;
import com.huiyee.interact.research.mgr.IResearchManager;
import com.huiyee.interact.research.model.ResearchModel;

public class ResearchPreviewAction extends InteractModelAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1247716468679806811L;
	private long rid;
	private IResearchManager researchMgr;
	private ResearchModel research;
	private long mid = 10006;
	
	
	@Override
	public String execute() throws Exception {
		Account account=(Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		long ownerid=account.getOwner().getId();
		research = researchMgr.findResearchModelById(rid, ownerid);
		return SUCCESS;
	}

	public void setResearchMgr(IResearchManager researchMgr)
	{
		this.researchMgr = researchMgr;
	}

	public ResearchModel getResearch()
	{
		return research;
	}

	public void setResearch(ResearchModel research)
	{
		this.research = research;
	}

	public long getRid()
	{
		return rid;
	}

	public void setRid(long rid)
	{
		this.rid = rid;
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
