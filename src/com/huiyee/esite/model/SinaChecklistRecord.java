package com.huiyee.esite.model;

import java.io.Serializable;
import java.util.Date;

public class SinaChecklistRecord implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7203539461652478022L;
	private long id;
	private long shareid;
	private long wbuid;
	private long wbid;
	private String content;
	private String imgPath;
	private String mimgPath;
	private String simgPath;
	private Date createtime;
	private int repostsCount;
	private int commentsCount;
	private int attitudesCount;
	private String status = "EDT";
	private SinaUser user;
	private long categoryid;
	private int idx;

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

	public long getWbuid() {
		return wbuid;
	}

	public void setWbuid(long wbuid) {
		this.wbuid = wbuid;
	}

	public long getWbid() {
		return wbid;
	}

	public void setWbid(long wbid) {
		this.wbid = wbid;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public int getRepostsCount() {
		return repostsCount;
	}

	public void setRepostsCount(int repostsCount) {
		this.repostsCount = repostsCount;
	}

	public int getCommentsCount() {
		return commentsCount;
	}

	public void setCommentsCount(int commentsCount) {
		this.commentsCount = commentsCount;
	}

	public int getAttitudesCount() {
		return attitudesCount;
	}

	public void setAttitudesCount(int attitudesCount) {
		this.attitudesCount = attitudesCount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public SinaUser getUser() {
		return user;
	}

	public void setUser(SinaUser user) {
		this.user = user;
	}

	public String getMimgPath() {
		return mimgPath;
	}

	public void setMimgPath(String mimgPath) {
		this.mimgPath = mimgPath;
	}

	public String getSimgPath() {
		return simgPath;
	}

	public void setSimgPath(String simgPath) {
		this.simgPath = simgPath;
	}

	public long getCategoryid() {
		return categoryid;
	}

	public void setCategoryid(long categoryid) {
		this.categoryid = categoryid;
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

}
