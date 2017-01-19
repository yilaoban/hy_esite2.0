package com.huiyee.esite.dto;

import com.huiyee.esite.model.SinaShareUser;

public class UserOauthDto implements IDto {
	
	private String refUrl;
	public String getRefUrl() {
		return refUrl;
	}
	public void setRefUrl(String refUrl) {
		this.refUrl = refUrl;
	}
}
