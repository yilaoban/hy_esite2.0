package com.huiyee.esite.dto;

import java.util.Date;

public class DanymicUserRecord {
    private long id;
	private Date lastactiontime;
	private long action;//活动类型
    private int dcount;//互动次数
    private String ip;
    private String area;
    private String nickname;
    private long siteid;
    private String wbuid;
    private String actiontype;
    private String terminal;
    public Date getLastactiontime() {
        return lastactiontime;
    }
    public void setLastactiontime(Date lastactiontime) {
        this.lastactiontime = lastactiontime;
    }
    public long getAction() {
        return action;
    }
    public void setAction(long action) {
        this.action = action;
    }
    public int getDcount() {
        return dcount;
    }
    public void setDcount(int dcount) {
        this.dcount = dcount;
    }
    public String getIp() {
        return ip;
    }
    public void setIp(String ip) {
        this.ip = ip;
    }
    public String getArea() {
        return area;
    }
    public void setArea(String area) {
        this.area = area;
    }
    public String getNickname() {
        return nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public long getSiteid() {
        return siteid;
    }
    public void setSiteid(long siteid) {
        this.siteid = siteid;
    }
    public String getWbuid() {
        return wbuid;
    }
    public void setWbuid(String wbuid) {
        this.wbuid = wbuid;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getActiontype() {
        return actiontype;
    }
    public void setActiontype(String actiontype) {
        this.actiontype = actiontype;
    }
	public String getTerminal()
	{
		return terminal;
	}
	public void setTerminal(String terminal)
	{
		this.terminal = terminal;
	}
}
