package com.huiyee.esite.fmgr.imp;

import java.util.List;

import com.huiyee.esite.dto.IDto;
import com.huiyee.esite.fdao.IHd133Dao;
import com.huiyee.esite.fmgr.IHdFeatureManager;
import com.huiyee.esite.model.HdModel;
import com.huiyee.esite.model.QueryObject;

public class Hd133ManagerImpl implements IHdFeatureManager {

	private IHd133Dao hd133Dao;
	
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
    	return hd133Dao.findHdModelName(sitegroupid, entityid);
    }

	@Override
	public IDto queryHdDetailList(long hdfid, int pageId, IDto dto,QueryObject queryObject) {
		return null;
	}

	public void setHd133Dao(IHd133Dao hd133Dao) {
		this.hd133Dao = hd133Dao;
	}
	
}
