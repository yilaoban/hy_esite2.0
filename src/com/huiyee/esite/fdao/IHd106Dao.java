package com.huiyee.esite.fdao;

import java.util.List;

import com.huiyee.esite.model.HD106Model;
import com.huiyee.esite.model.HdModel;

public interface IHd106Dao {

	public List<HdModel> findHdModel(long sitegroupid);
	
	public int findHdDetailTotal(long hdfid);
	
	public List<HD106Model> findHdDetail(long hdfid,int start,int size);
	
	public HdModel findHdModelName(long sitegroupid,long entityid);
}
