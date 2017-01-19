package com.huiyee.esite.dto;

import java.io.Serializable;
import java.util.List;

import com.huiyee.esite.model.UserUpload;
import com.huiyee.esite.model.UserUploadRecord;

public class Feature107Dto implements IDto, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<UserUpload> fidList;
	private long uploadid;
	private long fid;
	private List<UserUploadRecord> record;

	public List<UserUpload> getFidList() {
		return fidList;
	}

	public void setFidList(List<UserUpload> fidList) {
		this.fidList = fidList;
	}

	public long getUploadid() {
		return uploadid;
	}

	public void setUploadid(long uploadid) {
		this.uploadid = uploadid;
	}

	public long getFid() {
		return fid;
	}

	public void setFid(long fid) {
		this.fid = fid;
	}

	public List<UserUploadRecord> getRecord() {
		return record;
	}

	public void setRecord(List<UserUploadRecord> record) {
		this.record = record;
	}

}
