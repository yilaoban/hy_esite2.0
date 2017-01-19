package com.huiyee.interact.leader.model;

import java.io.Serializable;
import java.util.Date;

public class LeaderLog implements Serializable{
	
	private static final long serialVersionUID = -2112860426038072213L;
	private long id;
	private long hyuid;
	private String type;
	private String subtype;
	private String hydesc;
	private long enid;
	private Date createtime;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getHyuid() {
		return hyuid;
	}
	public void setHyuid(long hyuid) {
		this.hyuid = hyuid;
	}
	public long getEnid() {
		return enid;
	}
	public void setEnid(long enid) {
		this.enid = enid;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSubtype() {
		return subtype;
	}
	public void setSubtype(String subtype) {
		this.subtype = subtype;
	}
	public String getHydesc() {
		return hydesc;
	}
	public void setHydesc(String hydesc) {
		this.hydesc = hydesc;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	
	
}
