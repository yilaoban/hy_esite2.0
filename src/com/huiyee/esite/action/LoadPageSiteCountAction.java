package com.huiyee.esite.action;

import java.util.List;

import com.huiyee.esite.dto.LoadPageSiteCountDto;
import com.huiyee.esite.dto.SiteCountDto;
import com.huiyee.esite.model.VisitLog;

public class LoadPageSiteCountAction extends AbstractAction
{
	/**
     * 
     */
    private static final long serialVersionUID = 4052591336767345421L;
    private long siteid;
	private LoadPageSiteCountDto dto;
	private List<VisitLog> vistLogList;

	// Í³¼Æsite
	@Override
	public String execute() throws Exception
	{
		dto = (LoadPageSiteCountDto) pageCompose.composeSiteCount(siteid);
		return SUCCESS;
	}
	
	public LoadPageSiteCountDto getDto() {
        return dto;
    }

    public List<VisitLog> getVistLogList()
	{
		return vistLogList;
	}

	public void setSiteid(long siteid) {
        this.siteid = siteid;
    }


    public int getPagePosition(){
		return 3;
	}

}
