package com.huiyee.esite.model;

import java.io.Serializable;
import java.util.Date;

public class ActivityLog implements Serializable{
	private static final long serialVersionUID = 109033711973693239L;
	private long id;
	private long activityid;
	private long wbuid;
	private Date createtime;
	private String type;
	private String source;
	private String ip;
	private String nickname;
	
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
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
	public long getWbuid() {
		return wbuid;
	}
	public void setWbuid(long wbuid) {
		this.wbuid = wbuid;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	
}
