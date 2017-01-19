package com.huiyee.esite.fmgr.imp;

import java.util.List;

import com.huiyee.esite.dto.IDto;
import com.huiyee.esite.fdao.IHd139Dao;
import com.huiyee.esite.fmgr.IHdFeatureManager;
import com.huiyee.esite.model.HdModel;
import com.huiyee.esite.model.QueryObject;

public class Hd139ManagerImpl implements IHdFeatureManager
{
	private IHd139Dao hd139Dao;
	
	@Override
	public IDto findHdDetail(long hdfid, int pageId, IDto dto)
	{
		return null;
	}

	@Override
	public HdModel findHdModel(long sitegroupid, long entityid)
	{
		return hd139Dao.findHdModelName(sitegroupid, entityid);
	}

	@Override
	public List<HdModel> findHdModelList(long sitegroupid)
	{
		return null;
	}

	@Override
	public IDto queryHdDetailList(long hdfid, int pageId, IDto dto, QueryObject queryObject)
	{
		return null;
	}

	public IHd139Dao getHd139Dao()
	{
		return hd139Dao;
	}

	public void setHd139Dao(IHd139Dao hd139Dao)
	{
		this.hd139Dao = hd139Dao;
	}

	

}
