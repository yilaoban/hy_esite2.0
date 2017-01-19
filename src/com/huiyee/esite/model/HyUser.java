package com.huiyee.esite.model;

import java.io.Serializable;
import java.util.Date;

import com.huiyee.esite.dto.HuDetail;

public class HyUser implements Serializable {
	private static final long serialVersionUID = 1L;

	private long id;
	private String username;
	private String password;
	private long wxuid;
	private long wbuid;
	private long owner;
	private String nickname;
	private String telphone;
	private String email;
	private HuDetail huDetail;
	private String img;

	private WxUser wxUser;
	private int balance_total;
	private int balance_used;
	private Date birthday;
	private int gender;
	private long levelid;
	private String levelname;

	public long getLevelid() {
		return levelid;
	}

	public void setLevelid(long levelid) {
		this.levelid = levelid;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public long getOwner() {
		return owner;
	}

	public void setOwner(long owner) {
		this.owner = owner;
	}

	public void setWxuid(long wxuid) {
		this.wxuid = wxuid;
	}

	public void setWbuid(long wbuid) {
		this.wbuid = wbuid;
	}

	public long getWxuid() {
		return wxuid;
	}

	public long getWbuid() {
		return wbuid;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelphone() {
		return telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	public HuDetail getHuDetail() {
		return huDetail;
	}

	public void setHuDetail(HuDetail huDetail) {
		this.huDetail = huDetail;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public WxUser getWxUser() {
		return wxUser;
	}

	public void setWxUser(WxUser wxUser) {
		this.wxUser = wxUser;
	}

	public int getBalance_total() {
		return balance_total;
	}

	public void setBalance_total(int balance_total) {
		this.balance_total = balance_total;
	}

	public int getBalance_used() {
		return balance_used;
	}

	public void setBalance_used(int balance_used) {
		this.balance_used = balance_used;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public String getLevelname() {
		return levelname;
	}

	public void setLevelname(String levelname) {
		this.levelname = levelname;
	}

}
