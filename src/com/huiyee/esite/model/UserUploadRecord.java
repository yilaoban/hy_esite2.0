package com.huiyee.esite.model;

import java.io.Serializable;
import java.util.Date;

public class UserUploadRecord implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8592448699315116601L;
	private long id;
	private long uid;
	private String nickName;
	private long uploadid;
	private String smallimg;
	private String midimg;
	private String bigimg;
	private String content;
	private Date uploadtime;
	private Date passtime;
	private String status = "EDT";
	private int issel;//>0“——°‘Ò
	private String idx;//Œª÷√
	public int getIssel() {
	    if(idx!=null&&!"".equals(idx))
	        return 1;
        return 0;
    }
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

	public long getUploadid() {
		return uploadid;
	}

	public void setUploadid(long uploadid) {
		this.uploadid = uploadid;
	}

	public String getSmallimg() {
		return smallimg;
	}

	public void setSmallimg(String smallimg) {
		this.smallimg = smallimg;
	}

	public String getMidimg() {
		return midimg;
	}

	public void setMidimg(String midimg) {
		this.midimg = midimg;
	}

	public String getBigimg() {
		return bigimg;
	}

	public void setBigimg(String bigimg) {
		this.bigimg = bigimg;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getUploadtime() {
		return uploadtime;
	}

	public void setUploadtime(Date uploadtime) {
		this.uploadtime = uploadtime;
	}

	public Date getPasstime() {
		return passtime;
	}

	public void setPasstime(Date passtime) {
		this.passtime = passtime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
    public String getIdx() {
        return idx;
    }
    public void setIdx(String idx) {
        this.idx = idx;
    }
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
}
