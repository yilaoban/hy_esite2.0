package com.huiyee.esite.fmgr.imp;

import java.util.List;

import com.huiyee.esite.dto.IDto;
import com.huiyee.esite.fdao.IHd135Dao;
import com.huiyee.esite.fmgr.IHdFeatureManager;
import com.huiyee.esite.model.HdModel;
import com.huiyee.esite.model.QueryObject;

public class Hd135ManagerImpl implements IHdFeatureManager {

	private IHd135Dao hd135Dao;
	
	@Override
	public List<HdModel> findHdModelList(long sitegroupid) {
		return null;
	}

	@Override
	public IDto findHdDetail(long hdfid, int pageId,IDto dto) {
		return null;
	}

    @Override
    public HdModel findHdModel(long sitegroupid, long entityid) {
    	return hd135Dao.findHdModelName(sitegroupid, entityid);
    }

	@Override
	public IDto queryHdDetailList(long hdfid, int pageId, IDto dto,QueryObject queryObject) {
		return null;
	}

	public void setHd135Dao(IHd135Dao hd135Dao) {
		this.hd135Dao = hd135Dao;
	}
	
}
