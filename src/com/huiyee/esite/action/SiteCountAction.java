package com.huiyee.esite.action;

import java.util.List;

import com.huiyee.esite.dto.SiteCountDto;
import com.huiyee.esite.model.VisitLog;

public class SiteCountAction extends AbstractAction
{
	private long sitegroupid;
	private SiteCountDto dto;
	private List<VisitLog> vistLogList;

	// Í³¼Æsite
	@Override
	public String execute() throws Exception
	{
		dto = (SiteCountDto) pageCompose.composeSiteGroupCount(sitegroupid);
		return SUCCESS;
	}


	public SiteCountDto getDto()
	{
		return dto;
	}

	public List<VisitLog> getVistLogList()
	{
		return vistLogList;
	}


	public long getSitegroupid()
	{
		return sitegroupid;
	}


	public void setSitegroupid(long sitegroupid)
	{
		this.sitegroupid = sitegroupid;
	}
	
	public int getPagePosition(){
		return 3;
	}

}
