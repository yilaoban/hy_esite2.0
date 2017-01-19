package com.huiyee.interact.ad.dao;

import java.util.List;

import com.huiyee.esite.model.AreaProvince;


public interface IAreaProvinceDao
{

	public List<AreaProvince> findList();

	public List<AreaProvince> findList(String area);

	public AreaProvince findAreaByName(String area);

}
