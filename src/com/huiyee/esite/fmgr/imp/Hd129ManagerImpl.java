package com.huiyee.esite.fmgr.imp;

import java.util.List;

import com.huiyee.esite.dto.IDto;
import com.huiyee.esite.fdao.IHd129Dao;
import com.huiyee.esite.fmgr.IHdFeatureManager;
import com.huiyee.esite.model.HdModel;
import com.huiyee.esite.model.QueryObject;

public class Hd129ManagerImpl implements IHdFeatureManager{
	private IHd129Dao hd129Dao;
	
	public void setHd129Dao(IHd129Dao hd129Dao) {
		this.hd129Dao = hd129Dao;
	}

	@Override
	public IDto findHdDetail(long hdfid, int pageId, IDto dto) {
		return null;
	}

	@Override
	public HdModel findHdModel(long sitegroupid, long entityid) {
		return hd129Dao.findHdModelName(sitegroupid, entityid);
	}

	@Override
	public List<HdModel> findHdModelList(long sitegroupid) {
		return null;
	}

	@Override
	public IDto queryHdDetailList(long hdfid, int pageId, IDto dto, QueryObject queryObject) {
		return null;
	}

}
