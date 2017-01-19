package com.huiyee.esite.dto;

import java.util.List;
public class VisitViewAllDto implements IDto {
    private List<Integer>  hvisitnum;
    private List<Integer>  nhvisitnum;
    private List<Integer>  nhvisitadd;
    private List<Integer>  hdynamicnum;
    private List<List<Object>>  zongduan;
    private List<List<Object>>  area;
    private List<List<Object>>  fensi;
    private List<List<Object>>  source;
    private List<List<Object>>  sex;
    private int hfansY;
    private int hfansN;
    private List<String>  datelist;
    private String groupname;
    public List<Integer> getHvisitnum() {
        return hvisitnum;
    }
    public void setHvisitnum(List<Integer> hvisitnum) {
        this.hvisitnum = hvisitnum;
    }
    public List<List<Object>> getZongduan() {
        return zongduan;
    }
    public void setZongduan(List<List<Object>> zongduan) {
        this.zongduan = zongduan;
    }
    public List<List<Object>> getArea() {
        return area;
    }
    public void setArea(List<List<Object>> area) {
        this.area = area;
    }
    public List<List<Object>> getFensi() {
        return fensi;
    }
    public void setFensi(List<List<Object>> fensi) {
        this.fensi = fensi;
    }
    public List<List<Object>> getSource() {
        return source;
    }
    public void setSource(List<List<Object>> source) {
        this.source = source;
    }
    public List<List<Object>> getSex() {
        return sex;
    }
    public void setSex(List<List<Object>> sex) {
        this.sex = sex;
    }
    public List<Integer> getHdynamicnum() {
        return hdynamicnum;
    }
    public void setHdynamicnum(List<Integer> hdynamicnum) {
        this.hdynamicnum = hdynamicnum;
    }
    public List<String> getDatelist() {
        return datelist;
    }
    public void setDatelist(List<String> datelist) {
        this.datelist = datelist;
    }
    public List<Integer> getNhvisitnum() {
        return nhvisitnum;
    }
    public void setNhvisitnum(List<Integer> nhvisitnum) {
        this.nhvisitnum = nhvisitnum;
    }
    public List<Integer> getNhvisitadd() {
        return nhvisitadd;
    }
    public void setNhvisitadd(List<Integer> nhvisitadd) {
        this.nhvisitadd = nhvisitadd;
    }
    public int getHfansY() {
        return hfansY;
    }
    public void setHfansY(int hfansY) {
        this.hfansY = hfansY;
    }
    public int getHfansN() {
        return hfansN;
    }
    public void setHfansN(int hfansN) {
        this.hfansN = hfansN;
    }
    public String getGroupname() {
        return groupname;
    }
    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }
}
