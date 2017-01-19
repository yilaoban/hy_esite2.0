package com.huiyee.esite.mgr;

import java.util.List;

import com.huiyee.esite.model.BBSCategory;
import com.huiyee.esite.model.BBSForum;
import com.huiyee.esite.model.Page;

public interface IBBSManager
{
	public List<BBSCategory> findForumByOwnerid(long ownerid);
	
	public int findTotlaForumByOwnerid(long ownerid,long accid);
	
	public List<BBSForum> findForumListByOwnerid(long ownerid,long accid,int start,int size);
	
	public String saveBBSCategoryByOwner(long ownerid);
	
	public BBSCategory findCatNameByCatid(long catid);
	
	public long saveBBSForum(String title,long catid);
	
	public long saveBBSForum(String title,long catid,long accid,long ownerid );
	
	public int findForumByOwneridAndTitle(String title,long cateid,long ownerid);
	
	public int updateForumName(BBSForum forum);
	
	public List<BBSCategory> findBBSCategoryByOwnerid(long ownerid);
	
	public int updateBBSForumByForumid(BBSForum forum,String ip,long owner);
	
	public int updateBBSForumPermissionByForumid(BBSForum forum);
	
	public BBSForum findBBSForumById(long id);
	
	public List<Page> findPageidByOwnerid(long ownerid);
	
	public int delforum(long forumid);

	public List<Long> findEntityByType(long owner, String type);

	public List<String> findSource(long nid);
	
	public long findBBSCateByTypeName(String typeName, long owner);
	
	public long saveBBSCate(String typeName, long ownerid);

}
