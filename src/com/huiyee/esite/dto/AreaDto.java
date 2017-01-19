package com.huiyee.esite.dto;

import java.util.List;

import com.huiyee.esite.model.AreaCity;
import com.huiyee.esite.model.AreaProvince;

public class AreaDto implements IDto{
	
	private List<AreaProvince> province;
	private List<AreaCity> city;
	public List<AreaProvince> getProvince() {
		return province;
	}
	public void setProvince(List<AreaProvince> province) {
		this.province = province;
	}
	public List<AreaCity> getCity() {
		return city;
	}
	public void setCity(List<AreaCity> city) {
		this.city = city;
	}
}
