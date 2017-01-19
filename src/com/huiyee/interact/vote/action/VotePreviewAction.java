package com.huiyee.interact.vote.action;

import org.apache.struts2.ServletActionContext;

import com.huiyee.esite.action.InteractModelAction;
import com.huiyee.esite.model.Account;
import com.huiyee.interact.vote.mgr.IVoteMgr;
import com.huiyee.interact.vote.model.InteractVote;

public class VotePreviewAction extends InteractModelAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6608654736686302073L;
	private long voteid;
	private IVoteMgr voteMgr;
	private InteractVote vote;
	private long mid=10002;
	
	@Override
	public String execute() throws Exception {
		Account account=(Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		long ownerid=account.getOwner().getId();
		vote= voteMgr.findVoteModelWithOptionsById(voteid, ownerid);
		return SUCCESS;
	}

	public long getVoteid() {
		return voteid;
	}

	public void setVoteid(long voteid) {
		this.voteid = voteid;
	}

	public void setVoteMgr(IVoteMgr voteMgr) {
		this.voteMgr = voteMgr;
	}

	public InteractVote getVote() {
		return vote;
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
