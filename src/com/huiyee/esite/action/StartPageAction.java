package com.huiyee.esite.action;

import com.huiyee.esite.dto.PageActionDto;

public class StartPageAction extends AbstractAction{
	private PageActionDto dto ;
	private long sitegroupid;
	@Override
	public String execute() throws Exception {
		dto = (PageActionDto) pageCompose.findSiteNames(sitegroupid);
		return SUCCESS;
	}

	public long getSitegroupid() {
		return sitegroupid;
	}

	public void setSitegroupid(long sitegroupid) {
		this.sitegroupid = sitegroupid;
	}

	public PageActionDto getDto() {
		return dto;
	}
	public void setDto(PageActionDto dto) {
		this.dto = dto;
	}
	
}
