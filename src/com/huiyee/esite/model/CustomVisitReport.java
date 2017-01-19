package com.huiyee.esite.model;

import java.io.Serializable;
import java.util.Date;

public class CustomVisitReport implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4980426556131884757L;
	
	private String name;
	private String lasttime;
	private int num;
	private String ip;
	private String area;
	private String startdate;
	private String enddate;
	private long siteid;
	private String wbuid;
	private String terminal;
	private Date createtime;
	
	
	
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
	public String getStartdate() {
		return startdate;
	}
	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}
	public String getEnddate() {
		return enddate;
	}
	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLasttime() {
		return lasttime;
	}
	public void setLasttime(String lasttime) {
		this.lasttime = lasttime;
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
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
    public Date getCreatetime() {
        return createtime;
    }
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
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
