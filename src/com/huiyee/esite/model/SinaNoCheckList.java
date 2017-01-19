package com.huiyee.esite.model;

import java.io.Serializable;
import java.util.Date;

public class SinaNoCheckList implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8544983204810700425L;
	private long id;
	private String name;
	private int size;
	private long pageid;
	private long shareid;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public long getPageid() {
		return pageid;
	}

	public void setPageid(long pageid) {
		this.pageid = pageid;
	}

	public long getShareid() {
		return shareid;
	}

	public void setShareid(long shareid) {
		this.shareid = shareid;
	}

}
