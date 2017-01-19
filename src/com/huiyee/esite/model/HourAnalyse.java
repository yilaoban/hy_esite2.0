package com.huiyee.esite.model;

import java.io.Serializable;

public class HourAnalyse implements Serializable{
 /**
	 * 
	 */
	private static final long serialVersionUID = -3895218724143467033L;
	   private long sitegroupid;
	private int hdNum;
	private int visitNum;
	private int hour;
	
	public long getSitegroupid() {
        return sitegroupid;
    }
    public void setSitegroupid(long sitegroupid) {
        this.sitegroupid = sitegroupid;
    }
    public int getVisitNum() {
        return visitNum;
    }
    public void setVisitNum(int visitNum) {
        this.visitNum = visitNum;
    }
    public int getHdNum() {
        return hdNum;
    }
    public void setHdNum(int hdNum) {
        this.hdNum = hdNum;
    }
    public int getHour() {
        return hour;
    }
    public void setHour(int hour) {
        this.hour = hour;
    }
}
