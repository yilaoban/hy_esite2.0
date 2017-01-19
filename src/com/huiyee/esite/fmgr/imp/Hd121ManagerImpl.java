package com.huiyee.esite.fmgr.imp;

import java.util.List;

import com.huiyee.esite.constants.IPageConstants;
import com.huiyee.esite.dto.HdDetailDto;
import com.huiyee.esite.dto.IDto;
import com.huiyee.esite.dto.Pager;
import com.huiyee.esite.fdao.IHd121Dao;
import com.huiyee.esite.fmgr.IHdFeatureManager;
import com.huiyee.esite.model.HD113Model;
import com.huiyee.esite.model.HD121Model;
import com.huiyee.esite.model.Hd113QueryObject;
import com.huiyee.esite.model.HdModel;
import com.huiyee.esite.model.QueryObject;

public class Hd121ManagerImpl implements IHdFeatureManager {

	private IHd121Dao hd121Dao;
	
	@Override
	public List<HdModel> findHdModelList(long sitegroupid) {
		return hd121Dao.findHdModel(sitegroupid);
	}

    public void setHd121Dao(IHd121Dao hd121Dao) {
        this.hd121Dao = hd121Dao;
    }


    @Override
	public IDto findHdDetail(long hdfid, int pageId,IDto dto) {
		HdDetailDto  dhdto = (HdDetailDto) dto;
		int start = (pageId-1)*IPageConstants.HD_121_DETAIL_LIMIT;
		int total = hd121Dao.findHdDetailTotal(hdfid);
		if(total > 0){
			List<HD121Model> list = hd121Dao.findHdDetail(hdfid, start, IPageConstants.HD_121_DETAIL_LIMIT);			
			dhdto.setHd121Model(list);
		}
		dhdto.setPager(new Pager(pageId,total,IPageConstants.HD_121_DETAIL_LIMIT));
		return dto;
	}

    @Override
    public HdModel findHdModel(long sitegroupid, long entityid) {
        return hd121Dao.findHdModelName(sitegroupid, entityid);
    }

	@Override
	public IDto queryHdDetailList(long hdfid, int pageId, IDto dto,
			QueryObject queryObject) {
		Hd113QueryObject queryObj = (Hd113QueryObject) queryObject;
		HdDetailDto  dhdto = (HdDetailDto) dto;
		int start = (pageId-1)*IPageConstants.HD_121_DETAIL_LIMIT;
		int total = hd121Dao.findHdDetailTotal(hdfid,queryObj);
		if(total > 0){
			List<HD121Model> list = hd121Dao.findHdDetail(hdfid,queryObj,start, IPageConstants.HD_121_DETAIL_LIMIT);			
			dhdto.setHd121Model(list);
		}
		dhdto.setPager(new Pager(pageId,total,IPageConstants.HD_121_DETAIL_LIMIT));
		return dto;
	}
	
}
