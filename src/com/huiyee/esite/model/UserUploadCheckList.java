package com.huiyee.esite.model;

import java.io.Serializable;
import java.util.Date;

public class UserUploadCheckList implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3633721919156240391L;
	private long id;
	private long uploadid;
	private long pageid;
	private Date createtime;
	private String status;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getUploadid() {
		return uploadid;
	}

	public void setUploadid(long uploadid) {
		this.uploadid = uploadid;
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
