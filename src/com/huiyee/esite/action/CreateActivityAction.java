package com.huiyee.esite.action;

import java.util.ArrayList;

import org.apache.commons.lang.StringUtils;

import com.huiyee.esite.dto.SiteIndexActionDto;
import com.huiyee.esite.model.Account;
import com.opensymphony.xwork2.ActionContext;

public class CreateActivityAction extends AbstractAction{
	private SiteIndexActionDto dto;
	private String activityname;
	private ArrayList<Long> moduleList;
	private String result;
	private long siteid;

	@Override
	public String execute() throws Exception {
		dto = (SiteIndexActionDto) pageCompose.composeCreateActivitySub();
		return SUCCESS;
	}
	
	public String saveActivity() throws Exception{
		if (StringUtils.isEmpty(activityname))
		{
			result = "活动名称为空!";
			dto = (SiteIndexActionDto) pageCompose.composeCreateActivitySub();
			return INPUT;
		}
		if (moduleList == null || moduleList.size() == 0)
		{
			result = "版块未选!";
			dto = (SiteIndexActionDto) pageCompose.composeCreateActivitySub();
			return INPUT;
		}
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		long ownerid = account.getOwner().getId();
		result = pageCompose.composeCreateActivitySub(activityname,moduleList,siteid,ownerid);
		return INPUT;
	}
	
	public SiteIndexActionDto getDto() {
		return dto;
	}

	public void setDto(SiteIndexActionDto dto) {
		this.dto = dto;
	}

	public String getActivityname() {
		return activityname;
	}

	public void setActivityname(String activityname) {
		this.activityname = activityname;
	}

	public ArrayList<Long> getModuleList() {
		return moduleList;
	}

	public void setModuleList(ArrayList<Long> moduleList) {
		this.moduleList = moduleList;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public long getSiteid() {
		return siteid;
	}

	public void setSiteid(long siteid) {
		this.siteid = siteid;
	}
	
}
