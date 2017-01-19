package com.huiyee.esite.model;

import java.io.Serializable;
import java.util.Date;

import net.sf.json.JSONObject;

public class WxUser implements Serializable {
	private static final long serialVersionUID = 1L;

	private long id;
	private long mpid;
	private String openid;
	private String nickname;
	private int sex;
	private String province;
	private String city;
	private String country;
	private String headimgurl;
	private String privilege;
	private int subscribe;
	private Date subscribe_time;
	private String unionid;
	private Date updatetime;
	private String appnickname;

	public WxUser() {

	}

	public WxUser(JSONObject jo) {
		nickname = jo.getString("nickname").trim();
		sex = jo.getInt("sex");
		openid = jo.getString("openid");
		province = jo.getString("province");
		city = jo.getString("city");
		country = jo.getString("country");
		headimgurl = jo.getString("headimgurl");
		// privilege=jo.getString("privilege");
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getMpid() {
		return mpid;
	}

	public void setMpid(long mpid) {
		this.mpid = mpid;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getHeadimgurl() {
		return headimgurl;
	}

	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}

	public String getPrivilege() {
		return privilege;
	}

	public void setPrivilege(String privilege) {
		this.privilege = privilege;
	}

	public int getSubscribe() {
		return subscribe;
	}

	public void setSubscribe(int subscribe) {
		this.subscribe = subscribe;
	}

	public Date getSubscribe_time() {
		return subscribe_time;
	}

	public void setSubscribe_time(Date subscribe_time) {
		this.subscribe_time = subscribe_time;
	}

	public String getUnionid() {
		return unionid;
	}

	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}

	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

	public String getAppnickname() {
		return appnickname;
	}

	public void setAppnickname(String appnickname) {
		this.appnickname = appnickname;
	}

}
