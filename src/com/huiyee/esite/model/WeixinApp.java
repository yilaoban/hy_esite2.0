package com.huiyee.esite.model;

import java.io.Serializable;
import java.util.Date;

public class WeixinApp implements Comparable<WeixinApp>,Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8469764918607303053L;
	
	private long id;
	private String name;
	private long ownerid;
	private String appkey;
	private String appsecret;
	private Date createtime;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getOwnerid() {
		return ownerid;
	}
	public void setOwnerid(long ownerid) {
		this.ownerid = ownerid;
	}
	public String getAppkey() {
		return appkey;
	}
	public void setAppkey(String appkey) {
		this.appkey = appkey;
	}
	public String getAppsecret() {
		return appsecret;
	}
	public void setAppsecret(String appsecret) {
		this.appsecret = appsecret;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	@Override
	public int compareTo(WeixinApp o)
	{
		return (int) (this.id - o.getId());
	}
	
}
