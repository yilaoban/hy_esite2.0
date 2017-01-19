package com.huiyee.esite.dto;

import java.io.Serializable;
import java.util.List;

import com.huiyee.esite.model.SinaChecklistRecord;
import com.huiyee.esite.model.SinaNoCheckList;
import com.huiyee.esite.model.SinaShare;

public class Feature115Dto implements IDto, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2269921230871622879L;
	private SinaNoCheckList snl;
	private long fid;
	private List<SinaShare> checklist;

	public long getFid() {
		return fid;
	}

	public void setFid(long fid) {
		this.fid = fid;
	}

	public List<SinaShare> getChecklist() {
		return checklist;
	}

	public void setChecklist(List<SinaShare> checklist) {
		this.checklist = checklist;
	}

	public SinaNoCheckList getSnl() {
		return snl;
	}

	public void setSnl(SinaNoCheckList snl) {
		this.snl = snl;
	}

}
