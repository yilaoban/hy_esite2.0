package com.huiyee.interact.bbs.action;


import com.huiyee.esite.action.AbstractAction;
import com.huiyee.esite.mgr.IHyUserMgr;
import com.huiyee.interact.bbs.mgr.IBBSMgr;
import com.huiyee.interact.bbs.mgr.IBBSUserMgr;

public abstract class AbstractBBSAction extends AbstractAction
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	IBBSMgr bbsMgr;
	IBBSUserMgr bbsUserMgr;
	IHyUserMgr hyUserMgr;
	private long category;
	private long forumid;
	private long topicid;
	private long postid;
	private String bbserr;
	private long bbsowner;//用于验证安全
	
	public void setBbsMgr(IBBSMgr bbsMgr)
	{
		this.bbsMgr = bbsMgr;
	}

	public void setHyUserMgr(IHyUserMgr hyUserMgr)
	{
		this.hyUserMgr = hyUserMgr;
	}

	public long getCategory()
	{
		return category;
	}

	public void setCategory(long category)
	{
		this.category = category;
	}

	public long getForumid()
	{
		return forumid;
	}

	public void setForumid(long forumid)
	{
		this.forumid = forumid;
	}

	public long getTopicid()
	{
		return topicid;
	}

	public void setTopicid(long topicid)
	{
		this.topicid = topicid;
	}

	public long getPostid()
	{
		return postid;
	}

	public void setPostid(long postid)
	{
		this.postid = postid;
	}

	public String getBbserr()
	{
		return bbserr;
	}

	public void setBbserr(String bbserr)
	{
		this.bbserr = bbserr;
	}

	public long getBbsowner()
	{
		return hyUserMgr.getBbsowner(this.getForumid());
	}

	public void setBbsowner(long bbsowner)
	{
		this.bbsowner = bbsowner;
	}
	
	public void setBbsUserMgr(IBBSUserMgr bbsUserMgr)
	{
		this.bbsUserMgr = bbsUserMgr;
	}

}
