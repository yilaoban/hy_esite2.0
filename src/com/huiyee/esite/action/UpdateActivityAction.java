package com.huiyee.esite.action;

import java.util.ArrayList;

import com.huiyee.esite.dto.SiteIndexActionDto;

public class UpdateActivityAction extends AbstractAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2121360597567463718L;
	private long activityid;
	private ArrayList<Long> moduleList;
	private String activityname;
	
	private SiteIndexActionDto dto;
	private String result;

	@Override
	public String execute() throws Exception {
		dto = (SiteIndexActionDto) pageCompose.composeUpdateActivity(activityid);
		return SUCCESS;
	}
	
	public String updateActivity() throws Exception {
		result = pageCompose.composeCompileActivity(activityid,moduleList,activityname);
		return SUCCESS;
	}

	public long getActivityid() {
		return activityid;
	}

	public void setActivityid(long activityid) {
		this.activityid = activityid;
	}

	public SiteIndexActionDto getDto() {
		return dto;
	}

	public void setDto(SiteIndexActionDto dto) {
		this.dto = dto;
	}

	public ArrayList<Long> getModuleList() {
		return moduleList;
	}

	public void setModuleList(ArrayList<Long> moduleList) {
		this.moduleList = moduleList;
	}

	public String getActivityname() {
		return activityname;
	}

	public void setActivityname(String activityname) {
		this.activityname = activityname;
	}

	public String getResult() {
		return result;
	}
	
}
