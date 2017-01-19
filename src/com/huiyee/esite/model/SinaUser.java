package com.huiyee.esite.model;

import java.io.Serializable;

public class SinaUser implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -562381263293081254L;
	private long wbuid;
	private String nickname;
	private String imageurl;
	private String gender;
	private int followersCount;
	private int friendsCount;
	private int statusesCount;
	private int createtime;
	private int updatetime;

	public long getWbuid() {
		return wbuid;
	}

	public void setWbuid(long wbuid) {
		this.wbuid = wbuid;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getFollowersCount() {
		return followersCount;
	}

	public void setFollowersCount(int followersCount) {
		this.followersCount = followersCount;
	}

	public int getFriendsCount() {
		return friendsCount;
	}

	public void setFriendsCount(int friendsCount) {
		this.friendsCount = friendsCount;
	}

	public int getStatusesCount() {
		return statusesCount;
	}

	public void setStatusesCount(int statusesCount) {
		this.statusesCount = statusesCount;
	}

	public int getCreatetime() {
		return createtime;
	}

	public void setCreatetime(int createtime) {
		this.createtime = createtime;
	}

	public int getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(int updatetime) {
		this.updatetime = updatetime;
	}

	public String getImageurl() {
		return imageurl;
	}

	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}
}
