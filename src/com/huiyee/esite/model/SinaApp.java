package com.huiyee.esite.model;

import java.io.Serializable;
import java.util.Date;

public class SinaApp implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1960912161151850103L;
	private long id;
	private long appkey;
	private String appsecret;
	private Date createtime;
	private String name;
	private String oauthByCodeUrl;
	private String refurl;
	private String token;
	private String status;
	public String getToken()
	{
		return token;
	}
	public void setToken(String token)
	{
		this.token = token;
	}
	public String getStatus()
	{
		return status;
	}
	public void setStatus(String status)
	{
		this.status = status;
	}
	public String getRefurl()
	{
		return refurl;
	}
	public void setRefurl(String refurl)
	{
		this.refurl = refurl;
	}
	public String getOauthByCodeUrl()
	{
		return oauthByCodeUrl;
	}
	public void setOauthByCodeUrl(String oauthByCodeUrl)
	{
		this.oauthByCodeUrl = oauthByCodeUrl;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getAppkey() {
		return appkey;
	}
	public void setAppkey(long appkey) {
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
