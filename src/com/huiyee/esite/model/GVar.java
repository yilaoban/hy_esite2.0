package com.huiyee.esite.model;

public class GVar {
	private String var;   //hyvar = ""
	private String desc;
	private String cnt;
	private String value;
	private GK gk;
	public GVar(String var, String cnt,GK gk,String desc,String value) {
		super();
		this.var = var;
		this.cnt = cnt;
		this.desc = desc;
		this.gk = gk;
		this.value = value;
	}
	public String getVar() {
		return var;
	}
	public void setVar(String var) {
		this.var = var;
	}
	public String getCnt() {
		return cnt;
	}
	public void setCnt(String cnt) {
		this.cnt = cnt;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public GK getGk() {
		return gk;
	}
	public void setGk(GK gk) {
		this.gk = gk;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	

}
