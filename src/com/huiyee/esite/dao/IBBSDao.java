package com.huiyee.esite.dao;

import java.util.List;

import com.huiyee.esite.model.BBSCategory;
import com.huiyee.esite.model.BBSForum;
import com.huiyee.esite.model.Page;
import com.huiyee.interact.bbs.model.BBSUser;

public interface IBBSDao
{
	public List<BBSCategory> findForumByOwnerid(long ownerid);
	
	public int saveBBSCategoryByOwner(long ownerid,String name,int rank);
	
	public BBSCategory findCatNameByCatid(long catid);
	
	public long saveBBSForum(String title, long catid);
	
	public int saveBBSForumAccount(long forumid,long owner,long accid);
	
	public int findTotlaForumByOwnerid(long ownerid,long accid);
	
	public long saveBBSUser(String ip,long owner,long hyuserid);
	
	public int updateBBSUserById(long forumer,String nickname,String img);
	
	public List<BBSForum> findForumListByOwnerid(long ownerid,long accid,int start,int size);
	
	public int updateForumName(BBSForum forum);
	
	public List<BBSCategory> findBBSCategoryByOwnerid(long ownerid);
	
	public int updateBBSForumByForumid(BBSForum forum);
	
	public int updateBBSForumPermissionByForumid(BBSForum forum);
	
	public BBSForum findBBSForumById(long id);
	
	public List<Page> findPageidByOwnerid(long ownerid);
	
	public int delforum(long forumid);

	public List<Long> findEntityByType(long owner, int entitytype);
	
	public int findForumByOwneridAndTitle(String title, long cateid, long ownerid);

	public List<String> findSource(long topicid);
	
	public long findBBSCateByTypeName(String typeName, long owner);
	
	public long addBBSCate(String typeName, long ownerid);
	
	public long saveHyUser(long owner,String nickname,String img);
	
	public BBSUser findBBSUserById(long id);
	
	public int updateHyUserById(long hyuserid,String nickname,String img);
	
	public int updateBBSUserHyUser(long hyuserid,long id);
}
