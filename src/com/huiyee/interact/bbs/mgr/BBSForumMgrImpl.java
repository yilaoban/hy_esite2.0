package com.huiyee.interact.bbs.mgr;

import java.util.List;

import com.huiyee.interact.bbs.dao.IBBSForumDao;
import com.huiyee.interact.bbs.model.BBSForum;

public class BBSForumMgrImpl implements IBBSForumMgr {
	private IBBSForumDao bbsForumDao;

	public void setBbsForumDao(IBBSForumDao bbsForumDao) {
		this.bbsForumDao = bbsForumDao;
	}

	@Override
	public List<BBSForum> findForumsByCateid(long cateid) {
		return bbsForumDao.findForumsByCateid(cateid);
	}

	@Override
	public BBSForum findForumById(long forumid) {
		return bbsForumDao.findForumById(forumid);
	}

}
