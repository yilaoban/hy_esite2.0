package com.huiyee.esite.fdao;

import java.util.List;

import com.huiyee.esite.model.HD103Model;
import com.huiyee.esite.model.HdModel;

public interface IHd103Dao {

	public List<HdModel> findHdModelList(long sitegroupid);
	
	public int findHdDetailTotal(long hdfid);
	
	public List<HD103Model> findHdDetail(long hdfid,int start,int size);
	
    public HdModel findHdModel(long sitegroupid,long entityid);
	
}
