package com.huiyee.esite.action;

import org.apache.struts2.ServletActionContext;

import com.huiyee.esite.dto.FansAnalysisDto;
import com.huiyee.esite.model.Account;

/**
 * 
 * 
 * @author hy
 * 
 */
public class FansAnalysisAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long siteid;
	private int pageId=1;
	private FansAnalysisDto dto;


	public long getSiteid()
	{
		return siteid;
	}

	public void setSiteid(long siteid)
	{
		this.siteid = siteid;
	}

	public int getPageId() {
		return pageId;
	}

	public void setPageId(int pageId) {
		this.pageId = pageId;
	}
	
	public String execute() throws Exception {
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		dto = (FansAnalysisDto)pageCompose.composeFansReport(siteid,pageId,account.getOwner().getId());
		return SUCCESS;
	}

	public FansAnalysisDto getDto() {
		return dto;
	}	
	public int getReportPoint(){
    	return 1;
    }
	

}
