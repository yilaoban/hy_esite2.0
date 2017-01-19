package com.huiyee.interact.appointment.model;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

public class OrderMappingModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8339772411870892466L;

	private long id;
	private long aptid;
	private String name;
	private String mapping;
	private String coltype;
	private String stype;
	private String defaultvalue;
	private String isshow;
	private int row;
	private String tag;
	private String req;
	private String[] defaultvalueArr;
	
	public long getAptid() {
		return aptid;
	}
	public void setAptid(long aptid) {
		this.aptid = aptid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMapping() {
		return mapping;
	}
	public String getMappingid() {
		if(StringUtils.isNotEmpty(mapping)){
			return mapping.replaceFirst("f", "");
		}
		return mapping;
	}
	public void setMapping(String mapping) {
		this.mapping = mapping;
	}
	public String getColtype() {
		return coltype;
	}
	public void setColtype(String coltype) {
		this.coltype = coltype;
	}
	public String getStype() {
		return stype;
	}
	public void setStype(String stype) {
		this.stype = stype;
	}
	public String getDefaultvalue() {
		return defaultvalue;
	}
	public void setDefaultvalue(String defaultvalue) {
		this.defaultvalue = defaultvalue;
		if(defaultvalue != null){
			this.defaultvalueArr = defaultvalue.split(",");
		}
	}
	public String getIsshow() {
		return isshow;
	}
	public void setIsshow(String isshow) {
		this.isshow = isshow;
	}
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public String[] getDefaultvalueArr() {
		return this.defaultvalueArr;
	}
	public void setDefaultvalueArr(String[] defaultvalueArr) {
		this.defaultvalueArr = defaultvalueArr;
	}
	public String getReq() {
		return req;
	}
	public void setReq(String req) {
		this.req = req;
	}
	
}
