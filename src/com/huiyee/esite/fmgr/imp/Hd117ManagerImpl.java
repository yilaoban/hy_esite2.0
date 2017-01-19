package com.huiyee.esite.fmgr.imp;

import java.util.List;

import com.huiyee.esite.constants.IPageConstants;
import com.huiyee.esite.dto.HdDetailDto;
import com.huiyee.esite.dto.IDto;
import com.huiyee.esite.dto.Pager;
import com.huiyee.esite.fdao.IHd117Dao;
import com.huiyee.esite.fmgr.IHdFeatureManager;
import com.huiyee.esite.model.HD117Model;
import com.huiyee.esite.model.Hd117QueryObject;
import com.huiyee.esite.model.HdModel;
import com.huiyee.esite.model.QueryObject;

public class Hd117ManagerImpl implements IHdFeatureManager {

	private IHd117Dao hd117Dao;
	
	@Override
	public List<HdModel> findHdModelList(long sitegroupid) {
		return hd117Dao.findHdModel(sitegroupid);
	}

	@Override
	public IDto findHdDetail(long hdfid, int pageId,IDto dto) {
		HdDetailDto  dhdto = (HdDetailDto) dto;
		int start = (pageId-1)*IPageConstants.HD_113_DETAIL_LIMIT;
		int total = hd117Dao.findHdDetailTotal(hdfid);
		if(total > 0){
			List<HD117Model> list = hd117Dao.findHdDetail(hdfid, start, IPageConstants.HD_113_DETAIL_LIMIT);			
			dhdto.setHd117Model(list);
		}
		dhdto.setPager(new Pager(pageId,total,IPageConstants.HD_113_DETAIL_LIMIT));
		return dto;
	}

    @Override
    public HdModel findHdModel(long sitegroupid, long entityid) {
        return hd117Dao.findHdModelName(sitegroupid, entityid);
    }

	@Override
	public IDto queryHdDetailList(long hdfid, int pageId, IDto dto,
			QueryObject queryObject) {
		Hd117QueryObject queryObj = (Hd117QueryObject) queryObject;
		HdDetailDto  dhdto = (HdDetailDto) dto;
		int start = (pageId-1)*IPageConstants.HD_113_DETAIL_LIMIT;
		int total = hd117Dao.findHdDetailTotal(hdfid,queryObj);
		if(total > 0){
			List<HD117Model> list = hd117Dao.findHdDetail(hdfid,queryObj,start, IPageConstants.HD_113_DETAIL_LIMIT);			
			dhdto.setHd117Model(list);
		}
		dhdto.setPager(new Pager(pageId,total,IPageConstants.HD_113_DETAIL_LIMIT));
		return dto;
	}

	public void setHd117Dao(IHd117Dao hd117Dao) {
		this.hd117Dao = hd117Dao;
	}
	
}
