package com.huiyee.esite.dto;

import java.util.List;

import org.apache.solr.common.SolrDocument;

public class GroupDto {

	private List<SolrDocument> docs;
	private Pager pager;
	private List<List<Object>> fansList;
	private List<List<Object>> areaList;

	private long owner;
	private String mtype;
	private String atype = "c";
	// hystat
	private String f_l;
	private int year;
	private int month;
	private int week;
	// hylog
	private String day_i;
	private int hour = -1; // -1不限制
	private int new_i = -1;// -1不限制 是否新增用户
	private String area;
	private int gz_i = -1;// 0 非粉 1 粉丝 -1全部
	private long spage; // 分享页面
	// group
	private long groupid;// 目标组id
	private String groupname;// 目标组名字

	public List<SolrDocument> getDocs() {
		return docs;
	}

	public void setDocs(List<SolrDocument> docs) {
		this.docs = docs;
	}

	public Pager getPager() {
		return pager;
	}

	public void setPager(Pager pager) {
		this.pager = pager;
	}

	public List<List<Object>> getFansList() {
		return fansList;
	}

	public void setFansList(List<List<Object>> fansList) {
		this.fansList = fansList;
	}

	public List<List<Object>> getAreaList() {
		return areaList;
	}

	public void setAreaList(List<List<Object>> areaList) {
		this.areaList = areaList;
	}

	public long getOwner() {
		return owner;
	}

	public void setOwner(long owner) {
		this.owner = owner;
	}

	public String getMtype() {
		return mtype;
	}

	public void setMtype(String mtype) {
		this.mtype = mtype;
	}

	public String getAtype() {
		return atype;
	}

	public void setAtype(String atype) {
		this.atype = atype;
	}

	public String getDay_i() {
		return day_i;
	}

	public void setDay_i(String day_i) {
		this.day_i = day_i;
	}

	public String getF_l() {
		return f_l;
	}

	public void setF_l(String f_l) {
		this.f_l = f_l;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getWeek() {
		return week;
	}

	public void setWeek(int week) {
		this.week = week;
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public int getNew_i() {
		return new_i;
	}

	public void setNew_i(int new_i) {
		this.new_i = new_i;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public int getGz_i() {
		return gz_i;
	}

	public void setGz_i(int gz_i) {
		this.gz_i = gz_i;
	}

	public long getSpage() {
		return spage;
	}

	public void setSpage(long spage) {
		this.spage = spage;
	}

	public long getGroupid() {
		return groupid;
	}

	public void setGroupid(long groupid) {
		this.groupid = groupid;
	}

	public String getGroupname() {
		return groupname;
	}

	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}

}
