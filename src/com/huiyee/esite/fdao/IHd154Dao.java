package com.huiyee.esite.fdao;

import java.util.List;

import com.huiyee.esite.model.ContentCategory;
import com.huiyee.esite.model.HD154Model;
import com.huiyee.esite.model.PageBlockRelation;

public interface IHd154Dao {

	 public long saveFeatureInteract(final long pageid);
	 
	 public HD154Model findForumidListByFid(long fid);
	 
	 public PageBlockRelation findPageBlockRelationByRelationid(long relationid);
	 
	 public int updatePageBlockRelationByRelationid(long relationid,String json);
	 
	 public List<ContentCategory> findCategory(long id,String type);
	 
	 public int saveFid(long fid, String ids, String type, long topage);
	 
	 public int updateContentCategoryPageid(long cateid,long pageid);
	 
}
