package com.huiyee.esite.fdao;

import java.util.List;

import com.huiyee.esite.model.ContentCategory;
import com.huiyee.esite.model.ContentNew;
import com.huiyee.esite.model.FeatureNews;
import com.huiyee.esite.model.FeatureNewslist;

public interface IHd147Dao
{
	public long addNewsList(long pageid);
	
	public List<FeatureNews> findNewsListByFid(long fid);
	
	public List<ContentNew> findNewListByCatid(long catid);
	
	public FeatureNewslist findCatidByFid(long fid);
	
	public List<ContentCategory> findContentCategoryListByOwner(long ownerid);

	public void deleteAllRelation(long fid);
	
	public void addReation(long fid,long nid,int idx);
	
	public void updateFeatureNnewslistCatidByFid(long fid,long catid,long xqpageid);
	
	public int findIdxByfid(long fid);
	
}
