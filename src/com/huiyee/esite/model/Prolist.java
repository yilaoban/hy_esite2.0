package com.huiyee.esite.model;

import java.io.Serializable;

public class Prolist implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7797507852445918433L;
	private long id;
	private String title;
	private String content;
	private long prolist_listid;
	private int idx;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public long getProlist_listid() {
		return prolist_listid;
	}

	public void setProlist_listid(long prolist_listid) {
		this.prolist_listid = prolist_listid;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

}
