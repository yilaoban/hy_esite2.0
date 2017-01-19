package com.huiyee.esite.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class SinaShareRecordCategory implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5582791820646450977L;
	private long id;
	private String name;
	private long listid;
	private int idx;
	private Date createtime;
	private List<SinaChecklistRecord> list;
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
	public long getListid() {
		return listid;
	}
	public void setListid(long listid) {
		this.listid = listid;
	}
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public List<SinaChecklistRecord> getList() {
		return list;
	}
	public void setList(List<SinaChecklistRecord> list) {
		this.list = list;
	}
	
}
