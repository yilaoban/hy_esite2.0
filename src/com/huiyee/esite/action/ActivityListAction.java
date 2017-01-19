package com.huiyee.esite.action;

import org.apache.struts2.ServletActionContext;

import com.huiyee.esite.dto.SiteIndexActionDto;
import com.huiyee.esite.model.Account;

public class ActivityListAction extends AbstractAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1358112335813834434L;
	private long siteid;
	private SiteIndexActionDto dto;
	
	@Override
	public String execute() throws Exception {
		dto = (SiteIndexActionDto) pageCompose.composeFindActivityList(siteid);
		return SUCCESS;
	}

	public SiteIndexActionDto getDto() {
		return dto;
	}

	public void setDto(SiteIndexActionDto dto) {
		this.dto = dto;
	}

	public long getSiteid() {
		return siteid;
	}

	public void setSiteid(long siteid) {
		this.siteid = siteid;
	}
	
	
}
