package com.huiyee.esite.fdao;

import java.util.List;

import com.huiyee.esite.model.HD121Model;
import com.huiyee.esite.model.Hd113QueryObject;
import com.huiyee.esite.model.HdModel;

public interface IHd121Dao {
	
	public List<HdModel> findHdModel(long sitegroupid);
	
	public int findHdDetailTotal(long hdfid);
	
	public List<HD121Model> findHdDetail(long hdfid,int start,int size);
	
	public int findHdDetailTotal(long hdfid,Hd113QueryObject queryObject);
	
	public List<HD121Model> findHdDetail(long hdfid,Hd113QueryObject queryObject,int start,int size);
	
	public HdModel findHdModelName(long sitegroupid,long entityid);
}
