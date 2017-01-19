package com.huiyee.esite.action;

import com.huiyee.esite.dto.VisitReportDetailDto;

public class VisitRecordDetail extends AbstractAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8815116302544630941L;
	private long sgid;
	private long siteid;
	private String wbuid;
	private int pageId=1;
	private VisitReportDetailDto dto;
	
	public long getSgid() {
		return sgid;
	}

	public void setSgid(long sgid) {
		this.sgid = sgid;
	}

	public long getSiteid() {
		return siteid;
	}

	public void setSiteid(long siteid) {
		this.siteid = siteid;
	}

	public String getWbuid() {
		return wbuid;
	}

	public void setWbuid(String wbuid) {
		this.wbuid = wbuid;
	}

	public int getPageId() {
		return pageId;
	}

	public void setPageId(int pageId) {
		this.pageId = pageId;
	}

	public VisitReportDetailDto getDto() {
		return dto;
	}

	public void setDto(VisitReportDetailDto dto) {
		this.dto = dto;
	}

	public String execute() throws Exception{
		dto= (VisitReportDetailDto) pageCompose.findVisitReportDetailList(sgid,siteid ,wbuid, pageId);
		return SUCCESS;
	}
}
