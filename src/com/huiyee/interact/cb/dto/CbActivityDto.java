package com.huiyee.interact.cb.dto;

import java.util.List;

import com.huiyee.esite.model.CbActivity;
import com.huiyee.interact.cb.model.CbActivityJlRecord;

public class CbActivityDto extends CbRsDto {

	private List<CbActivity> list;
	private List<CbActivityJlRecord> jlRecordList;
	private CbActivity activity;
	private int pageId;

	public List<CbActivity> getList() {
		return list;
	}

	public void setList(List<CbActivity> list) {
		this.list = list;
	}

	public List<CbActivityJlRecord> getJlRecordList() {
		return jlRecordList;
	}

	public void setJlRecordList(List<CbActivityJlRecord> jlRecordList) {
		this.jlRecordList = jlRecordList;
	}

	public CbActivity getActivity() {
		return activity;
	}

	public void setActivity(CbActivity activity) {
		this.activity = activity;
	}

	public int getPageId() {
		return pageId;
	}

	public void setPageId(int pageId) {
		this.pageId = pageId;
	}

}
