package com.huiyee.esite.dto;

import java.io.Serializable;
import java.util.List;

import com.huiyee.esite.model.SinaChecklistRecord;
import com.huiyee.esite.model.SinaShareRecordCategory;

public class Feature116Dto implements IDto, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2451113855031072720L;
	private long fid;
	private List<SinaShareRecordCategory> category;
	private List<SinaChecklistRecord> list;
	private List<Long> recordid;
	private List<Long> categoryid;
	private List<Integer> idx;
	
	public List<Long> getRecordid() {
		return recordid;
	}
	public void setRecordid(List<Long> recordid) {
		this.recordid = recordid;
	}
	public List<Long> getCategoryid() {
		return categoryid;
	}
	public void setCategoryid(List<Long> categoryid) {
		this.categoryid = categoryid;
	}
	public List<SinaShareRecordCategory> getCategory() {
		return category;
	}
	public void setCategory(List<SinaShareRecordCategory> category) {
		this.category = category;
	}
	public List<SinaChecklistRecord> getList() {
		return list;
	}
	public void setList(List<SinaChecklistRecord> list) {
		this.list = list;
	}
	public long getFid() {
		return fid;
	}
	public void setFid(long fid) {
		this.fid = fid;
	}
	public List<Integer> getIdx() {
		return idx;
	}
	public void setIdx(List<Integer> idx) {
		this.idx = idx;
	}
	
}
