package com.huiyee.esite.model;

import java.io.Serializable;
public class FeatureUploadRecommentList implements Serializable{
	/**
     * 
     */
    private static final long serialVersionUID = 5259392282650543627L;
    private long id;
	private long pageid;
	private long uploadid;
	private String name;
    public long getUploadid() {
        return uploadid;
    }
    public void setUploadid(long uploadid) {
        this.uploadid = uploadid;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public long getPageid() {
        return pageid;
    }
    public void setPageid(long pageid) {
        this.pageid = pageid;
    }
}
