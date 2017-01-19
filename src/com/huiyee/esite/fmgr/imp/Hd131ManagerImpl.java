package com.huiyee.esite.fmgr.imp;

import java.util.List;

import com.huiyee.esite.dto.IDto;
import com.huiyee.esite.fdao.IHd131Dao;
import com.huiyee.esite.fmgr.IHdFeatureManager;
import com.huiyee.esite.model.HdModel;
import com.huiyee.esite.model.QueryObject;

public class Hd131ManagerImpl implements IHdFeatureManager {

	private IHd131Dao hd131Dao;
	
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
    	return hd131Dao.findHdModelName(sitegroupid, entityid);
    }

	@Override
	public IDto queryHdDetailList(long hdfid, int pageId, IDto dto,QueryObject queryObject) {
		return null;
	}

	public void setHd131Dao(IHd131Dao hd131Dao) {
		this.hd131Dao = hd131Dao;
	}
	
}
