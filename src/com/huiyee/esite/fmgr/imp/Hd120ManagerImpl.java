package com.huiyee.esite.fmgr.imp;

import java.util.List;

import com.huiyee.esite.constants.IPageConstants;
import com.huiyee.esite.dto.HdDetailDto;
import com.huiyee.esite.dto.IDto;
import com.huiyee.esite.dto.Pager;
import com.huiyee.esite.fdao.IHd120Dao;
import com.huiyee.esite.fmgr.IHdFeatureManager;
import com.huiyee.esite.model.HD113Model;
import com.huiyee.esite.model.HD117Model;
import com.huiyee.esite.model.HD120Model;
import com.huiyee.esite.model.Hd117QueryObject;
import com.huiyee.esite.model.Hd120QueryObject;
import com.huiyee.esite.model.HdModel;
import com.huiyee.esite.model.QueryObject;

public class Hd120ManagerImpl implements IHdFeatureManager{
	private IHd120Dao hd120Dao; 
	
	public void setHd120Dao(IHd120Dao hd120Dao) {
		this.hd120Dao = hd120Dao;
	}

	@Override
	public List<HdModel> findHdModelList(long sitegroupid) {
		return hd120Dao.findHdModel(sitegroupid);
	}
	
	@Override
	public IDto findHdDetail(long hdfid, int pageId, IDto dto) {
		HdDetailDto  dhdto = (HdDetailDto) dto;
		int start = (pageId-1)*IPageConstants.HD_113_DETAIL_LIMIT;
		int total = hd120Dao.findHdDetailTotal(hdfid);
		if(total > 0){
			List<HD120Model> list = hd120Dao.findHdDetail(hdfid, start, IPageConstants.HD_113_DETAIL_LIMIT);			
			dhdto.setHd120Model(list);
		}
		dhdto.setPager(new Pager(pageId,total,IPageConstants.HD_113_DETAIL_LIMIT));
		return dto;
	}

	@Override
	public HdModel findHdModel(long sitegroupid, long entityid) {
		return hd120Dao.findHdModelName(sitegroupid, entityid);
	}

	@Override
	public IDto queryHdDetailList(long hdfid, int pageId, IDto dto,
			QueryObject queryObject) {
		Hd120QueryObject queryObj = (Hd120QueryObject) queryObject;
		HdDetailDto dhdto = (HdDetailDto) dto;
		int start = (pageId-1)*IPageConstants.HD_113_DETAIL_LIMIT;
		int total = hd120Dao.findHdDetailTotal(hdfid,queryObj);
		if(total > 0){
			List<HD120Model> list = hd120Dao.findHdDetail(hdfid,queryObj,start, IPageConstants.HD_113_DETAIL_LIMIT);			
			dhdto.setHd120Model(list);
		}
		dhdto.setPager(new Pager(pageId,total,IPageConstants.HD_113_DETAIL_LIMIT));
		return dto;
	}

}
