package com.huiyee.esite.dao;

import com.huiyee.esite.model.MaterialCategory;
import com.huiyee.esite.model.MaterialPic;
import java.util.List;
public interface IMaterialDao {

	
	
	public int findMaterialCount(long fid, long sid);
	
	public List<MaterialPic> findPicList(long fid,long sid,int start,int size);
	
	public List<MaterialCategory> findSCategoryList(long fid);
	
	public List<MaterialCategory> findFCategoryList();
	
	public int findCountById(long id);
	
	
}
