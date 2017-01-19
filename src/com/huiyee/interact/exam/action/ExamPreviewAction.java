package com.huiyee.interact.exam.action;

import org.apache.struts2.ServletActionContext;

import com.huiyee.esite.action.InteractModelAction;
import com.huiyee.esite.model.Account;
import com.huiyee.interact.exam.mgr.IExamManager;
import com.huiyee.interact.exam.model.ExamModel;

public class ExamPreviewAction extends InteractModelAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1247716468679806811L;
	private long rid;
	private IExamManager examMgr;
	private ExamModel exam;
	private long mid = 10006;
	
	
	@Override
	public String execute() throws Exception {
		Account account=(Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		long ownerid=account.getOwner().getId();
		exam = examMgr.findExamModelById(rid, ownerid);
		return SUCCESS;
	}

	public void setExamMgr(IExamManager examMgr)
	{
		this.examMgr = examMgr;
	}

	public ExamModel getExam()
	{
		return exam;
	}

	public void setExam(ExamModel exam)
	{
		this.exam = exam;
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
