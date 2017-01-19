package com.huiyee.tfmodel.result;

import java.io.Serializable;
import java.util.List;

import com.huiyee.tfmodel.HyWbUserInfo;

public class TfPageForUser implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String startrow;
	private String endrow;
	private List<HyWbUserInfo>  users; 

	public String getStartrow()
	{
		return startrow;
	}
	public void setStartrow(String startrow)
	{
		this.startrow = startrow;
	}
	public String getEndrow()
	{
		return endrow;
	}
	public void setEndrow(String endrow)
	{
		this.endrow = endrow;
	}
	public List<HyWbUserInfo> getUsers() {
		return users;
	}
	public void setUsers(List<HyWbUserInfo> users) {
		this.users = users;
	}
	

}
