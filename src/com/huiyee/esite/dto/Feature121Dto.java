package com.huiyee.esite.dto;

import java.io.Serializable;
public class Feature121Dto implements IDto,Serializable{
/**
     * 
     */
    private static final long serialVersionUID = 2056460103473715606L;
    private String name;
    private long fid;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public long getFid() {
        return fid;
    }
    public void setFid(long fid) {
        this.fid = fid;
    }
}
