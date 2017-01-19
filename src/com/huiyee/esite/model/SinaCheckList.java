package com.huiyee.esite.model;

import java.io.Serializable;
import java.util.Date;

public class SinaCheckList implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8544983204810700425L;
	private long id;
	private long shareid;
	private long pageid;
	private Date createtime;
	private String status;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getShareid() {
		return shareid;
	}

	public void setShareid(long shareid) {
		this.shareid = shareid;
	}

	public long getPageid() {
		return pageid;
	}

	public void setPageid(long pageid) {
		this.pageid = pageid;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
