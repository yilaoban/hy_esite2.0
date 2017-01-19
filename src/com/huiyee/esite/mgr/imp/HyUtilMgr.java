package com.huiyee.esite.mgr.imp;

import java.util.List;

import com.huiyee.esite.dao.IHyUtilDao;
import com.huiyee.esite.mgr.IHyUtilMgr;
import com.huiyee.esite.model.OwnerSetting;

public class HyUtilMgr implements IHyUtilMgr
{
	private IHyUtilDao hyUtilDao;
	private IHyUtilDao hyUtilDao2;

	
	public void setHyUtilDao2(IHyUtilDao hyUtilDao2)
	{
		this.hyUtilDao2 = hyUtilDao2;
	}

	public void setHyUtilDao(IHyUtilDao hyUtilDao)
	{
		this.hyUtilDao = hyUtilDao;
	}

	@Override
	public long findOwnerByName(String oname)
	{
		return hyUtilDao.findOwnerByName(oname);
	}

	@Override
	public List<OwnerSetting> findOwnerSetting()
	{
		return hyUtilDao2.findOwnerSetting();
	}

}
