package com.huiyee.esite.model;

import java.io.Serializable;
import java.util.Date;

public class Feature implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 0L;
	private long id;
	private String name;
	private String fname;
	private String type;
	private long mmid;
	private long pageid;
	private Date createtime;
	private long fid;
	private int idx;
	private long pfid;
	
	private String status;
	
	private long interactionCount;//互动总数
	private Date interactionDate;//互动更新时间
	
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public long getMmid() {
		return mmid;
	}
	public void setMmid(long mmid) {
		this.mmid = mmid;
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
	public long getFid() {
		return fid;
	}
	public void setFid(long fid) {
		this.fid = fid;
	}
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public long getPfid() {
		return pfid;
	}
	public void setPfid(long pfid) {
		this.pfid = pfid;
	}
	public long getInteractionCount() {
		return interactionCount;
	}
	public void setInteractionCount(long interactionCount) {
		this.interactionCount = interactionCount;
	}
	public Date getInteractionDate() {
		return interactionDate;
	}
	public void setInteractionDate(Date interactionDate) {
		this.interactionDate = interactionDate;
	}
    public String getFname() {
        return fname;
    }
    public void setFname(String fname) {
        this.fname = fname;
    }
}
