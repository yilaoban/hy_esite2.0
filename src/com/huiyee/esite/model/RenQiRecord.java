package com.huiyee.esite.model;

import java.util.Date;

public class RenQiRecord {
	
	private long id;
	private long rqid;
	private long duid;//参与者的uid
	private long fuid;//发起者的uid
	private int utype;
	private int addjf;
	private String ip;
	private String terminal;
	private String source;
	private Date createtime;
	private String dname;
	private String fname;
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
	public long getDuid() {
		return duid;
	}
	public void setDuid(long duid) {
		this.duid = duid;
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
	public int getAddjf() {
		return addjf;
	}
	public void setAddjf(int addjf) {
		this.addjf = addjf;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getTerminal() {
		return terminal;
	}
	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}

}
