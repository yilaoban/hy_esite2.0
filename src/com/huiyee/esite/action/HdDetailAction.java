package com.huiyee.esite.action;

import com.huiyee.esite.compose.IFeatureCompose;
import com.huiyee.esite.dto.HdDetailDto;
import com.huiyee.esite.dto.Pager;


public class HdDetailAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5869312559547073628L;
	private IFeatureCompose featureCompose;
	private long sgid;//sitegroupid
	private HdDetailDto dto;
	private long hdid;//互动功能id
	private long hdfid;//互动fid
	private int pageId = 1;//第几页
	private Pager pager;
	private long pid;
	
	@Override
	public String execute() throws Exception{
		dto = (HdDetailDto) featureCompose.composeHdReportDetail(pid,hdid,hdfid, pageId);
		pager = dto.getPager();
		return SUCCESS;
	}
	
	public String queryExecute() throws Exception{
		dto = (HdDetailDto) featureCompose.composeQueryHdReportDetail(sgid, hdid, hdfid, dto, pageId);
		return SUCCESS;
	}

	public long getSgid() {
		return sgid;
	}

	public void setSgid(long sgid) {
		this.sgid = sgid;
	}

	public void setFeatureCompose(IFeatureCompose featureCompose) {
		this.featureCompose = featureCompose;
	}

	public long getHdid() {
		return hdid;
	}

	public void setHdid(long hdid) {
		this.hdid = hdid;
	}

	public int getPageId() {
		return pageId;
	}

	public void setPageId(int pageId) {
		this.pageId = pageId;
	}

	public HdDetailDto getDto() {
		return dto;
	}

	public Pager getPager() {
		return pager;
	}

	public void setPager(Pager pager) {
		this.pager = pager;
	}

	public long getHdfid() {
		return hdfid;
	}

	public void setHdfid(long hdfid) {
		this.hdfid = hdfid;
	}
	
	public int getReportPoint(){
    	return 2;
    }

	public void setDto(HdDetailDto dto) {
		this.dto = dto;
	}

	public long getPid()
	{
		return pid;
	}

	public void setPid(long pid)
	{
		this.pid = pid;
	}
	
}
