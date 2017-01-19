package com.huiyee.esite.dao;

import java.util.List;

import com.huiyee.esite.model.PageFeature;



public interface IPageFeatureDao {

	public long addPageFeature(long pageid,long featureid,long fid,String featurename,String needUser);
	
	public long addPageFeature(long pageid,long featureid,long fid);
	
	public int deletePageFeature(long pfid);
	
	public int updateUpPageFeature(long pfid);
	
	public int updateDownPageFeature(long pfid);
	
	public long findOwneridByPageidAndFid(long pageid,long fid);
	
    public int updateName(long pfid,String name);
    
    public PageFeature findPageFeature(long pfid);
    
    public List<Long> findPfidByPageId(long np, int featureid);
    
}
