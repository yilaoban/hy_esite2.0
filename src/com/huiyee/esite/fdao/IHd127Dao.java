package com.huiyee.esite.fdao;

import java.util.List;

import com.huiyee.esite.model.HdModel;

public interface IHd127Dao {
	
	public List<HdModel> findHdModel(long sitegroupid);
	
	public int findHdDetailTotal(long hdfid);
	
	public HdModel findHdModelName(long sitegroupid,long entityid);
}
