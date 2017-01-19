package com.huiyee.esite.action;

import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.huiyee.esite.compose.IFeatureCompose;
import com.huiyee.esite.constants.IPageConstants;
import com.huiyee.esite.dto.HdNumDataDto;
import com.huiyee.esite.dto.HdPerNumDataDto;
import com.huiyee.esite.dto.HdReportDto;
import com.huiyee.esite.dto.HighChartsDataDto;
import com.huiyee.esite.dto.HourReportDto;
import com.huiyee.esite.dto.TerminalDataDto;
import com.huiyee.esite.dto.VisitViewAllDto;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.model.ReportGenderAnalyse;
public class HdReportAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5869312559547073628L;
	private IFeatureCompose featureCompose;
	private long siteid;
	private HdReportDto dto;
	private HourReportDto reportDto;
	private VisitViewAllDto viewDto;
	private int datenum=IPageConstants.REPORT_DAY_NUM;
	private ReportGenderAnalyse genderAnalyse;
	private TerminalDataDto terminalDto;
	private String terminalstr;
	private HighChartsDataDto areaDataDto;
	private HdNumDataDto numDataDto;
	private HdPerNumDataDto perNumDataDto;
	@Override
	public String execute() throws Exception {
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		dto = (HdReportDto) featureCompose.composeHdReport(siteid, account.getOwner().getId());
		return SUCCESS;
	}
	
	public String getAreaReport() throws Exception {
        Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
        areaDataDto = (HighChartsDataDto) pageCompose.hdAreaReport(siteid,account.getOwner().getId());
        return SUCCESS;
    }
	
	public String getNumReport() throws Exception {
        Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
        numDataDto = (HdNumDataDto) pageCompose.hdNumReport(siteid, account.getOwner().getId());
        return SUCCESS;
    }
	public String getPerNumReport() throws Exception {
        Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
        perNumDataDto = (HdPerNumDataDto) pageCompose.hdPerNumReport(siteid, account.getOwner().getId());
        return SUCCESS;
    }
	public String getHdReport() throws Exception {
        Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
        viewDto = (VisitViewAllDto) pageCompose.hdViewAllData(siteid, datenum, account);
        return SUCCESS;
    }

    public String getHdHourReportData() throws Exception {
        Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
        reportDto = (HourReportDto) pageCompose.HdHourReportData(siteid, account);
        return SUCCESS;
    }
    
    public String getHdGenderData() throws Exception {
        Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
        genderAnalyse=pageCompose.danymicGenderData(siteid, account);
        return SUCCESS;
    }
    
    public String getHdTerminalData() throws Exception {
        Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
        terminalDto=pageCompose.danymicTerminalData(siteid, account);
        Gson gson = new Gson();
        terminalstr= gson.toJson(terminalDto.getTerminaldata());
        return SUCCESS;
    }

	public HdReportDto getDto() {
		return dto;
	}

	public void setFeatureCompose(IFeatureCompose featureCompose) {
		this.featureCompose = featureCompose;
	}
	
    public int getReportPoint(){
    	return 2;
    }

    public HourReportDto getReportDto() {
        return reportDto;
    }

    public VisitViewAllDto getViewDto() {
        return viewDto;
    }

    public ReportGenderAnalyse getGenderAnalyse() {
        return genderAnalyse;
    }

	public TerminalDataDto getTerminalDto()
	{
		return terminalDto;
	}

	public String getTerminalstr()
	{
		return terminalstr;
	}

	public HighChartsDataDto getAreaDataDto()
	{
		return areaDataDto;
	}

	public HdNumDataDto getNumDataDto()
	{
		return numDataDto;
	}

	public HdPerNumDataDto getPerNumDataDto()
	{
		return perNumDataDto;
	}

	public long getSiteid()
	{
		return siteid;
	}

	public void setSiteid(long siteid)
	{
		this.siteid = siteid;
	}
}
