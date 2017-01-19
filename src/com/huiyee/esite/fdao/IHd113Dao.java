package com.huiyee.esite.fdao;

import java.util.List;

import com.huiyee.esite.model.HD113Model;
import com.huiyee.esite.model.Hd113QueryObject;
import com.huiyee.esite.model.HdModel;

public interface IHd113Dao {
	
	public List<HdModel> findHdModel(long sitegroupid);
	
	public int findHdDetailTotal(long hdfid);
	
	public List<HD113Model> findHdDetail(long hdfid,int start,int size);
	
	public int findHdDetailTotal(long hdfid,Hd113QueryObject queryObject);
	
	public List<HD113Model> findHdDetail(long hdfid,Hd113QueryObject queryObject,int start,int size);
	
	public HdModel findHdModelName(long sitegroupid,long entityid);
}
