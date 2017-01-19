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
public class FansHdAnalysisAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long sgid;
	private int pageId=1;
	private FansAnalysisDto dto;
	public long getSgid() {
		return sgid;
	}

	public void setSgid(long sgid) {
		this.sgid = sgid;
	}

	public int getPageId() {
		return pageId;
	}

	public void setPageId(int pageId) {
		this.pageId = pageId;
	}
	
	public String execute() throws Exception {
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		dto = (FansAnalysisDto)pageCompose.composeHdFansReport(sgid,pageId,account.getOwner().getId());
		return SUCCESS;
	}

	public FansAnalysisDto getDto() {
		return dto;
	}

	public int getReportPoint(){
    	return 2;
    }
	

}
