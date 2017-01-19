package com.huiyee.esite.model;

import java.util.Date;

public class HD113Model{
	
	private long wbuid;
	private String nickname;
	private String imageurl;
	private long wbid;
	private String content;
	private String bimg;
	private String mimg;
	private String simg;
	private Date createtime;
	private int repostsCount;
	private int commentsCount;
	private int attitudesCount;
	private String terminal;
	public long getWbuid() {
		return wbuid;
	}
	public void setWbuid(long wbuid) {
		this.wbuid = wbuid;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getImageurl() {
		return imageurl;
	}
	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getBimg() {
		return bimg;
	}
	public void setBimg(String bimg) {
		this.bimg = bimg;
	}
	public String getMimg() {
		return mimg;
	}
	public void setMimg(String mimg) {
		this.mimg = mimg;
	}
	public String getSimg() {
		return simg;
	}
	public void setSimg(String simg) {
		this.simg = simg;
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
	public String getTerminal() {
		return terminal;
	}
	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}
	public long getWbid() {
		return wbid;
	}
	public void setWbid(long wbid) {
		this.wbid = wbid;
	}
	
}
