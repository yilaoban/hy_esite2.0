package com.huiyee.weixin.model;

import java.util.Map;

public class WxConfig {

	private String appid;
	private String noncestr;
	private String timestamp;
	private String signature;
	private Map<String, String> map;

	private WxPageShow wps;

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getNoncestr() {
		return noncestr;
	}

	public void setNoncestr(String noncestr) {
		this.noncestr = noncestr;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public WxPageShow getWps() {
		return wps;
	}

	public void setWps(WxPageShow wps) {
		this.wps = wps;
	}

	public Map<String, String> getMap() {
		return map;
	}

	public void setMap(Map<String, String> map) {
		this.map = map;
	}

}
