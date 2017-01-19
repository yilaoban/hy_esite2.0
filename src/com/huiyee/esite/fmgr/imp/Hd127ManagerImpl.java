package com.huiyee.esite.fmgr.imp;

import java.util.List;

import com.huiyee.esite.dto.IDto;
import com.huiyee.esite.fdao.IHd127Dao;
import com.huiyee.esite.fmgr.IHdFeatureManager;
import com.huiyee.esite.model.HdModel;
import com.huiyee.esite.model.QueryObject;

public class Hd127ManagerImpl implements IHdFeatureManager {
		
	private IHd127Dao hd127Dao;

	public void setHd127Dao(IHd127Dao hd127Dao) {
		this.hd127Dao = hd127Dao;
	}

	@Override
	public List<HdModel> findHdModelList(long sitegroupid) {
		return hd127Dao.findHdModel(sitegroupid);
	}
	
	@Override
	public IDto findHdDetail(long hdfid, int pageId, IDto dto) {
		return null;
	}

	@Override
	public HdModel findHdModel(long sitegroupid, long entityid) {
		return hd127Dao.findHdModelName(sitegroupid, entityid);
	}

	@Override
	public IDto queryHdDetailList(long hdfid, int pageId, IDto dto, QueryObject queryObject){
		return null;
	}

}
