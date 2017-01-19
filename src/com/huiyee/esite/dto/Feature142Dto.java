package com.huiyee.esite.dto;

import java.io.Serializable;
import java.util.List;

import com.huiyee.esite.model.SecurityCodeModel;

public class Feature142Dto implements IDto , Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long fid;
	private List<SecurityCodeModel> list;
	
	public long getFid()
	{
		return fid;
	}

	public void setFid(long fid)
	{
		this.fid = fid;
	}

	public List<SecurityCodeModel> getList() {
		return list;
	}

	public void setList(List<SecurityCodeModel> list) {
		this.list = list;
	}
	
	
}
