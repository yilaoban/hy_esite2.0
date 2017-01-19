package com.huiyee.esite.dto.showdto;

import java.io.Serializable;
import java.util.List;

import com.huiyee.esite.dto.IDto;
import com.huiyee.esite.model.SinaCheckList;
import com.huiyee.esite.model.SinaChecklistRecord;
import com.huiyee.esite.model.SinaShare;

public class Show115Dto implements IDto, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5018812924307783397L;
	private List<SinaChecklistRecord> checkListRecord;
	private long fid;

	public long getFid() {
		return fid;
	}

	public void setFid(long fid) {
		this.fid = fid;
	}

	public List<SinaChecklistRecord> getCheckListRecord() {
		return checkListRecord;
	}

	public void setCheckListRecord(List<SinaChecklistRecord> checkListRecord) {
		this.checkListRecord = checkListRecord;
	}

}
