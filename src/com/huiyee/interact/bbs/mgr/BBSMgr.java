package com.huiyee.interact.bbs.mgr;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.huiyee.esite.dao.IContentNewDao;
import com.huiyee.esite.dao.IContentPictureDao;
import com.huiyee.esite.dao.IContentProductDao;
import com.huiyee.esite.dao.IContentVideoDao;
import com.huiyee.esite.dto.EsForum;
import com.huiyee.esite.dto.BBSAccount;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.model.ContentNew;
import com.huiyee.esite.model.ContentPicture;
import com.huiyee.esite.model.ContentProduct;
import com.huiyee.esite.model.ContentVideo;
import com.huiyee.interact.bbs.dao.IBBSDao;
import com.huiyee.interact.bbs.dao.IBBSTopicDao;
import com.huiyee.interact.bbs.dao.IBBSUserDao;
import com.huiyee.interact.bbs.model.BBS;
import com.huiyee.interact.bbs.model.BBSCategory;
import com.huiyee.interact.bbs.model.BBSContent;
import com.huiyee.interact.bbs.model.BBSForum;
import com.huiyee.interact.bbs.model.BBSUser;

public class BBSMgr implements IBBSMgr
{
	private IBBSDao bbsDao;
	private IBBSUserDao bbsUserDao;
	private IBBSTopicDao bbsTopicDao;
	private IContentProductDao contentProductDao;
	private IContentPictureDao contentPictureDao;
	private IContentNewDao contentNewDao;
	private IContentVideoDao contentVideoDao;

	public void setBbsTopicDao(IBBSTopicDao bbsTopicDao)
	{
		this.bbsTopicDao = bbsTopicDao;
	}

	public void setBbsUserDao(IBBSUserDao bbsUserDao)
	{
		this.bbsUserDao = bbsUserDao;
	}

	public void setBbsDao(IBBSDao bbsDao)
	{
		this.bbsDao = bbsDao;
	}

	@Override
	public BBS findBBS(long id)
	{
		return bbsDao.findBBS(id);
	}

	@Override
	public List<BBSForum> findListByOwner(long owner,long accid, String type)
	{
		List<BBSForum> result = new ArrayList<BBSForum>();
		List<BBSCategory> list = bbsDao.findCListByOwner(owner, getTypeName(type));
		if (list.size() > 0)
		{
			for (BBSCategory category : list)
			{
				List<BBSForum> flist = bbsDao.findListByCid(category.getBbsid(),accid);
				if (flist.size() > 0)
				{
					result.addAll(flist);
				}
			}
		}
		return result.size() > 0 ? result : null;
	}

	@Override
	public int saveTopicForContent(long entityId, String type, String entityName, Account account)
	{
		int exist = bbsTopicDao.findTopicExist(entityId, getTypeCode(type));
		if (exist > 0)
		{
			return -1;
		}
		long cateid = 0;
		long forumid = 0;
		long forumer = 0;
		// 如果没有分区 则创建分区
		cateid = bbsDao.findBBSCateByTypeName(getTypeName(type), account.getOwner().getId());
		if (cateid == 0)
		{
			cateid = bbsDao.addBBSCate(getTypeName(type), account.getOwner().getId());
		}
		List<BBSForum> list = bbsDao.findListByCid(cateid,account.getId());
		if (list.size() == 0)
		{
			// 创建板块管理员
			BBSUser user = new BBSUser();
			user.setOwner(account.getOwner().getId());
			user.setNickname(getTypeName(type) + "管理员");
			forumer = bbsUserDao.saveBBSUser(user, null);
			forumid = bbsDao.saveBBSForum(cateid, getTypeName(type), forumer, getTypeName(type) + "社区");
			
			
			
			BBSAccount bbsa=new BBSAccount();
			bbsa.setOwner(account.getOwner().getId());
			bbsa.setAccid(account.getId());
			bbsa.setForumid(forumid);
			bbsDao.saveBBsAccount(bbsa);
		}
		else if (list.size() == 1)
		{
			forumid = list.get(0).getId();
		}
		else
		{
			return 2;
		}
		if (forumid > 0)
		{
			BBSForum forum = bbsDao.findForumById(forumid);
			long topicid = bbsTopicDao.saveBBSTopic(forum.getId(), forum.getForumer(), entityName, entityId, getTypeCode(type));
			if (topicid > 0)
			{
				updateEntity(topicid,entityId,getTypeCode(type));
				return 1;
			}
		}
		return 0;
	}

