package com.huiyee.esite.action;
import java.util.List;
import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.huiyee.esite.constants.IPageConstants;
import com.huiyee.esite.dto.DataCenterDto;
import com.huiyee.esite.dto.FansLevelAnalyseDto;
import com.huiyee.esite.dto.HourReportDto;
import com.huiyee.esite.dto.OwnerDataDto;
import com.huiyee.esite.dto.PageDto;
import com.huiyee.esite.dto.ReportRecordDto;
import com.huiyee.esite.dto.SiteDataDto;
import com.huiyee.esite.dto.SiteDto;
import com.huiyee.esite.dto.SitePage;
import com.huiyee.esite.dto.TerminalDataDto;
import com.huiyee.esite.dto.VisitViewAllDto;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.model.ReportGenderAnalyse;
import com.huiyee.esite.model.Site;
public class SiteVistReportAction extends AbstractAction
{

    /**
     * 
     */
    private static final long serialVersionUID = 1161505268038566539L;
    private long siteid;
    private String siteGroupName;
    private String siteName;
    private ReportGenderAnalyse genderAnalyse;
    private int pageId = 1;
    private VisitViewAllDto viewDto;
    private int datenum=IPageConstants.REPORT_DAY_NUM;
    private HourReportDto reportDto;
    private FansLevelAnalyseDto levelAnalyseDto;
    private DataCenterDto indexDto;
    private TerminalDataDto terminalDto;
    private String terminalstr;
    private long datatype;
    private SiteDataDto siteData;
    private List<SitePage> list;
    private OwnerDataDto ownerData;
    private int report;
    private SiteDto siteDto;
    private PageDto pageDto;
    private long pid;
    private ReportRecordDto dto;
    private String src;
    @Override
    public String execute() throws Exception {
        Site site=pageCompose.findSiteById(siteid);
        if(site!=null){
            Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
            if(site.getOwnerId()!=account.getOwner().getId()){
                return SUCCESS;
            }
            siteName=site.getName();
            viewDto=(VisitViewAllDto)pageCompose.visitViewAllData(siteid,datenum);
        }
        return SUCCESS;
    }
    
    public String getGenderData() throws Exception {
        Site site=pageCompose.findSiteById(siteid);
        if(site!=null){
            Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
            if(site.getOwnerId()!=account.getOwner().getId()){
                return SUCCESS;
            }
            siteGroupName=site.getName();
            genderAnalyse=pageCompose.visitGenderData(siteid);
        }
        return SUCCESS;
    }
    
    public String getTerminalData() throws Exception
	{
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		terminalDto = pageCompose.visitTerminalData(siteid, account);
		Gson gson = new Gson();
		terminalstr= gson.toJson(terminalDto.getTerminaldata());
		return SUCCESS;
	}
    /*
	 * public String getVisitData() throws Exception { HttpServletResponse
	 * response = ServletActionContext.getResponse();
	 * response.setHeader("pragma", "no-cache");
	 * response.setHeader("cache-control", "no-cache");
	 * response.setContentType("text/html; charset=utf-8"); Account account =
	 * (Account)
	 * ServletActionContext.getRequest().getSession().getAttribute("account");
	 * VisitViewAllDto
	 * indexDto=(VisitViewAllDto)pageCompose.visitViewAllDataAjax(sgid,account);
	 * Gson gson = new Gson(); String gsonComments = gson.toJson(indexDto);
	 * PrintWriter pw = response.getWriter(); pw.print(gsonComments);
	 * pw.flush(); pw.close(); return null;
	 *  }
	 * 
	 */
    public String getHourReportData() throws Exception {
        Account account=(Account)ServletActionContext.getRequest().getSession().getAttribute("account");
        reportDto=(HourReportDto)pageCompose.hourReportData(siteid,account);
        return SUCCESS;
    }
    
    public String getVisitFansLevelData() throws Exception {
        Account account=(Account)ServletActionContext.getRequest().getSession().getAttribute("account");
        levelAnalyseDto=(FansLevelAnalyseDto)pageCompose.getVisitLevelData(siteid,account);
        return SUCCESS;
    }
    public String getOwnerVisitData() throws Exception {
        Account account=(Account)ServletActionContext.getRequest().getSession().getAttribute("account");
        ownerData=pageCompose.getOwnerVisitData(account.getOwner().getId());
        return SUCCESS;
    }
    public String getOwnerHdData() throws Exception {
        Account account=(Account)ServletActionContext.getRequest().getSession().getAttribute("account");
        ownerData=pageCompose.getOwnerHdData(account.getOwner().getId());
        return SUCCESS;
    }
    public String getSiteReport() throws Exception {
        Site site=pageCompose.findSiteById(siteid);
        datatype=siteid;
        if(site!=null){
            Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
            if(site.getOwnerId()!=account.getOwner().getId()){
                return SUCCESS;
            }
            siteData=pageCompose.getSiteData(siteid,account.getOwner().getId(),datenum);
        }
        return SUCCESS;
    }
    
