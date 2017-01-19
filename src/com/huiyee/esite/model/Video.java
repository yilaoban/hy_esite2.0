package com.huiyee.esite.model;

import java.io.Serializable;

public class Video implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5821107141370434130L;
	private long id;
	private String title;
	private String picurl;
	private String videourl;
	private String html;
	private String name;
	private long vlvid;
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
    public String getPicurl() {
        return picurl;
    }
    public void setPicurl(String picurl) {
        this.picurl = picurl;
    }
    public String getVideourl() {
        return videourl;
    }
    public void setVideourl(String videourl) {
        this.videourl = videourl;
    }
    public String getHtml() {
        return html;
    }
    public void setHtml(String html) {
        this.html = html;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public long getVlvid() {
        return vlvid;
    }
    public void setVlvid(long vlvid) {
        this.vlvid = vlvid;
    }
    public int getIdx() {
        return idx;
    }
    public void setIdx(int idx) {
        this.idx = idx;
    }

}