	@Override
	public int saveBathTopic(String bathPid, String entityType,long formid, Account account)
	{
		int rs=0;
		String[] pid = bathPid.split(",");
		for (String id : pid)
		{
			if (StringUtils.isNotEmpty(id))
			{
				long contentId = Long.parseLong(id);
				String entityName = null;
				if ("T".equals(entityType))
				{
					ContentProduct p = contentProductDao.findProductById(contentId);
					entityName = p == null ? "" : p.getName();
				}
				else if ("P".equals(entityType))
				{
					ContentPicture p = contentPictureDao.findPictureById(contentId, account.getOwner().getId());
					entityName = p == null ? "" : p.getTitle();
				}
				else if ("V".equals(entityType))
				{
					ContentVideo p = contentVideoDao.findVideoById(contentId, account.getOwner().getId());
					entityName = p == null ? "" : p.getTitle();
				}
				else if ("N".equals(entityType))
				{
					ContentNew p = contentNewDao.findNewsById(contentId);
					entityName = p == null ? "" : p.getTitle();
				}
				int result=0;
				if(formid==0){
					result=this.saveTopicForContent(contentId, entityType, entityName, account);	
				}else{
					result=this.saveTopicForContent(contentId, entityType, entityName,formid, account);
				}
				if(result>0){
					rs++;
				}
			}
		}
		return rs;
	}

	@Override
	public int saveTopicForContent(long entityId, String type, String entityName, long forumid, Account account)
	{
		int exist = bbsTopicDao.findTopicExist(entityId, getTypeCode(type));
		if (exist > 0)
		{
			return -1;
		}
		BBSForum forum = bbsDao.findForumById(forumid);
		long topicid = bbsTopicDao.saveBBSTopic(forum.getId(), forum.getForumer(), entityName, entityId, getTypeCode(type));
		if (topicid > 0)
		{
			updateEntity(topicid,entityId,getTypeCode(type));
			return 1;
		}
		return 0;
	}

	private void updateEntity(long topicid,long entityid, int typeCode) {
		// 0-图文;1-产品;2-新闻;3-视频
		switch (typeCode) {
		case 0:contentPictureDao.updatePicturePost(entityid,topicid);break;
		case 1:contentProductDao.updateProductPost(entityid,topicid);break;
		case 2:contentNewDao.updateNewsPost(entityid,topicid);break;
		case 3:contentVideoDao.updateVideoPost(entityid,topicid);break;
		}
	}

	@Override
	public List<BBSContent> findData(List<String> pids, String entityType)
	{
		List<BBSContent> list = new ArrayList<BBSContent>();
		if (pids != null && pids.size() > 0)
		{
			for (String pid : pids)
			{
				BBSContent b = bbsDao.findData(pid, getTypeCode(entityType));
				if (b != null)
				{
					b.setType(entityType);
					list.add(b);
				}
			}
		}
		return list;
	}

	private String getTypeName(String type)
	{
		if ("T".equals(type))
		{
			return "产品";
		}
		else if ("P".equals(type))
		{
			return "图片";
		}
		else if ("V".equals(type))
		{
			return "视频";
		}
		else if ("N".equals(type))
		{
			return "新闻";
		}
		else
		{
			return "";
		}
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
	public List<BBSAccount> findBBSAccount(long owner)
	{
		return bbsDao.findBBSAccount(owner);
	}

	@Override
	public List<EsForum> findBbsForumByOwner(long owner)
	{
		return bbsDao.findBbsForumByOwner(owner);
	}

	@Override
	public int updateBbsa(BBSAccount bbsa)
	{
		if (bbsa.getControl() > 0)
		{
			return bbsDao.saveBBsAccount(bbsa);
		}
		if (bbsa.getControl() < 0)
		{
			return bbsDao.deleteBBsAccount(bbsa);
		}
		return 0;
	}

	public void setContentProductDao(IContentProductDao contentProductDao)
	{
		this.contentProductDao = contentProductDao;
	}

	public void setContentPictureDao(IContentPictureDao contentPictureDao)
	{
		this.contentPictureDao = contentPictureDao;
	}

	public void setContentNewDao(IContentNewDao contentNewDao)
	{
		this.contentNewDao = contentNewDao;
	}

	public void setContentVideoDao(IContentVideoDao contentVideoDao)
	{
		this.contentVideoDao = contentVideoDao;
	}
}
