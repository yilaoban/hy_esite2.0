package com.huiyee.esite.action;

import com.huiyee.esite.dto.HdAreaDto;
import com.huiyee.esite.dto.HighChartsDataDto;
import com.huiyee.esite.model.Account;
import org.apache.struts2.ServletActionContext;

public class HdAreaAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HdAreaDto dto;
	private long sgid;
	private int pageId=1;
	public String getAreaList() throws Exception {
        Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
        dto = (HdAreaDto) pageCompose.hdAreaList(sgid,account.getOwner().getId(),pageId);
        return SUCCESS;
    }
	public HdAreaDto getDto()
	{
		return dto;
	}
	public void setDto(HdAreaDto dto)
	{
		this.dto = dto;
	}
	public long getSgid()
	{
		return sgid;
	}
	public void setSgid(long sgid)
	{
		this.sgid = sgid;
	}
	public int getPageId()
	{
		return pageId;
	}
	public void setPageId(int pageId)
	{
		this.pageId = pageId;
	}
}
