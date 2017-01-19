package com.huiyee.esite.action;

import com.huiyee.esite.dto.SiteVistLogDto;

public class LoadSiteVistLogAction extends AbstractAction
{
	/**
     * 
     */
    private static final long serialVersionUID = -8822774974924324059L;
    private long siteid;
	private SiteVistLogDto dto;
	private String type;
	private int pageId = 1;

	// Í³¼Æsite
	@Override
	public String execute() throws Exception
	{
		dto = (SiteVistLogDto) pageCompose.composeLoadVistLogList(siteid, type, pageId);
		return SUCCESS;
	}

	public void setType(String type)
	{
		this.type = type;
	}


	public SiteVistLogDto getDto()
	{
		return dto;
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
