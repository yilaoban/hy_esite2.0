package com.huiyee.interact.exam.dto;

import com.huiyee.esite.model.Page;
import com.huiyee.esite.model.PageCard;
import com.huiyee.interact.exam.model.ExamResult;

public class PingceResultDto implements IDto{
	
	private long pageid;
	private PageCard tc;
	private Page page;
	private String cardcss;
	private ExamResult result;
	
	public ExamResult getResult()
	{
		return result;
	}
	
	public void setResult(ExamResult result)
	{
		this.result = result;
	}
	public String getCardcss() {
		return cardcss;
	}
	public void setCardcss(String cardcss) {
		this.cardcss = cardcss;
	}
	public long getPageid()
	{
		return pageid;
	}
	public void setPageid(long pageid)
	{
		this.pageid = pageid;
	}
	public PageCard getTc() {
		return tc;
	}
	public void setTc(PageCard tc) {
		this.tc = tc;
	}
	public Page getPage()
	{
		return page;
	}
	public void setPage(Page page)
	{
		this.page = page;
	}
}
