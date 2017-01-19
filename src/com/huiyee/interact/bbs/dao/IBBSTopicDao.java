package com.huiyee.interact.bbs.dao;

import java.util.List;

import com.huiyee.interact.bbs.model.BBSForum;
import com.huiyee.interact.bbs.model.BBSTopic;

public interface IBBSTopicDao {

	public List<BBSTopic> findTopicsByForumid(BBSForum forum, int start, int rows);

	public BBSTopic findTopicById(long topicid);

	public BBSTopic findTopicByEntity(long entityid, long entype);

	public int addTopicField(long topicid, String field);

	public long addTopic(long forumid, BBSTopic topic);

	public int addTopicText(long topicid, BBSTopic topic);

	public int findTopicExist(long entityId, int typeCode);

	public long saveBBSTopic(long id, long forumer, String entityName, long entityId, int typeCode);
}
