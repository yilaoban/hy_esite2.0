package com.huiyee.esite.dto;

import java.io.Serializable;

import com.huiyee.esite.model.UserUpload;

public class Feature105Dto implements IDto,Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UserUpload userUpload;

	public UserUpload getUserUpload() {
		return userUpload;
	}

	public void setUserUpload(UserUpload userUpload) {
		this.userUpload = userUpload;
	}

}
