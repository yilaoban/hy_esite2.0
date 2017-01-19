package com.huiyee.esite.dto.showdto;

import java.io.Serializable;
import java.util.List;

import com.huiyee.esite.dto.IDto;
import com.huiyee.esite.model.BBSForum;
import com.huiyee.esite.model.ContentCategory;
import com.huiyee.interact.xc.model.Xc;

public class Show161Dto implements IDto, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5585327882247813039L;
	private long fid;
	private long relationid;
	private long pageid;
	private List<ContentCategory> list;
	
	public List<ContentCategory> getList()
	{
		return list;
	}
	
	public void setList(List<ContentCategory> list)
	{
		this.list = list;
	}
	public long getFid() {
		return fid;
	}
	public void setFid(long fid) {
		this.fid = fid;
	}
	public long getRelationid() {
		return relationid;
	}
	public void setRelationid(long relationid) {
		this.relationid = relationid;
	}
	public long getPageid() {
		return pageid;
	}
	public void setPageid(long pageid) {
		this.pageid = pageid;
	}
}
