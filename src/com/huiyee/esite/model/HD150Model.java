package com.huiyee.esite.model;

import java.util.Date;

public class HD150Model {

	private long id;
	private long pageid;
	private long categoryid;
	private String type;
	private long topage;
	private Date createtime;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getPageid() {
		return pageid;
	}
	public void setPageid(long pageid) {
		this.pageid = pageid;
	}
	public long getCategoryid() {
		return categoryid;
	}
	public void setCategoryid(long categoryid) {
		this.categoryid = categoryid;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public long getTopage() {
		return topage;
	}
	public void setTopage(long topage) {
		this.topage = topage;
	}
	
	
}
