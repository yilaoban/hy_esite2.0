package com.huiyee.interact.bbs.mgr;

import java.util.List;

import com.huiyee.esite.mgr.imp.AbstractMgr;
import com.huiyee.interact.bbs.dao.IBBSForumDao;
import com.huiyee.interact.bbs.dao.IBBSTopicDao;
import com.huiyee.interact.bbs.model.BBSForum;
import com.huiyee.interact.bbs.model.BBSTopic;

public class BBSTopicMgrImpl extends AbstractMgr implements IBBSTopicMgr {
	private IBBSTopicDao bbsTopicDao;
	private IBBSForumDao bbsForumDao;
	
	public IBBSForumDao getBbsForumDao()
	{
		return bbsForumDao;
	}

	
	public void setBbsForumDao(IBBSForumDao bbsForumDao)
	{
		this.bbsForumDao = bbsForumDao;
	}

	public void setBbsTopicDao(IBBSTopicDao bbsTopicDao) {
		this.bbsTopicDao = bbsTopicDao;
	}

	@Override
	public List<BBSTopic> findTopicsByForumid(BBSForum forum, int start, int rows) {
		return bbsTopicDao.findTopicsByForumid(forum, start, rows);
	}

	@Override
	public BBSTopic findTopicById(long topicid) {
		return bbsTopicDao.findTopicById(topicid);
	}

	@Override
	public BBSTopic findTopicByEntity(long entityid, long entype) {
		return bbsTopicDao.findTopicByEntity(entityid, entype);
	}

	@Override
	public int addTopicField(long topicid, String field) {
		return bbsTopicDao.addTopicField(topicid, field);
	}

	@Override
	public long addTopic(long forumid, BBSTopic topic,long hyuid,long owner) {
		BBSForum bbsForum = bbsForumDao.findForumById(forumid);
		if(bbsForum != null){
			String topicCheck = bbsForum.getTopicCheck();
			if("N".equals(topicCheck)){
				topic.setChecked("CMP");
			}else{
				topic.setChecked("NOR");
			}
		}
		long topicid = bbsTopicDao.addTopic(forumid, topic);
		bbsTopicDao.addTopicText(topicid, topic);
		this.updateHyUserBalance(hyuid, "TPC", owner, forumid);
		return topicid;
	}

}
