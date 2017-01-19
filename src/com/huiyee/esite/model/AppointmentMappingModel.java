package com.huiyee.esite.model;

import java.io.Serializable;
import java.util.List;

public class AppointmentMappingModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7131144111250860990L;
	private List<Long> id;
	private List<Long> aptid;;
	private List<String> name;
	private List<String> mapping;
	private List<String> coltype;
	private List<String> stype;
    private List<String> defaultvalue;
	private List<String> show;
	private List<Integer> row;
	private List<String> tag;
	private List<String> req;
	
	
	public List<String> getName() {
		return name;
	}
	public void setName(List<String> name) {
		this.name = name;
	}
	public List<String> getMapping() {
		return mapping;
	}
	public void setMapping(List<String> mapping) {
		this.mapping = mapping;
	}
	
	public List<String> getColtype() {
		return coltype;
	}
	public void setColtype(List<String> coltype) {
		this.coltype = coltype;
	}
	public List<String> getStype() {
		return stype;
	}
	public void setStype(List<String> stype) {
		this.stype = stype;
	}
	public List<String> getShow() {
		return show;
	}
	public void setShow(List<String> show) {
		this.show = show;
	}
	
	public List<Integer> getRow() {
		return row;
	}
	public void setRow(List<Integer> row) {
		this.row = row;
	}
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	public List<String> getDefaultvalue() {
		return defaultvalue;
	}
	public void setDefaultvalue(List<String> defaultvalue) {
		this.defaultvalue = defaultvalue;
	}
	public List<Long> getAptid() {
		return aptid;
	}
	public void setAptid(List<Long> aptid) {
		this.aptid = aptid;
	}
	public List<Long> getId() {
		return id;
	}
	public void setId(List<Long> id) {
		this.id = id;
	}
	public List<String> getTag() {
		return tag;
	}
	public void setTag(List<String> tag) {
		this.tag = tag;
	}
	public List<String> getReq() {
		return req;
	}
	public void setReq(List<String> req) {
		this.req = req;
	}
	
}
