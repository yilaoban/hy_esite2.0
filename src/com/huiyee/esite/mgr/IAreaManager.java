package com.huiyee.esite.mgr;

import java.util.List;

import com.huiyee.esite.model.AreaCity;
import com.huiyee.esite.model.AreaProvince;

public interface IAreaManager {
	
	public List<AreaProvince> findAreaPorvince();
	
	public List<AreaCity> findAreaCity(String provinceId);
}
