package com.huiyee.esite.fdao;

import java.util.List;

import com.huiyee.esite.model.HD111Model;
import com.huiyee.esite.model.HdModel;

public interface IHd111Dao {

	public List<HdModel> findHdModel(long sitegroupid);
	
	public int findHdDetailTotal(long hdfid);
	
	public List<HD111Model> findHdDetail(long hdfid,int start,int size);
	
	public HdModel findHdModelName(long sitegroupid,long entityid);
}
