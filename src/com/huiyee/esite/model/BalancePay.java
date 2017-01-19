package com.huiyee.esite.model;

import java.util.Date;

public class BalancePay {
	private String mediaorder;
	private Date createtime;
	private String ip;
	private int status;
	private int price;
	private long hyuid;
	private long owner;
	private long rid;
	
	public String getMediaorder() {
		return mediaorder;
	}
	public void setMediaorder(String mediaorder) {
		this.mediaorder = mediaorder;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public long getHyuid() {
		return hyuid;
	}
	public void setHyuid(long hyuid) {
		this.hyuid = hyuid;
	}
	public long getOwner() {
		return owner;
	}
	public void setOwner(long owner) {
		this.owner = owner;
	}
	public long getRid() {
		return rid;
	}
	public void setRid(long rid) {
		this.rid = rid;
	}
	
	
}
