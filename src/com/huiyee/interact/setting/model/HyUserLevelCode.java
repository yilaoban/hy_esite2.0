package com.huiyee.interact.setting.model;

import java.util.Date;

public class HyUserLevelCode {

	private long id;
	private long owner;
	private long levelid;
	private String code;
	private Integer status;
	private Date updatetime;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getOwner() {
		return owner;
	}

	public void setOwner(long owner) {
		this.owner = owner;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public long getLevelid() {
		return levelid;
	}

	public void setLevelid(long levelid) {
		this.levelid = levelid;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

}
