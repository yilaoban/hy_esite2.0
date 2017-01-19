package com.huiyee.esite.fmgr.imp;

import java.util.List;

import com.huiyee.esite.constants.IPageConstants;
import com.huiyee.esite.dto.HdDetailDto;
import com.huiyee.esite.dto.IDto;
import com.huiyee.esite.dto.Pager;
import com.huiyee.esite.fdao.IHd103Dao;
import com.huiyee.esite.fmgr.IHdFeatureManager;
import com.huiyee.esite.model.HD103Model;
import com.huiyee.esite.model.HdModel;
import com.huiyee.esite.model.QueryObject;

public class Hd103ManagerImpl implements IHdFeatureManager {

	private IHd103Dao hd103Dao;
	@Override
	public List<HdModel> findHdModelList(long sitegroupid) {
		return hd103Dao.findHdModelList(sitegroupid);
	}
	public void setHd103Dao(IHd103Dao hd103Dao) {
		this.hd103Dao = hd103Dao;
	}
	@Override
	public IDto findHdDetail(long hdfid, int pageId,IDto dto) {
		HdDetailDto  dhdto = (HdDetailDto) dto;
		int start = (pageId-1)*IPageConstants.HD_103_DETAIL_LIMIT;
		int total = hd103Dao.findHdDetailTotal(hdfid);
		if(total > 0){
			List<HD103Model> list = hd103Dao.findHdDetail(hdfid, start, IPageConstants.HD_103_DETAIL_LIMIT);			
			dhdto.setHd103Model(list);
		}
		dhdto.setPager(new Pager(pageId,total,IPageConstants.HD_103_DETAIL_LIMIT));
		return dto;
	}
    @Override
    public HdModel findHdModel(long sitegroupid,long entityid) {
        return hd103Dao.findHdModel(sitegroupid,entityid);
    }
	@Override
	public IDto queryHdDetailList(long hdfid, int pageId, IDto dto,
			QueryObject queryObject) {
		return null;
	}
	
}
