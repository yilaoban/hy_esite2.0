package com.huiyee.esite.action;

import org.apache.struts2.ServletActionContext;

import com.huiyee.esite.dto.SiteGroupDto;
import com.huiyee.esite.model.Account;

public class CopySuitAction extends AbstractAction
{
	private static final long serialVersionUID = 4554279779416801034L;

	private SiteGroupDto dto;
	private long siteid;
	private long pageid;
	private String result;

	public String execute() throws Exception
	{
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		dto = (SiteGroupDto) pageCompose.composeSiteGroupList(account);
		return SUCCESS;
	}

	public String saveNewSuit() throws Exception
	{
		result = pageCompose.saveNewSuit(siteid, pageid);
		return SUCCESS;
	}

	public SiteGroupDto getDto()
	{
		return dto;
	}

	public void setDto(SiteGroupDto dto)
	{
		this.dto = dto;
	}

	public long getSiteid()
	{
		return siteid;
	}

	public void setSiteid(long siteid)
	{
		this.siteid = siteid;
	}

	public long getPageid()
	{
		return pageid;
	}

	public void setPageid(long pageid)
	{
		this.pageid = pageid;
	}

	public String getResult()
	{
		return result;
	}

	public void setResult(String result)
	{
		this.result = result;
	}

}
