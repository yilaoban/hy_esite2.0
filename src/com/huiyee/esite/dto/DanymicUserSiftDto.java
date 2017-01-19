package com.huiyee.esite.dto;

public class DanymicUserSiftDto {
	private String startTime;
	private String endTime;
	private long action;
    private String dcount;//»¥¶¯´ÎÊý
    public String getStartTime() {
        return startTime;
    }
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
    public String getEndTime() {
        return endTime;
    }
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
    public long getAction() {
        return action;
    }
    public void setAction(long action) {
        this.action = action;
    }
    public String getDcount() {
        return dcount;
    }
    public void setDcount(String dcount) {
        this.dcount = dcount;
    }
}
