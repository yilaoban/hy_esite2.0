package com.huiyee.esite.model;

import java.io.Serializable;

public class SinaShareUser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2336824968443222900L;
	private long wbuid;
	private String token;
	public long getWbuid() {
		return wbuid;
	}
	public void setWbuid(long wbuid) {
		this.wbuid = wbuid;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
}
