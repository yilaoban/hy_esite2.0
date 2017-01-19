package com.huiyee.esite.model;

import java.io.Serializable;

public class ReportGenderAnalyse implements Serializable
{
    /**
     * 
     */
    private static final long serialVersionUID = -6354785228858788706L;
    private long id;
    private int vfnum;
    private String vfnumstr;
    private int vmnum;
    private String vmnumstr;
    private int vothernum;
    private String vothernumstr;
    private int hfnum;
    private String hfnumstr;
    private int hmnum;
    private String hmnumstr;
    private int hothernum;
    private String hothernumstr;
    private String groupname;
    public String getGroupname() {
        return groupname;
    }
    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public int getVfnum() {
        return vfnum;
    }
    public void setVfnum(int vfnum) {
        this.vfnum = vfnum;
    }
    public int getVmnum() {
        return vmnum;
    }
    public void setVmnum(int vmnum) {
        this.vmnum = vmnum;
    }
    public int getVothernum() {
        return vothernum;
    }
    public void setVothernum(int vothernum) {
        this.vothernum = vothernum;
    }
    public int getHfnum() {
        return hfnum;
    }
    public void setHfnum(int hfnum) {
        this.hfnum = hfnum;
    }
    public int getHmnum() {
        return hmnum;
    }
    public void setHmnum(int hmnum) {
        this.hmnum = hmnum;
    }
    public int getHothernum() {
        return hothernum;
    }
    public void setHothernum(int hothernum) {
        this.hothernum = hothernum;
    }
    public String getVfnumstr() {
        return vfnumstr;
    }
    public void setVfnumstr(String vfnumstr) {
        this.vfnumstr = vfnumstr;
    }
    public String getVmnumstr() {
        return vmnumstr;
    }
    public void setVmnumstr(String vmnumstr) {
        this.vmnumstr = vmnumstr;
    }
    public String getVothernumstr() {
        return vothernumstr;
    }
    public void setVothernumstr(String vothernumstr) {
        this.vothernumstr = vothernumstr;
    }
    public String getHfnumstr() {
        return hfnumstr;
    }
    public void setHfnumstr(String hfnumstr) {
        this.hfnumstr = hfnumstr;
    }
    public String getHmnumstr() {
        return hmnumstr;
    }
    public void setHmnumstr(String hmnumstr) {
        this.hmnumstr = hmnumstr;
    }
    public String getHothernumstr() {
        return hothernumstr;
    }
    public void setHothernumstr(String hothernumstr) {
        this.hothernumstr = hothernumstr;
    }
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
}
