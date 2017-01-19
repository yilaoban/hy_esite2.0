package com.huiyee.esite.model;

import java.util.Date;

public class HdModel {

	private long hdid;//»¥¶¯¹¦ÄÜid
	private String type;
	private String name;
	private Date lasttime;
	private int total;
	private long hdfid;
	private String totalstr;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getLasttime() {
		return lasttime;
	}
	public void setLasttime(Date lasttime) {
		this.lasttime = lasttime;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public long getHdid() {
		return hdid;
	}
	public void setHdid(long hdid) {
		this.hdid = hdid;
	}
	public long getHdfid() {
		return hdfid;
	}
	public void setHdfid(long hdfid) {
		this.hdfid = hdfid;
	}
	public String getTotalstr()
	{
		return totalstr;
	}
	public void setTotalstr(String totalstr)
	{
		this.totalstr = totalstr;
	}
}
