package com.huiyee.esite.action;

import com.huiyee.esite.dto.OwnerDataDto;
import com.huiyee.esite.model.Account;
import org.apache.struts2.ServletActionContext;
public class ReportAllAction extends AbstractAction
{

    /**
     * 
     */
    private static final long serialVersionUID = 1161505268038566539L;
    
    private int pageId = 1;
    private OwnerDataDto dto;
    private int report;
    public String getReportIndex() throws Exception {
        Account account=(Account)ServletActionContext.getRequest().getSession().getAttribute("account");
        dto=pageCompose.getPageData(account.getOwner().getId(),pageId);
        report=1;
        return SUCCESS;
    }
	public int getPageId()
	{
		return pageId;
	}
	public void setPageId(int pageId)
	{
		this.pageId = pageId;
	}
	public OwnerDataDto getDto()
	{
		return dto;
	}
	public int getReport()
	{
		return report;
	}
   
  
    
   
    


}
