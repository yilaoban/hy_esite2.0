package com.huiyee.esite.fdao;

import java.util.List;

import com.huiyee.esite.model.BBSForum;
import com.huiyee.esite.model.HD151Model;
import com.huiyee.esite.model.PageBlockRelation;

public interface IHd151Dao {

	 public long saveFeatureInteract(final long pageid);
	 
	 public HD151Model findForumidListByFid(long fid);
	 
	 public List<BBSForum> findForumListByOwnerid(long ownerid,long accid);
	 
	 public int updateFeatureIneractCommunity(String forumid,long fid);
	 
	 public PageBlockRelation findPageBlockRelationByRelationid(long relationid);
	 
	 public int updatePageBlockRelationByRelationid(long relationid,String json);
	 
	 public BBSForum findBBSForumById(long id);
}
