package com.huiyee.esite.fdao;

import java.util.List;

import com.huiyee.esite.model.CategoryTree;
import com.huiyee.esite.model.ContentNew;
import com.huiyee.esite.model.FeatureNews;
import com.huiyee.esite.model.FeatureNewslist;
import com.huiyee.esite.model.PageBlockRelation;

public interface INewsListDao {

	public long addNewsList(long pageid);

	public List<FeatureNews> findNewsListByFid(long fid);

	public void addReation(FeatureNews fn);

	public void deleteAllRelation(long fid);

	public int findIdxByfid(long fid);
	
	public List<ContentNew> findNewsByOwner(long owner);
	
	public ContentNew findNewsByNid(long id);
	
	 public List<Long> findFeatureNewListByFid(long fid);
	 
	 public int saveFeatureNewslistNews(long fid,long nid,long idx);
	 
	 public PageBlockRelation findPageBlockRelationByRelationid(long relationid);
	 
	 public int updatePageBlockRelationByRelationid(long relationid,String json);
	 
	 public List<CategoryTree> findTreeByType(String type, long ownerid);
	 
	 public void updateFeatureNnewslistXpidByFid(long fid,long xqpageid);
	 
	 public FeatureNewslist findCatidByFid(long fid);
	 
	 public int updateContentCategoryPageid(long cateid,long pageid);
	 
}
