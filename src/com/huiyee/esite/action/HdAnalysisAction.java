package com.huiyee.esite.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.huiyee.esite.dto.FansLevelAnalyseDto;
import com.huiyee.esite.dto.HighChartsDataDto;
import com.huiyee.esite.dto.RgAnalysisDto;
import com.huiyee.esite.model.Account;

/**
 * 
 * 
 * @author hy
 * 
 */
public class HdAnalysisAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long sgid;
	private RgAnalysisDto dto;
	private String sitegroupname;
	private String sitename;
	private long sitegroupid;
	private int pageId=1;
	private int visitnum;
	private int hdnum;
	private FansLevelAnalyseDto levelAnalyseDto;
	public int getVisitnum() {
		return visitnum;
	}

	public void setVisitnum(int visitnum) {
		this.visitnum = visitnum;
	}

	public int getHdnum() {
		return hdnum;
	}

	public void setHdnum(int hdnum) {
		this.hdnum = hdnum;
	}

	public String getSitegroupname() {
		return sitegroupname;
	}

	public void setSitegroupname(String sitegroupname) {
		this.sitegroupname = sitegroupname;
	}

	public long getSitegroupid() {
		return sitegroupid;
	}

	public void setSitegroupid(long sitegroupid) {
		this.sitegroupid = sitegroupid;
	}


	public RgAnalysisDto getDto() {
		return dto;
	}

	public void setDto(RgAnalysisDto dto) {
		this.dto = dto;
	}

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
	
	//互动表action
	public String execute() throws Exception {
		Account account=(Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		long ownerid=account.getOwner().getId();
		dto = (RgAnalysisDto) pageCompose.showAnalysisReport(sgid,pageId,ownerid);
		sitename=pageCompose.findSiteById(sgid).getName();
		return SUCCESS;
	}
	//查询互动生成互动图
	public String getHudongData() throws Exception{
		
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setHeader("pragma", "no-cache");
		response.setHeader("cache-control", "no-cache");
		response.setContentType("text/html; charset=utf-8");
		
		HighChartsDataDto hdto=(HighChartsDataDto) pageCompose.showDynamicHighChartReport(sgid);
		Gson gson = new Gson();
		String str= gson.toJson(hdto);
		PrintWriter pw = response.getWriter();
		pw.print(str);
		pw.flush();
		pw.close();
		return null;
	}
	
	public String getHdFansLevelData() throws Exception {
        Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
        levelAnalyseDto = (FansLevelAnalyseDto) pageCompose.getHdLevelData(sgid, account);
        return SUCCESS;
    }
	    
	
	public int getReportPoint(){
    	return 2;
    }

    public FansLevelAnalyseDto getLevelAnalyseDto() {
        return levelAnalyseDto;
    }

	public String getSitename()
	{
		return sitename;
	}

	public void setSitename(String sitename)
	{
		this.sitename = sitename;
	}
	
}
