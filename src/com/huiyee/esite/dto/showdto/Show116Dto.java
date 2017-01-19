package com.huiyee.esite.dto.showdto;

import java.io.Serializable;
import java.util.List;

import com.huiyee.esite.dto.IDto;
import com.huiyee.esite.model.SinaShareRecordCategory;

public class Show116Dto implements IDto, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2643688439863496458L;
	private long fid;
	private List<SinaShareRecordCategory> list;
	public long getFid() {
		return fid;
	}
	public void setFid(long fid) {
		this.fid = fid;
	}
	public List<SinaShareRecordCategory> getList() {
		return list;
	}
	public void setList(List<SinaShareRecordCategory> list) {
		this.list = list;
	}
	
}
