package com.huiyee.esite.model;

import java.io.Serializable;
import java.util.Date;

public class SinaShare implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4394798276566577038L;
	private long id;
	private long pageid;
	private String name;
	private String status;
	private Date lastupdatetime;
	private int sharecount;//参与人数
	private String soauthurl;//成功授权转向链接
	private String coauthurl;//取消授权转向链接
	private String oldurl;
	
	public String getSoauthurl() {
		return soauthurl;
	}
	public void setSoauthurl(String soauthurl) {
		this.soauthurl = soauthurl;
	}
	public String getCoauthurl() {
		return coauthurl;
	}
	public void setCoauthurl(String coauthurl) {
		this.coauthurl = coauthurl;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getPageid() {
		return pageid;
	}
	public void setPageid(long pageid) {
		this.pageid = pageid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getLastupdatetime() {
		return lastupdatetime;
	}
	public void setLastupdatetime(Date lastupdatetime) {
		this.lastupdatetime = lastupdatetime;
	}
	public int getSharecount() {
		return sharecount;
	}
	public void setSharecount(int sharecount) {
		this.sharecount = sharecount;
	}
	public String getOldurl() {
		return oldurl;
	}
	public void setOldurl(String oldurl) {
		this.oldurl = oldurl;
	}
}
