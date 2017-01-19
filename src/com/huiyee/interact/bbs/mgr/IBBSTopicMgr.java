package com.huiyee.interact.bbs.mgr;

import java.util.List;

import com.huiyee.interact.bbs.model.BBSForum;
import com.huiyee.interact.bbs.model.BBSTopic;

public interface IBBSTopicMgr {

	public List<BBSTopic> findTopicsByForumid(BBSForum forum, int start, int rows);

	public BBSTopic findTopicById(long topicid);

	public BBSTopic findTopicByEntity(long entityid, long entype);

	public int addTopicField(long topicid, String field);

	public long addTopic(long forumid, BBSTopic topic,long hyuid,long owner);

}
