package com.huiyee.esite.model;

import java.io.Serializable;

public class Picture implements Serializable{
	/**
     * 
     */
    private static final long serialVersionUID = 594062253897674100L;
    private long id;
    private String title;
	private String imgurl;
	private long vlpid;
	private int idx;
	private String linkurl;
	private String tag;
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
    public String getImgurl() {
        return imgurl;
    }
    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }
    public long getVlpid() {
        return vlpid;
    }
    public void setVlpid(long vlpid) {
        this.vlpid = vlpid;
    }
    public int getIdx() {
        return idx;
    }
    public void setIdx(int idx) {
        this.idx = idx;
    }
	public String getLinkurl()
	{
		return linkurl;
	}
	public void setLinkurl(String linkurl)
	{
		this.linkurl = linkurl;
	}
	public String getTag()
	{
		return tag;
	}
	public void setTag(String tag)
	{
		this.tag = tag;
	}
    
}
