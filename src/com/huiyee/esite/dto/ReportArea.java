package com.huiyee.esite.dto;

public class ReportArea {
    private long aid;
	private String area;
	private String ip;
	private int num;
	private String numpercent;
    public String getArea() {
        return area;
    }
    public void setArea(String area) {
        this.area = area;
    }
    public int getNum() {
        return num;
    }
    public void setNum(int num) {
        this.num = num;
    }
    public String getIp() {
        return ip;
    }
    public void setIp(String ip) {
        this.ip = ip;
    }
    public long getAid() {
        return aid;
    }
    public void setAid(long aid) {
        this.aid = aid;
    }
	public String getNumpercent()
	{
		return numpercent;
	}
	public void setNumpercent(String numpercent)
	{
		this.numpercent = numpercent;
	}
}
