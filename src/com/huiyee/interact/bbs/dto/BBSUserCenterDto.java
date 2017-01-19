package com.huiyee.interact.bbs.dto;

import java.util.List;

import com.huiyee.interact.bbs.model.BBSTopic;
import com.huiyee.interact.bbs.model.BBSUser;

public class BBSUserCenterDto implements IDto
{
	private BBSUser bbsUser;
	private List<BBSTopic> topicList;
	
	public BBSUser getBbsUser()
	{
		return bbsUser;
	}

	public void setBbsUser(BBSUser bbsUser)
	{
		this.bbsUser = bbsUser;
	}

	public List<BBSTopic> getTopicList()
	{
		return topicList;
	}

	public void setTopicList(List<BBSTopic> topicList)
	{
		this.topicList = topicList;
	}
	
	
	
}
