package com.huiyee.esite.model;

import java.io.Serializable;
import java.util.Date;

public class Feature118InteractApt implements Serializable{
	private static final long serialVersionUID = 2787291308607564140L;
	private long id;
	private long ownerid;
	private String title;
	private String address;
	private String phone;
	private String pic;
	private String content;
	private Date starttime;
	private Date endtime;
	private int daylimit;
	private int total;
	private String repic;
	private String type;
	private Date createtime;
	private String status;
	private long omid;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getOwnerid() {
		return ownerid;
	}
	public void setOwnerid(long ownerid) {
		this.ownerid = ownerid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getStarttime() {
		return starttime;
	}
	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}
	public Date getEndtime() {
		return endtime;
	}
	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}
	public int getDaylimit() {
		return daylimit;
	}
	public void setDaylimit(int daylimit) {
		this.daylimit = daylimit;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public String getRepic() {
		return repic;
	}
	public void setRepic(String repic) {
		this.repic = repic;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public long getOmid() {
		return omid;
	}
	public void setOmid(long omid) {
		this.omid = omid;
	}
	
}