    public String getSitePageList() throws Exception {
    	Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
        list=pageCompose.findSitePageByOwner(account.getOwner().getId());
        report=2;
        return SUCCESS;
    }
    public String getSiteHdData() throws Exception {
    	Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
        siteDto=pageCompose.getSiteHdData(siteid,account.getOwner().getId());
        report=2;
        return SUCCESS;
    }
    public String getSiteVisitData() throws Exception {
    	Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
        siteDto=pageCompose.getSiteVisitData(siteid,account.getOwner().getId());
        report=1;
        return SUCCESS;
    }
    public String getVisitPageArea() throws Exception {
    	Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
        pageDto=pageCompose.getPageVisitArea(pid,account.getOwner().getId(),src);
        report=1;
        return SUCCESS;
    }
    public String getVisitPageGender() throws Exception {
    	Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
        pageDto=pageCompose.getPageVisitGender(pid,account.getOwner().getId(),src);
        report=1;
        return SUCCESS;
    }
    public String getVisitPageFans() throws Exception {
    	Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
        pageDto=pageCompose.getPageVisitFans(pid,account.getOwner().getId());
        report=1;
        return SUCCESS;
    }
    public String getHdPageArea() throws Exception {
    	Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
        pageDto=pageCompose.getPageHdArea(pid,account.getOwner().getId(),src);
        report=2;
        return SUCCESS;
    }
    public String getHdPageGender() throws Exception {
    	Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
        pageDto=pageCompose.getPageHdGender(pid,account.getOwner().getId(),src);
        report=2;
        return SUCCESS;
    }
    public String getHdPageFans() throws Exception {
    	Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
        pageDto=pageCompose.getPageHdFans(pid,account.getOwner().getId());
        report=2;
        return SUCCESS;
    }
    public String getHdPageList() throws Exception {
    	Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
        dto=(ReportRecordDto)pageCompose.composeHdListByPageId(pid,pageId,account.getOwner().getId(),src);
        return SUCCESS;
    }
    public String getVisitPageList() throws Exception {
    	Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
        dto=(ReportRecordDto)pageCompose.composeVisitListByPageId(pid,pageId,account.getOwner().getId(),src);
        return SUCCESS;
    }
    public String getHdPageType() throws Exception {
    	Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
        pageDto=pageCompose.getPageHdType(pid,account.getOwner().getId(),src);
        report=3;
        return SUCCESS;
    }
    

    public String getSiteGroupName() {
        return siteGroupName;
    }

    public ReportGenderAnalyse getGenderAnalyse() {
        return genderAnalyse;
    }

    public int getPageId() {
        return pageId;
    }

    public void setPageId(int pageId) {
        this.pageId = pageId;
    }

    public VisitViewAllDto getViewDto() {
        return viewDto;
    }

    public HourReportDto getReportDto() {
        return reportDto;
    }
    
    public int getReportPoint(){
    	return 1;
    }

    public FansLevelAnalyseDto getLevelAnalyseDto() {
        return levelAnalyseDto;
    }

	public DataCenterDto getIndexDto()
	{
		return indexDto;
	}

	public TerminalDataDto getTerminalDto()
	{
		return terminalDto;
	}

	public String getTerminalstr()
	{
		return terminalstr;
	}

	public long getDatatype()
	{
		return datatype;
	}

	public SiteDataDto getSiteData()
	{
		return siteData;
	}

	public long getSiteid()
	{
		return siteid;
	}

	public void setSiteid(long siteid)
	{
		this.siteid = siteid;
	}

	public String getSiteName()
	{
		return siteName;
	}

	public void setSiteName(String siteName)
	{
		this.siteName = siteName;
	}

	public List<SitePage> getList()
	{
		return list;
	}

	public void setList(List<SitePage> list)
	{
		this.list = list;
	}

	public int getReport()
	{
		return report;
	}

	public OwnerDataDto getOwnerData()
	{
		return ownerData;
	}

	public SiteDto getSiteDto()
	{
		return siteDto;
	}

	public PageDto getPageDto()
	{
		return pageDto;
	}

	public long getPid()
	{
		return pid;
	}

	public void setPid(long pid)
	{
		this.pid = pid;
	}

	public ReportRecordDto getDto()
	{
		return dto;
	}

	public String getSrc()
	{
		return src;
	}

	public void setSrc(String src)
	{
		this.src = src;
	}

}
