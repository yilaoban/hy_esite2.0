package com.huiyee.esite.dto;

import java.util.Date;


public class DanymicActionRecord {
    private long id;
    private long sitegroupid;
    private String hour;
    private String ip;
    private Date createtime;
    private String gender;
    public String getHour() {
        return hour;
    }
    public void setHour(String hour) {
        this.hour = hour;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public long getSitegroupid() {
        return sitegroupid;
    }
    public void setSitegroupid(long sitegroupid) {
        this.sitegroupid = sitegroupid;
    }
    public String getIp() {
        return ip;
    }
    public void setIp(String ip) {
        this.ip = ip;
    }
    public Date getCreatetime() {
        return createtime;
    }
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    
}
