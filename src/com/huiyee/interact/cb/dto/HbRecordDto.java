package com.huiyee.interact.cb.dto;

import java.util.List;

import com.huiyee.interact.cb.model.CbHbRecord;
import com.huiyee.interact.cb.model.CbSender;
import com.huiyee.interact.cb.model.InteractCb;


public class HbRecordDto extends CbRsDto
{
	private List<CbHbRecord> cbHbRecordList;
	private CbSender cbSender;
	private int pageId;
	private double total;//×Ü¶î
	private double balance;//Óà¶î
	private List<CbSender> cbSenderList;
	private InteractCb cb;

	public InteractCb getCb() {
		return cb;
	}

	public void setCb(InteractCb cb) {
		this.cb = cb;
	}

	public List<CbSender> getCbSenderList() {
		return cbSenderList;
	}

	public void setCbSenderList(List<CbSender> cbSenderList) {
		this.cbSenderList = cbSenderList;
	}


	public int getPageId()
	{
		return pageId;
	}

	
	public void setPageId(int pageId)
	{
		this.pageId = pageId;
	}

	public List<CbHbRecord> getCbHbRecordList()
	{
		return cbHbRecordList;
	}
	
	public void setCbHbRecordList(List<CbHbRecord> cbHbRecordList)
	{
		this.cbHbRecordList = cbHbRecordList;
	}
	
	public CbSender getCbSender()
	{
		return cbSender;
	}
	
	public void setCbSender(CbSender cbSender)
	{
		this.cbSender = cbSender;
	}


	
	public double getTotal()
	{
		return total;
	}


	
	public void setTotal(double total)
	{
		this.total = total;
	}


	
	public double getBalance()
	{
		return balance;
	}


	
	public void setBalance(double balance)
	{
		this.balance = balance;
	}

	
}
