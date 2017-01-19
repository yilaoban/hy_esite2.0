package com.huiyee.interact.bbs.mgr;

import java.util.List;

import com.huiyee.interact.bbs.model.BBSForum;

public interface IBBSForumMgr {

	public List<BBSForum> findForumsByCateid(long cateid);

	public BBSForum findForumById(long forumid);

}
