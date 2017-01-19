package com.huiyee.esite.model;

import java.io.Serializable;

public class UserUpload implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 9046062850902762288L;
	private long id;
	private long pageid;
	private String name;
	private String content;
	private int mwidth;
	private int mheight;
	private int swidth;
	private int sheight;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getMwidth() {
		return mwidth;
	}

	public void setMwidth(int mwidth) {
		this.mwidth = mwidth;
	}

	public int getMheight() {
		return mheight;
	}

	public void setMheight(int mheight) {
		this.mheight = mheight;
	}

	public int getSwidth() {
		return swidth;
	}

	public void setSwidth(int swidth) {
		this.swidth = swidth;
	}

	public int getSheight() {
		return sheight;
	}

	public void setSheight(int sheight) {
		this.sheight = sheight;
	}
}
