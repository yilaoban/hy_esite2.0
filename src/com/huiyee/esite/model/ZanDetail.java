package com.huiyee.esite.model;

import java.io.Serializable;
import java.util.Date;

public class ZanDetail implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9107897333436242514L;
	
	private long uid;
	private String nickname;
	private Date createtime;
	private long productid;
	private String productname;
	private String productlinkurl;
	public long getUid() {
		return uid;
	}
	public void setUid(long uid) {
		this.uid = uid;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public long getProductid() {
		return productid;
	}
	public void setProductid(long productid) {
		this.productid = productid;
	}
	public String getProductname() {
		return productname;
	}
	public void setProductname(String productname) {
		this.productname = productname;
	}
	public String getProductlinkurl() {
		return productlinkurl;
	}
	public void setProductlinkurl(String productlinkurl) {
		this.productlinkurl = productlinkurl;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

}
