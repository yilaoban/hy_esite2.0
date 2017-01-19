package com.huiyee.esite.fmgr.imp;

import java.util.List;

import com.huiyee.esite.dto.IDto;
import com.huiyee.esite.fdao.IHd141Dao;
import com.huiyee.esite.fmgr.IHdFeatureManager;
import com.huiyee.esite.model.HdModel;
import com.huiyee.esite.model.QueryObject;

public class Hd141ManagerImpl implements IHdFeatureManager
{
	private IHd141Dao hd141Dao;
	
	@Override
	public IDto findHdDetail(long hdfid, int pageId, IDto dto)
	{
		return null;
	}

	@Override
	public HdModel findHdModel(long sitegroupid, long entityid)
	{
		return hd141Dao.findHdModelName(sitegroupid, entityid);
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

	public void setHd141Dao(IHd141Dao hd141Dao)
	{
		this.hd141Dao = hd141Dao;
	}

}
