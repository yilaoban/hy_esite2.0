package com.huiyee.esite.fmgr;

import java.util.List;

import com.huiyee.esite.dto.IDto;
import com.huiyee.esite.model.HdModel;
import com.huiyee.esite.model.QueryObject;

public interface IHdFeatureManager {
	
	public List<HdModel> findHdModelList(long sitegroupid);
	
	public HdModel findHdModel(long sitegroupid,long entityid);
	
	public IDto findHdDetail(long hdfid,int pageId,IDto dto);
	
	public IDto queryHdDetailList(long hdfid,int pageId,IDto dto,QueryObject queryObject);
	
}
