package com.huiyee.esite.action;

import com.huiyee.esite.dto.SiteVistLogDto;

public class ActivityParticipateFailDetailsAction extends AbstractAction{
	private static final long serialVersionUID = -3353933299958896887L;
	private String terminalName;
	private long activityid;
	private String visitTime1;
	private String visitTime2;
	private String source;
	private String nickname;
	
	private int pageId = 1;
	private SiteVistLogDto dto;
	
	public String execute() throws Exception {
		dto = (SiteVistLogDto) pageCompose.activityParticipateFailDetails(terminalName,pageId,activityid);
		return SUCCESS;
	}
	
	public String getActivityParticipateFailDetails() throws Exception{
		dto = (SiteVistLogDto) pageCompose.getActivityParticipateFailDetails(terminalName,visitTime1,visitTime2,source,pageId,activityid);
		return SUCCESS;
	}
	
	public String getActivityParticipateFailDetailsByName() throws Exception{
		dto = (SiteVistLogDto) pageCompose.getActivityParticipateFailDetailsByName(terminalName,nickname,pageId,activityid);
		return SUCCESS;
	}

	public String getTerminalName() {
		return terminalName;
	}

	public void setTerminalName(String terminalName) {
		this.terminalName = terminalName;
	}

	public long getActivityid() {
		return activityid;
	}

	public void setActivityid(long activityid) {
		this.activityid = activityid;
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

	public int getPageId() {
		return pageId;
	}

	public void setPageId(int pageId) {
		this.pageId = pageId;
	}

	public SiteVistLogDto getDto() {
		return dto;
	}

	public void setDto(SiteVistLogDto dto) {
		this.dto = dto;
	}
	
}
