package com.huiyee.esite.action;

import com.huiyee.esite.dto.SiteVistLogDto;
import com.huiyee.esite.util.ToolUtils;

public class NonAnonVisitReportAction extends AbstractAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6758519144065665188L;
	private long sitegroupid;
	private SiteVistLogDto dto;
	
	private String visitTime1;
	private String visitTime2;
	private String terminal;
	private String source;
	
	private String nickname;
	private int pageId = 1;
	
	private long activityid;
	@Override
	public String execute() throws Exception {
		dto = (SiteVistLogDto) pageCompose.nonAnonVisitReportData(sitegroupid,pageId,activityid);
		return SUCCESS;
	}
	
	public String getNonAnonVisitReportData() throws Exception {
		dto = (SiteVistLogDto) pageCompose.findnonAnonVisitDataDetails(sitegroupid,visitTime1,visitTime2,terminal,source,pageId,activityid);
		return SUCCESS;
	}
	
	public String getNonAnonVisitData() throws Exception {
		dto = (SiteVistLogDto) pageCompose.findnonAnonVisitDataDetails(sitegroupid,nickname,pageId,activityid);
		return SUCCESS;
	}
	

	public int getPageId() {
		return pageId;
	}

	public void setPageId(int pageId) {
		this.pageId = pageId;
	}

	public long getSitegroupid() {
		return sitegroupid;
	}
	public void setSitegroupid(long sitegroupid) {
		this.sitegroupid = sitegroupid;
	}
	public SiteVistLogDto getDto() {
		return dto;
	}
	public void setDto(SiteVistLogDto dto) {
		this.dto = dto;
	}
	public String getVisitTime1() {
		return visitTime1;
	}
	public void setVisitTime1(String visitTime1) {
		this.visitTime1 = visitTime1;
	}
	public String getVisitTime2() {
		return visitTime2;
	}
	public void setVisitTime2(String visitTime2) {
		this.visitTime2 = visitTime2;
	}
	public String getTerminal() {
		return terminal;
	}
	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public long getActivityid() {
		return activityid;
	}

	public void setActivityid(long activityid) {
		this.activityid = activityid;
	}
	
}
