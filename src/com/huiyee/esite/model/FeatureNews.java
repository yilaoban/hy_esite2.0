package com.huiyee.esite.model;

import java.io.Serializable;

public class FeatureNews implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2396439445266675764L;
	private long id;
	private long nlid;
	private long nid;
	private int idx;
	private String status;
	private ContentNew news;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getNlid() {
		return nlid;
	}

	public void setNlid(long nlid) {
		this.nlid = nlid;
	}

	public long getNid() {
		return nid;
	}

	public void setNid(long nid) {
		this.nid = nid;
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public ContentNew getNews() {
		return news;
	}

	public void setNews(ContentNew news) {
		this.news = news;
	}

}
