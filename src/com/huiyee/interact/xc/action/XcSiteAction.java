
package com.huiyee.interact.xc.action;

import com.huiyee.esite.action.AbstractAction;
import com.huiyee.interact.xc.dto.XcSiteDto;

public class XcSiteAction extends AbstractAction
{

	private long xcid;
	private long sitegroupid;
	private XcSiteDto dto;

	public long getXcid()
	{
		return xcid;
	}

	public void setXcid(long xcid)
	{
		this.xcid = xcid;
	}

	public long getSitegroupid()
	{
		return sitegroupid;
	}

	public void setSitegroupid(long sitegroupid)
	{
		this.sitegroupid = sitegroupid;
	}

	public XcSiteDto getDto()
	{
		return dto;
	}

	public void setDto(XcSiteDto dto)
	{
		this.dto = dto;
	}

	@Override
	public String execute() throws Exception
	{
		dto = (XcSiteDto) pageCompose.findXcSiteBygroupid(sitegroupid, xcid);
		return SUCCESS;
	}

}
