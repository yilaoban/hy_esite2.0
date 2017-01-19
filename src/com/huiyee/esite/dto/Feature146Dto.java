package com.huiyee.esite.dto;

import java.io.Serializable;
import java.util.List;
import com.huiyee.interact.xc.model.Xc;

public class Feature146Dto implements IDto, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1083697580084574464L;
	private long fid;
	private long xcid;
	private List<Xc> xclist;
	public long getFid() {
		return fid;
	}
	public void setFid(long fid) {
		this.fid = fid;
	}
	public List<Xc> getXclist() {
		return xclist;
	}
	public void setXclist(List<Xc> xclist) {
		this.xclist = xclist;
	}
	public long getXcid() {
		return xcid;
	}
	public void setXcid(long xcid) {
		this.xcid = xcid;
	}
	
}
