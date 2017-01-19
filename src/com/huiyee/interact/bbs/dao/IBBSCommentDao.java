package com.huiyee.interact.bbs.dao;

import java.util.List;

import com.huiyee.interact.bbs.model.BBSComment;

public interface IBBSCommentDao {

	public List<BBSComment> findCommentsByTopicid(long topicid,String commentCheck, int start, int rows);

	public long addComment(long topicid, BBSComment comment);

	public int addCommentText(long commentid, BBSComment comment);

	public int addCommentField(long commentid, String field);

}
