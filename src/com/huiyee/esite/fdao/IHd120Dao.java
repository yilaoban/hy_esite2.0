package com.huiyee.esite.fdao;

import java.util.List;

import com.huiyee.esite.model.HD120Model;
import com.huiyee.esite.model.Hd120QueryObject;
import com.huiyee.esite.model.HdModel;

public interface IHd120Dao {
	
	public List<HdModel> findHdModel(long sitegroupid);
	
	public int findHdDetailTotal(long hdfid);
	
	public List<HD120Model> findHdDetail(long hdfid,int start,int size);
	
	public HdModel findHdModelName(long sitegroupid,long entityid);
	
	public int findHdDetailTotal(long hdfid,Hd120QueryObject queryObject);

	public List<HD120Model> findHdDetail(long hdfid,Hd120QueryObject queryObject,int start,int size);
}
