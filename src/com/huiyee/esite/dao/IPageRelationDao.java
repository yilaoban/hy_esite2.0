package com.huiyee.esite.dao;

import java.util.List;

import com.huiyee.esite.model.PageBlockRelation;
import com.huiyee.esite.model.PageRelation;

public interface IPageRelationDao {
	
	public List<PageRelation> findPageRelationListByPageid(long pageid);
	
	public int updatePageBlockRelationByRelationid(long relationid,String json);
	
	public PageBlockRelation findPageBlockRelationByRelationid(long relationid);
	
}
