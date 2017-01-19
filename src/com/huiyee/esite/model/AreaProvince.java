package com.huiyee.esite.model;

import java.io.Serializable;

public class AreaProvince implements Serializable {
	
	private long id ;
	private String provinceId;
	private String province;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	
}
