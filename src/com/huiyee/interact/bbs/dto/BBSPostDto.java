package com.huiyee.interact.bbs.dto;

import java.util.List;

import com.huiyee.interact.bbs.model.BBSMessage;
import com.huiyee.interact.bbs.model.BBSTopic;

public class BBSPostDto implements IDto
{
	private BBSTopic topic;
	private List<BBSTopic> topicList;
	private List<BBSMessage> messagList;
	
	public BBSTopic getTopic()
	{
		return topic;
	}

	public void setTopic(BBSTopic topic)
	{
		this.topic = topic;
	}

	public List<BBSTopic> getTopicList()
	{
		return topicList;
	}

	public void setTopicList(List<BBSTopic> topicList)
	{
		this.topicList = topicList;
	}

	public List<BBSMessage> getMessagList()
	{
		return messagList;
	}

	public void setMessagList(List<BBSMessage> messagList)
	{
		this.messagList = messagList;
	}

	
}
