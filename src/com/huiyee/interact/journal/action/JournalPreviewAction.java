package com.huiyee.interact.journal.action;

import org.apache.struts2.ServletActionContext;

import com.huiyee.esite.action.InteractModelAction;
import com.huiyee.esite.model.Account;
import com.huiyee.interact.journal.mgr.IJournalMgr;
import com.huiyee.interact.journal.model.JournalModel;

public class JournalPreviewAction extends InteractModelAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 939699821123570075L;
	private long jid;
	
	private IJournalMgr journalMgr;
	private JournalModel journal;
	
	@Override
	public String execute() throws Exception {
		long ownerid = ((Account)ServletActionContext.getRequest().getSession().getAttribute("account")).getOwner().getId();
		journal = journalMgr.findJournalModelById(jid, ownerid);
		return SUCCESS;
	}

	public void setJournalMgr(IJournalMgr journalMgr) {
		this.journalMgr = journalMgr;
	}

	public JournalModel getJournal() {
		return journal;
	}

	public long getJid() {
		return jid;
	}

	public void setJid(long jid) {
		this.jid = jid;
	}

}
