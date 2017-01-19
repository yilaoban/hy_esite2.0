package com.huiyee.esite.mgr.imp;

import java.util.ArrayList;
import java.util.List;

import com.huiyee.esite.dao.IBBSDao;
import com.huiyee.esite.mgr.IBBSManager;
import com.huiyee.esite.model.BBSCategory;
import com.huiyee.esite.model.BBSForum;
import com.huiyee.esite.model.Page;
import com.huiyee.interact.bbs.model.BBSUser;

public class BBSManagerImpl implements IBBSManager
{
	private IBBSDao bbsDao;

	public void setBbsDao(IBBSDao bbsDao)
	{
		this.bbsDao = bbsDao;
	}

	@Override
	public List<BBSCategory> findForumByOwnerid(long ownerid)
	{
		return bbsDao.findForumByOwnerid(ownerid);
	}

	@Override
	public String saveBBSCategoryByOwner(long ownerid)
	{
		List<String> list = new ArrayList<String>();
		list.add("图片");list.add("新闻");list.add("视频");list.add("产品");list.add("论坛");
		for(int i=0;i<list.size();i++){
			bbsDao.saveBBSCategoryByOwner(ownerid,list.get(i),i+1);
		}
		return "Y";
	}

	@Override
	public BBSCategory findCatNameByCatid(long catid)
	{
		return bbsDao.findCatNameByCatid(catid);
	}

	@Override
	public long saveBBSForum(String title, long catid)
	{
		return bbsDao.saveBBSForum(title,catid);
	}
	
	@Override
	public long saveBBSForum(String title, long catid,long accid,long ownerid )
	{
		long forumid = bbsDao.saveBBSForum(title,catid);
		return bbsDao.saveBBSForumAccount(forumid,accid,ownerid);
	}

	@Override
	public List<BBSForum> findForumListByOwnerid(long ownerid,long accid,int start,int size)
	{
		return bbsDao.findForumListByOwnerid(ownerid,accid,start,size);
	}

	@Override
	public int updateForumName(BBSForum forum)
	{
		return bbsDao.updateForumName(forum);
	}

	@Override
	public List<BBSCategory> findBBSCategoryByOwnerid(long ownerid)
	{
		return bbsDao.findBBSCategoryByOwnerid(ownerid);
	}

	@Override
	public int findTotlaForumByOwnerid(long ownerid,long accid)
	{
		return bbsDao.findTotlaForumByOwnerid(ownerid,accid);
	}

	@Override
	public int updateBBSForumByForumid(BBSForum forum,String ip,long owner)
	{
		BBSForum bbsForum = bbsDao.findBBSForumById(forum.getId());
		if(bbsForum.getForumer() == 0){
			long hyuserid = bbsDao.saveHyUser(owner,forum.getNickname(),forum.getImg());
			long forumer = bbsDao.saveBBSUser(ip,owner,hyuserid);
			forum.setForumer(forumer);
			return bbsDao.updateBBSForumByForumid(forum);
		}else{
			BBSUser bbsUser = bbsDao.findBBSUserById(bbsForum.getForumer());
			if(bbsUser != null && bbsUser.getHyuserid() != 0){
				return bbsDao.updateHyUserById(bbsUser.getHyuserid(),forum.getNickname(),forum.getImg());
			}else{
				long hyuserid = bbsDao.saveHyUser(owner,forum.getNickname(),forum.getImg());
				return bbsDao.updateBBSUserHyUser(hyuserid,bbsUser.getId());
			}
//			bbsDao.updateBBSUserById(bbsForum.getForumer(),forum.getNickname(),forum.getImg());
//			forum.setForumer(bbsForum.getForumer());
//			return bbsDao.updateBBSForumByForumid(forum);
		}
	}

	@Override
	public int updateBBSForumPermissionByForumid(BBSForum forum)
	{
		return bbsDao.updateBBSForumPermissionByForumid(forum);
	}

	@Override
	public BBSForum findBBSForumById(long id)
	{
		return bbsDao.findBBSForumById(id);
	}

	@Override
	public List<Page> findPageidByOwnerid(long ownerid) {
		return bbsDao.findPageidByOwnerid(ownerid);
	}

	@Override
	public int delforum(long forumid) {
		return bbsDao.delforum(forumid);
	}
	
	@Override
	public List<Long> findEntityByType(long owner, String type) {
		return bbsDao.findEntityByType(owner,getTypeCode(type));
	}
	
	private int getTypeCode(String type)
	{
		// 0-图文;1-产品;2-新闻;3-视频
		if ("T".equals(type))
		{
			return 1;
		}
		else if ("P".equals(type))
		{
			return 0;
		}
		else if ("V".equals(type))
		{
			return 3;
		}
		else if ("N".equals(type))
		{
			return 2;
		}
		else
		{
			return 0;
		}
	}

	@Override
	public int findForumByOwneridAndTitle(String title, long cateid, long ownerid) {
		return bbsDao.findForumByOwneridAndTitle(title,cateid,ownerid);
	}
	
	@Override
	public List<String> findSource(long topicid) {
		return bbsDao.findSource(topicid);
	}

	@Override
	public long saveBBSCate(String typeName, long ownerid)
	{
		return bbsDao.addBBSCate(typeName, ownerid);
	}

	@Override
	public long findBBSCateByTypeName(String typeName, long owner)
	{
		return bbsDao.findBBSCateByTypeName(typeName, owner);
	}
}
