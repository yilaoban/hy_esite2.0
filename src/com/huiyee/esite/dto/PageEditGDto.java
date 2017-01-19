package com.huiyee.esite.dto;

import com.huiyee.esite.model.Page;

public class PageEditGDto implements IDto
{
	private long pageid;
	private Page page;
	public long getPageid() {
		return pageid;
	}
	public void setPageid(long pageid) {
		this.pageid = pageid;
	}
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	
	
}
