package com.huiyee.interact.bbs.mgr;

import java.util.List;

import com.huiyee.interact.bbs.model.BBSComment;

public interface IBBSCommentMgr {

	public List<BBSComment> findCommentsByTopicid(long topicid,String commentCheck, int start, int rows);

	public int addComment(long topicid, BBSComment comment,long forumid,long hyuid,long owner);

	public int addCommentField(long commentid, String field);

}
