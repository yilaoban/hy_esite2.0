package com.huiyee.interact.EmailPeriodical.dto;

import java.util.List;

import com.huiyee.interact.EmailPeriodical.model.EmailPeriodicalModel;
import com.huiyee.interact.EmailPeriodical.model.Pager;


public class EmailPeriodicalDto implements IDto{

	private List<EmailPeriodicalModel> list;
	private EmailPeriodicalModel emailPeriodical;
	private Pager pager;
	private int total;
	
	public List<EmailPeriodicalModel> getList() {
		return list;
	}
	public void setList(List<EmailPeriodicalModel> list) {
		this.list = list;
	}
	public Pager getPager() {
		return pager;
	}
	public void setPager(Pager pager) {
		this.pager = pager;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public EmailPeriodicalModel getEmailPeriodical()
	{
		return emailPeriodical;
	}
	public void setEmailPeriodical(EmailPeriodicalModel emailPeriodical)
	{
		this.emailPeriodical = emailPeriodical;
	}
	
	
}
