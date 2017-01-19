package com.huiyee.interact.lottery.model;

import java.io.Serializable;

public class LotteryUserSub implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2371059247759055112L;
	private long id;
	private long lurid;
	private String username;
	private String userphone;
	private String useremail;
	private String userlocation;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getLurid() {
		return lurid;
	}

	public void setLurid(long lurid) {
		this.lurid = lurid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserphone() {
		return userphone;
	}

	public void setUserphone(String userphone) {
		this.userphone = userphone;
	}

	public String getUseremail() {
		return useremail;
	}

	public void setUseremail(String useremail) {
		this.useremail = useremail;
	}

	public String getUserlocation() {
		return userlocation;
	}

	public void setUserlocation(String userlocation) {
		this.userlocation = userlocation;
	}
}
