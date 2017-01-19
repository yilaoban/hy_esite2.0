package com.huiyee.esite.dao;

import java.util.List;

import com.huiyee.esite.model.AreaCity;
import com.huiyee.esite.model.AreaProvince;

public interface IAreaDao {

	public List<AreaProvince> findAreaProvince();
	
	public List<AreaCity> findAreaCity(String provinceId);
}
