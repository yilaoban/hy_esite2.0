package com.huiyee.esite.model;

import java.io.Serializable;

public class VisitAnalyse implements Serializable{
	/**
     * 
     */
    private static final long serialVersionUID = 1L;
    private long sitegroupid;
	private int visitTotalNum;
	private int visitNum;
	private int hdnum;
	private int addVisitNum;
	private String date;
	public long getSitegroupid() {
        return sitegroupid;
    }
    public void setSitegroupid(long sitegroupid) {
        this.sitegroupid = sitegroupid;
    }
    public int getVisitTotalNum() {
        return visitTotalNum;
    }
    public void setVisitTotalNum(int visitTotalNum) {
        this.visitTotalNum = visitTotalNum;
    }
    public int getVisitNum() {
        return visitNum;
    }
    public void setVisitNum(int visitNum) {
        this.visitNum = visitNum;
    }
    public int getHdnum() {
        return hdnum;
    }
    public void setHdnum(int hdnum) {
        this.hdnum = hdnum;
    }
    public int getAddVisitNum() {
        return addVisitNum;
    }
    public void setAddVisitNum(int addVisitNum) {
        this.addVisitNum = addVisitNum;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
}
