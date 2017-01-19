package com.huiyee.esite.dto;

import java.util.List;

import com.huiyee.esite.model.BBSCategory;
import com.huiyee.esite.model.BBSForum;
import com.huiyee.esite.model.BBSJfLevel;
import com.huiyee.esite.model.BBSJfRule;
import com.huiyee.esite.model.Page;
import com.huiyee.interact.bbs.model.BBSTopic;
import com.huiyee.interact.bbs.model.BBSUser;

public class CommunityDto implements IDto
{
	private Pager pager;
	private BBSCategory bbsCatergory;
	private List<BBSCategory> bbsCatergoryList;
	private List<BBSForum> bbsForumList;
	private long cateid;
	private BBSForum bbsForum;
	
	private BBSTopic topic;
	private List<BBSTopic> bbsTopicList;

	private List<BBSJfRule> bbsJfRuleList;
	private BBSJfRule jfRule;
	
	private List<BBSJfLevel> bbsJfLevelList;
	private BBSJfLevel jfLevel;
	
	private List<BBSUser> bbsUserList;
	private List<Page> pageList;
	private String commentCheck;
	

	
	public String getCommentCheck()
	{
		return commentCheck;
	}

	
	public void setCommentCheck(String commentCheck)
	{
		this.commentCheck = commentCheck;
	}

	public BBSJfRule getJfRule()
	{
		return jfRule;
	}

	public void setJfRule(BBSJfRule jfRule)
	{
		this.jfRule = jfRule;
	}

	public List<BBSTopic> getBbsTopicList()
	{
		return bbsTopicList;
	}

	public void setBbsTopicList(List<BBSTopic> bbsTopicList)
	{
		this.bbsTopicList = bbsTopicList;
	}

	public Pager getPager()
	{
		return pager;
	}

	public void setPager(Pager pager)
	{
		this.pager = pager;
	}

	public BBSCategory getBbsCatergory()
	{
		return bbsCatergory;
	}

	public void setBbsCatergory(BBSCategory bbsCatergory)
	{
		this.bbsCatergory = bbsCatergory;
	}

	public List<BBSCategory> getBbsCatergoryList()
	{
		return bbsCatergoryList;
	}

	public void setBbsCatergoryList(List<BBSCategory> bbsCatergoryList)
	{
		this.bbsCatergoryList = bbsCatergoryList;
	}

	public List<BBSForum> getBbsForumList()
	{
		return bbsForumList;
	}

	public void setBbsForumList(List<BBSForum> bbsForumList)
	{
		this.bbsForumList = bbsForumList;
	}

	public long getCateid()
	{
		return cateid;
	}

	public void setCateid(long cateid)
	{
		this.cateid = cateid;
	}

	public BBSForum getBbsForum()
	{
		return bbsForum;
	}

	public void setBbsForum(BBSForum bbsForum)
	{
		this.bbsForum = bbsForum;
	}

	public BBSTopic getTopic()
	{
		return topic;
	}

	public void setTopic(BBSTopic topic)
	{
		this.topic = topic;
	}

	public List<BBSJfRule> getBbsJfRuleList()
	{
		return bbsJfRuleList;
	}

	public void setBbsJfRuleList(List<BBSJfRule> bbsJfRuleList)
	{
		this.bbsJfRuleList = bbsJfRuleList;
	}

	public List<BBSJfLevel> getBbsJfLevelList()
	{
		return bbsJfLevelList;
	}

	public void setBbsJfLevelList(List<BBSJfLevel> bbsJfLevelList)
	{
		this.bbsJfLevelList = bbsJfLevelList;
	}

	public BBSJfLevel getJfLevel()
	{
		return jfLevel;
	}

	public void setJfLevel(BBSJfLevel jfLevel)
	{
		this.jfLevel = jfLevel;
	}

	public List<BBSUser> getBbsUserList()
	{
		return bbsUserList;
	}

	public void setBbsUserList(List<BBSUser> bbsUserList)
	{
		this.bbsUserList = bbsUserList;
	}

	public List<Page> getPageList() {
		return pageList;
	}

	public void setPageList(List<Page> pageList) {
		this.pageList = pageList;
	}
	
}
