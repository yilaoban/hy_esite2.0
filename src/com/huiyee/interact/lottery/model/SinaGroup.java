package com.huiyee.interact.lottery.model;

import java.io.Serializable;
import java.util.Date;

public class SinaGroup implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9137985316729728427L;

	private long id;
	private long wbuid;
	private String groupName;
	private String type;
	private int userNum;
	private Date updateTime;
	private long tagsid;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getWbuid() {
		return wbuid;
	}
	public void setWbuid(long wbuid) {
		this.wbuid = wbuid;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getUserNum() {
		return userNum;
	}
	public void setUserNum(int userNum) {
		this.userNum = userNum;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public long getTagsid() {
		return tagsid;
	}
	public void setTagsid(long tagsid) {
		this.tagsid = tagsid;
	}
	
	
}
