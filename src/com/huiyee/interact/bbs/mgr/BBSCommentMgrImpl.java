package com.huiyee.interact.bbs.mgr;

import java.util.List;

import com.huiyee.esite.mgr.imp.AbstractMgr;
import com.huiyee.interact.bbs.dao.IBBSCommentDao;
import com.huiyee.interact.bbs.dao.IBBSForumDao;
import com.huiyee.interact.bbs.model.BBSComment;
import com.huiyee.interact.bbs.model.BBSForum;

public class BBSCommentMgrImpl extends AbstractMgr implements IBBSCommentMgr {
	private IBBSCommentDao bbsCommentDao;
	private IBBSForumDao bbsForumDao;
	
	public void setBbsForumDao(IBBSForumDao bbsForumDao)
	{
		this.bbsForumDao = bbsForumDao;
	}

	public void setBbsCommentDao(IBBSCommentDao bbsCommentDao) {
		this.bbsCommentDao = bbsCommentDao;
	}

	@Override
	public List<BBSComment> findCommentsByTopicid(long topicid,String commentCheck, int start, int rows) {
		return bbsCommentDao.findCommentsByTopicid(topicid,commentCheck, start, rows);
	}

	@Override
	public int addComment(long topicid, BBSComment comment,long forumid,long hyuid,long owner) {
		BBSForum bbsForum = bbsForumDao.findForumById(forumid);
		if(bbsForum != null){
			String commentCheck = bbsForum.getCommentCheck();
			if("N".equals(commentCheck)){
				comment.setChecked("CMP");
			}else{
				comment.setChecked("NOR");
			}
		}
		this.updateHyUserBalance(hyuid, "CMT", owner, forumid);
		long commentid = bbsCommentDao.addComment(topicid, comment);
		return bbsCommentDao.addCommentText(commentid, comment);
	}

	@Override
	public int addCommentField(long commentid, String field) {
		return bbsCommentDao.addCommentField(commentid, field);
	}

}
