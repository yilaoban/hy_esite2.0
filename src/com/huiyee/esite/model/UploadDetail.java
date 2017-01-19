package com.huiyee.esite.model;

import java.io.Serializable;
import java.util.Date;

public class UploadDetail implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9107897333436242514L;
	
	private long uid;
	private String nickname;
	private Date createtime;
	private String content;
	private String bimg;
	private String simg;
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSimg() {
		return simg;
	}
	public void setSimg(String simg) {
		this.simg = simg;
	}
	public long getUid() {
		return uid;
	}
	public void setUid(long uid) {
		this.uid = uid;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getBimg() {
		return bimg;
	}
	public void setBimg(String bimg) {
		this.bimg = bimg;
	}

}
