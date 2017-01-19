package com.huiyee.esite.model;

import java.io.Serializable;
public class FansLevelAnalyse implements Serializable{
    /**
     * 
     */
    private static final long serialVersionUID = -609844233410134906L;
    private int level;
    private int dnum;
    private int vnum;
    public int getLevel() {
        return level;
    }
    public void setLevel(int level) {
        this.level = level;
    }
    public int getDnum() {
        return dnum;
    }
    public void setDnum(int dnum) {
        this.dnum = dnum;
    }
    public int getVnum() {
        return vnum;
    }
    public void setVnum(int vnum) {
        this.vnum = vnum;
    }
}
