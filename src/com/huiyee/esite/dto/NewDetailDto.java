package com.huiyee.esite.dto;

import com.huiyee.esite.model.ContentNew;
import com.huiyee.esite.model.ContentProduct;
import com.huiyee.esite.model.Page;
import com.huiyee.esite.model.PageCard;

public class NewDetailDto implements IDto{
	
	private ContentNew contentNew;
	private ContentProduct contentProduct;
	private long pageid;
	private PageCard tc;
	private Page page;
	private String cardcss;
	private long topicid;//ª∞Ã‚id
	private String source;//cateid-formid-topicid
	
	public long getTopicid() {
		return topicid;
	}
	public void setTopicid(long topicid) {
		this.topicid = topicid;
	}
	public String getCardcss() {
		return cardcss;
	}
	public void setCardcss(String cardcss) {
		this.cardcss = cardcss;
	}
	public ContentNew getContentNew()
	{
		return contentNew;
	}
	public void setContentNew(ContentNew contentNew)
	{
		this.contentNew = contentNew;
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
	
	public String getSource()
	{
		return source;
	}
	
	public void setSource(String source)
	{
		this.source = source;
	}
	public ContentProduct getContentProduct()
	{
		return contentProduct;
	}
	
	public void setContentProduct(ContentProduct contentProduct)
	{
		this.contentProduct = contentProduct;
	}
}
