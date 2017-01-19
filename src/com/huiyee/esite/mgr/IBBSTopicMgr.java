package com.huiyee.esite.mgr;

import com.huiyee.esite.dto.IDto;
import com.huiyee.interact.bbs.model.BBSTopic;
import com.huiyee.interact.bbs.model.BBSTopicText;

public interface IBBSTopicMgr
{
	public IDto findTopicListByForumid(long forumid,int pageId,long owner);
	
	public IDto findTopicListSort(long forumid,int pageId,int index,long owner);
	
	public long saveTopic(BBSTopic topic,BBSTopicText topicText,long owner);
	
	public int updateBBSTopic(BBSTopic topic,BBSTopicText topicText);
	
	public long updateBBSTopicStatus(long id,String checked);//ÐÞ¸ÄÉóºË×´Ì¬
	
	public int updateBBSTopicStatus(long topicid,long forumid);
	
	public IDto findTopicbytopicid(long topicid,int pageId,long owner,long forumid);
	
	public IDto findTopicbytopicid(long topicid,long owner);
	
	public long saveReplyInfo(BBSTopic topic,String ip,long owner);
	
	public int updateBBSTopicTop(long topicid,int top,long owner,long forumer);
	
	public int updateBBSTopicUp(long topicid,long owner,long forumer);
	
	public int updateBBSTopicDown(long topicid,long owner,long forumer);
	
	public int delReplyTopic(long postid,long topicid);
	
	public int updateBBSPostChecked(long postid,String checked);
	
}
