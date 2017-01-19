package com.huiyee.esite.model;

import java.util.Date;

public class RenQiFuidJf {
	
	private long id;
	private long rqid;
	private long fuid;//发起者的uid
	private int utype;
	private int totalnum;
	private int usednum;
	private int lunum;
	private Date createtime;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getRqid() {
		return rqid;
	}
	public void setRqid(long rqid) {
		this.rqid = rqid;
	}
	public long getFuid() {
		return fuid;
	}
	public void setFuid(long fuid) {
		this.fuid = fuid;
	}
	public int getUtype() {
		return utype;
	}
	public void setUtype(int utype) {
		this.utype = utype;
	}
	public int getTotalnum() {
		return totalnum;
	}
	public void setTotalnum(int totalnum) {
		this.totalnum = totalnum;
	}
	public int getUsednum() {
		return usednum;
	}
	public void setUsednum(int usednum) {
		this.usednum = usednum;
	}
	public int getLunum() {
		return lunum;
	}
	public void setLunum(int lunum) {
		this.lunum = lunum;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

}
