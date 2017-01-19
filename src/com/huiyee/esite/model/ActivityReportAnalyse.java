package com.huiyee.esite.model;

import java.io.Serializable;
import java.util.Date;

public class ActivityReportAnalyse implements Serializable{

	private static final long serialVersionUID = 307776671613906770L;
	
	private long id;
	private long activityid;
	private int joinnum;
	private int sucjoinnum;
	private Date createtime;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getActivityid() {
		return activityid;
	}
	public void setActivityid(long activityid) {
		this.activityid = activityid;
	}
	public int getJoinnum() {
		return joinnum;
	}
	public void setJoinnum(int joinnum) {
		this.joinnum = joinnum;
	}
	public int getSucjoinnum() {
		return sucjoinnum;
	}
	public void setSucjoinnum(int sucjoinnum) {
		this.sucjoinnum = sucjoinnum;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	
	

}
