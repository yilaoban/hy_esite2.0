package com.huiyee.esite.fmgr.imp;

import java.util.List;

import com.huiyee.esite.constants.IPageConstants;
import com.huiyee.esite.dto.HdDetailDto;
import com.huiyee.esite.dto.IDto;
import com.huiyee.esite.dto.Pager;
import com.huiyee.esite.fdao.IHd111Dao;
import com.huiyee.esite.fmgr.IHdFeatureManager;
import com.huiyee.esite.model.HD106Model;
import com.huiyee.esite.model.HD111Model;
import com.huiyee.esite.model.HdModel;
import com.huiyee.esite.model.QueryObject;

public class Hd111ManagerImpl implements IHdFeatureManager {
	
	private IHd111Dao hd111Dao;
	
	@Override
	public List<HdModel> findHdModelList(long sitegroupid) {
		return hd111Dao.findHdModel(sitegroupid);
	}

	public void setHd111Dao(IHd111Dao hd111Dao) {
		this.hd111Dao = hd111Dao;
	}

	@Override
	public IDto findHdDetail(long hdfid, int pageId,IDto dto) {
		HdDetailDto  dhdto = (HdDetailDto) dto;
		int start = (pageId-1)*IPageConstants.HD_111_DETAIL_LIMIT;
		int total = hd111Dao.findHdDetailTotal(hdfid);
		if(total > 0){
			List<HD111Model> list = hd111Dao.findHdDetail(hdfid, start, IPageConstants.HD_111_DETAIL_LIMIT);			
			dhdto.setHd111Model(list);
		}
		dhdto.setPager(new Pager(pageId,total,IPageConstants.HD_111_DETAIL_LIMIT));
		return dto;
	}

    @Override
    public HdModel findHdModel(long sitegroupid, long entityid) {
        return hd111Dao.findHdModelName(sitegroupid, entityid);
    }

	@Override
	public IDto queryHdDetailList(long hdfid, int pageId, IDto dto,
			QueryObject queryObject) {
		return null;
	}
}
