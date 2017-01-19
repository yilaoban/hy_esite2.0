package com.huiyee.esite.model;

import java.io.Serializable;
public class FeatureVideoList implements Serializable{
	/**
     * 
     */
    private static final long serialVersionUID = 5259392282650543627L;
    private long id;
	private long pageid;
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
