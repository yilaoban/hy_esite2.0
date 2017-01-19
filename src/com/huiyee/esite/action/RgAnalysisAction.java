package com.huiyee.esite.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.huiyee.esite.dto.HighChartsDataDto;
import com.huiyee.esite.dto.RgAnalysisDto;
import com.huiyee.esite.model.Account;

/**
 * 
 * 
 * @author hy
 * 
 */
public class RgAnalysisAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long site;
	private RgAnalysisDto dto;
	private String sitename;
	private long siteid;
	private int pageId=1;
	private int visitnum;
	private int hdnum;
	
	
	
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



	public String getSitename()
	{
		return sitename;
	}

	public void setSitename(String sitename)
	{
		this.sitename = sitename;
	}




	public long getSiteid()
	{
		return siteid;
	}

	public void setSiteid(long siteid)
	{
		this.siteid = siteid;
	}

	public RgAnalysisDto getDto() {
		return dto;
	}

	public void setDto(RgAnalysisDto dto) {
		this.dto = dto;
	}



	public long getSite()
	{
		return site;
	}

	public void setSite(long site)
	{
		this.site = site;
	}

	public int getPageId() {
		return pageId;
	}

	public void setPageId(int pageId) {
		this.pageId = pageId;
	}
	
	//访问表action
	public String getVisitReport() throws Exception{
		Account account=(Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		long ownerid=account.getOwner().getId();
		dto=(RgAnalysisDto) pageCompose.showVisiterReport(siteid,pageId,ownerid);
		sitename=pageCompose.findSiteById(siteid).getName();
		return SUCCESS;
	}
	
	//查询访问生成访问图
	public String getVisitData() throws Exception {

		HttpServletResponse response = ServletActionContext.getResponse();
		response.setHeader("pragma", "no-cache");
		response.setHeader("cache-control", "no-cache");
		response.setContentType("text/html; charset=utf-8");

		HighChartsDataDto picdto = (HighChartsDataDto) pageCompose.showVisitHighChartReport(siteid);
		Gson gson = new Gson();
		String str = gson.toJson(picdto);
		PrintWriter pw = response.getWriter();
		pw.print(str);
		pw.flush();
		pw.close();
		return null;
	}
	
	public int getReportPoint(){
    	return 1;
    }
	
}
