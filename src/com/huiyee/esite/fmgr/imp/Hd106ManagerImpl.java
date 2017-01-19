package com.huiyee.esite.fmgr.imp;

import java.util.List;

import com.huiyee.esite.constants.IPageConstants;
import com.huiyee.esite.dto.HdDetailDto;
import com.huiyee.esite.dto.IDto;
import com.huiyee.esite.dto.Pager;
import com.huiyee.esite.fdao.IHd106Dao;
import com.huiyee.esite.fmgr.IHdFeatureManager;
import com.huiyee.esite.model.HD103Model;
import com.huiyee.esite.model.HD106Model;
import com.huiyee.esite.model.HdModel;
import com.huiyee.esite.model.QueryObject;

public class Hd106ManagerImpl implements IHdFeatureManager {
	
	private IHd106Dao hd106Dao;

	@Override
	public List<HdModel> findHdModelList(long sitegroupid) {
		return hd106Dao.findHdModel(sitegroupid);
	}

	public void setHd106Dao(IHd106Dao hd106Dao) {
		this.hd106Dao = hd106Dao;
	}

	@Override
	public IDto findHdDetail(long hdfid, int pageId,IDto dto) {
		HdDetailDto  dhdto = (HdDetailDto) dto;
		int start = (pageId-1)*IPageConstants.HD_106_DETAIL_LIMIT;
		int total = hd106Dao.findHdDetailTotal(hdfid);
		if(total > 0){
			List<HD106Model> list = hd106Dao.findHdDetail(hdfid, start, IPageConstants.HD_106_DETAIL_LIMIT);			
			dhdto.setHd106Model(list);
		}
		dhdto.setPager(new Pager(pageId,total,IPageConstants.HD_106_DETAIL_LIMIT));
		return dto;
	}

    @Override
    public HdModel findHdModel(long sitegroupid, long entityid) {
        return hd106Dao.findHdModelName(sitegroupid, entityid);
    }

	@Override
	public IDto queryHdDetailList(long hdfid, int pageId, IDto dto,
			QueryObject queryObject) {
		return null;
	}

}
