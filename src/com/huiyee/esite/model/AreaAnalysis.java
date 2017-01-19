package com.huiyee.esite.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 区域分析model
 * @author hy
 *
 */
public class AreaAnalysis implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6643825604017702751L;
	//区域
	private String area;
	//ip地址
	private String ip;
	//互动数
	private int num;
	//最后的互动时间
	private String lasttime;
	
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getLasttime() {
		return lasttime;
	}
	public void setLasttime(String string) {
		this.lasttime = string;
	}
	
	
}
