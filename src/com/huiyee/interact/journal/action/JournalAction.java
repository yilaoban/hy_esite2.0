package com.huiyee.interact.journal.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.huiyee.esite.action.InteractModelAction;
import com.huiyee.esite.constants.IInteractConstants;
import com.huiyee.esite.model.Account;
import com.huiyee.interact.journal.dto.JournalDto;
import com.huiyee.interact.journal.mgr.IJournalMgr;
import com.huiyee.interact.journal.model.JournalModel;
import com.huiyee.interact.journal.model.Pager;

public class JournalAction extends InteractModelAction
{

	private IJournalMgr journalMgr;
	private int pageId=1;
	private JournalDto dto;
	private JournalModel jm;
	private String result;
	private int resultid;
	private long id;
	

	public JournalModel getJm()
	{
		return jm;
	}

	public void setJm(JournalModel jm)
	{
		this.jm = jm;
	}

	public String getResult()
	{
		return result;
	}

	public void setResult(String result)
	{
		this.result = result;
	}

	public int getResultid()
	{
		return resultid;
	}

	public void setResultid(int resultid)
	{
		this.resultid = resultid;
	}

	public IJournalMgr getJournalMgr()
	{
		return journalMgr;
	}

	public void setJournalMgr(IJournalMgr journalMgr)
	{
		this.journalMgr = journalMgr;
	}
	
	public String execute()throws Exception{
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		long ownerid = account.getOwner().getId();
		int start=(pageId - 1) *IInteractConstants.JOURNAL_LIMIT;
		int total=journalMgr.findJournalTotal(ownerid);
		dto=(JournalDto) journalMgr.findJournalList(ownerid, start, IInteractConstants.JOURNAL_LIMIT);
		Pager pager=new Pager(pageId, total, IInteractConstants.JOURNAL_LIMIT);
		dto.setPager(pager);
		return SUCCESS;
	}
	
	public String saveJournal()throws Exception{
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		long ownerid = account.getOwner().getId();
		resultid=journalMgr.saveJournal(ownerid, jm);
		if(resultid>0){
			result="Y";
		}
		return SUCCESS;
	}
	
	public String updateJournal()throws Exception{
		resultid=journalMgr.updateJournal(id, jm);
		if(resultid>0){
			result="Y";
		}
		return SUCCESS;
	}
	
	public String findOneJournal()throws Exception{
		jm=journalMgr.findOneJournal(id);
		return SUCCESS;
	}
	
	public String deleteJournal()throws Exception{
		int len=journalMgr.deleteJournal(id);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.write(len);
		out.flush();
		out.close();
		return null;
	}
	
	public JournalDto getDto()
	{
		return dto;
	}

	public void setDto(JournalDto dto)
	{
		this.dto = dto;
	}

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}
}
