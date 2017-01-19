package com.huiyee.esite.mgr.imp;

import java.util.List;

import com.huiyee.esite.constants.IPageConstants;
import com.huiyee.esite.dao.IAccountDao;
import com.huiyee.esite.dao.IAreaDao;
import com.huiyee.esite.mgr.IAccountManager;
import com.huiyee.esite.mgr.IAreaManager;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.model.AreaCity;
import com.huiyee.esite.model.AreaProvince;
import com.huiyee.esite.model.Owner;

public class AreaManagerImpl implements IAreaManager {

	private IAreaDao areaDao;
	
	@Override
	public List<AreaProvince> findAreaPorvince() {
		return areaDao.findAreaProvince();
	}

	public void setAreaDao(IAreaDao areaDao) {
		this.areaDao = areaDao;
	}

	@Override
	public List<AreaCity> findAreaCity(String provinceId) {
		return areaDao.findAreaCity(provinceId);
	}
	
}
