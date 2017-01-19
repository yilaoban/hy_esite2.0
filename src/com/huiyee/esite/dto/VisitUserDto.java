package com.huiyee.esite.dto;

public class VisitUserDto implements IDto {

	//开始时间
	private String startdate;
	//结束时间
	private String enddate;
	//访问次数
	private String num;
	
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
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	
	
}
