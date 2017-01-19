package com.huiyee.esite.dao;

import java.util.List;

import com.huiyee.esite.model.BBSForum;
import com.huiyee.esite.model.BBSJfRule;
import com.huiyee.interact.bbs.model.BBSTopic;
import com.huiyee.interact.bbs.model.BBSTopicText;

public interface IBBSTopicDao
{
	public int findTopicCountByForumid(long forumid,long owner);
	
	public BBSForum findBBSForumById(long forumid);
	
	public int findTopicCountByForumid(long forumid,int index,long owner);
	
	List<BBSTopic> findTopicListByForumid(long forumid,long owner,int start,int size);
	
	List<BBSTopic> findTopicListByForumid(long forumid,int index,long owner,int start,int size);
	
	public int updateBBSTopicById(BBSTopic topic);
	
	public int updateBBSTopicContentById(long topicid,BBSTopicText topicText);
	
	public long saveBBSTopic(BBSTopic topic);
	
	public long saveBBSTopicText(long topicid,BBSTopicText topicText);
	
	public int updateBBSUsrTopicnum(long userid);
	
	public int updateForumTopicnum(long forumid);
	
	public int updateForumpostnum(long forumid);
	
	public long updateBBSTopicStatus(long id,String status);
	
	public int updateBBSTopicStatus(long topicid);
	
	public BBSTopic findBBSTopic(long topicid);
	
	public BBSTopic findTopicbytopicidAndOwner(long topicid,long owner,int entype);
	
	public BBSTopic findTopicbytopicid(long topicid,long owner);
	
	public int findBBSPostCountbyTopicid(long topicid,long owner);
	
	public List<BBSTopic> findBBSPostbyTopicid(long topicid,long owner,int start,int size);
	
	public long saveBBSPost(BBSTopic topic, String ip);
	
	public long saveBBSPostext(long postid,BBSTopic topic);
	
	public int updateBBSTopic(BBSTopic topic,long postid);
	
	public int updateBBSUserReplynum(long userid);
	
	public int updateBBSTopicTop(long topicid, int top);
	
	public int updateBBSTopicUp(long topicid);
	
	public int updateBBSTopicDown(long topicid);
	
	public BBSJfRule findBBSUserJfRuleByAction(long owner,int action);
	
	public int updateBBUserJf(int jifen,long forumer);
	
	public int delReplyTopic(long postid);
	
	public int updateBBSTopicReplyCount(long topicid);
	
	public BBSForum findForumById(long forumid);
	
	public int updateBBSPostChecked(long postid, String checked);
	
	public int updateBBSForumTopicnum(long forumid);
}
