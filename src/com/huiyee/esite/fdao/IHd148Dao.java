package com.huiyee.esite.fdao;

import java.util.List;

import com.huiyee.esite.model.ContentCategory;
import com.huiyee.esite.model.ContentPicture;
import com.huiyee.esite.model.FeaturePiclist;
import com.huiyee.esite.model.Picture;

public interface IHd148Dao
{
	 public long saveFeturePicture(final long pageid);
	 
	 public FeaturePiclist findCatidByFid(long fid);
	 
	 public List<ContentCategory> findContentCategoryListByOwner(long ownerid);
	 
	 public List<ContentPicture> findPicListByCatid(long catid);
	 
	 public void updateFeaturePiclistCatidByFid(long fid,long catid);
	 
	 public void deleteAllRelation(long fid);
	 
	 public int findIdxByfid(long fid);
	 
	 public void addReation(long fid,long pid,int idx);
	 
	 public List<Picture> findPictureById(long id);
}
