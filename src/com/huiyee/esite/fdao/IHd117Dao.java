package com.huiyee.esite.fdao;

import java.util.List;

import com.huiyee.esite.model.HD117Model;
import com.huiyee.esite.model.Hd117QueryObject;
import com.huiyee.esite.model.HdModel;

public interface IHd117Dao {
	
	public List<HdModel> findHdModel(long sitegroupid);
	
	public int findHdDetailTotal(long hdfid);
	
	public List<HD117Model> findHdDetail(long hdfid,int start,int size);
	
	public int findHdDetailTotal(long hdfid,Hd117QueryObject queryObject);
	
	public List<HD117Model> findHdDetail(long hdfid,Hd117QueryObject queryObject,int start,int size);
	
	public HdModel findHdModelName(long sitegroupid,long entityid);
}
