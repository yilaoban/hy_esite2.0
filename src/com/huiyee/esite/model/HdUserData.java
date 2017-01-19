package com.huiyee.esite.model;

import java.util.Date;

public class HdUserData {

	private long id;
	private long uid;
	private int utype;
	private long hdid;
	private Date hdday;
	private int featureid;
	private int num;

	private int total;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public int getUtype() {
		return utype;
	}

	public void setUtype(int utype) {
		this.utype = utype;
	}

	public long getHdid() {
		return hdid;
	}

	public void setHdid(long hdid) {
		this.hdid = hdid;
	}

	public Date getHdday() {
		return hdday;
	}

	public void setHdday(Date hdday) {
		this.hdday = hdday;
	}

	public int getFeatureid() {
		return featureid;
	}

	public void setFeatureid(int featureid) {
		this.featureid = featureid;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

}
