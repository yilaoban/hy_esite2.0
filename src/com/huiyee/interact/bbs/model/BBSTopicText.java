package com.huiyee.interact.bbs.model;

import java.io.Serializable;

public class BBSTopicText implements Serializable
{
	private static final long serialVersionUID = -3748034398833130213L;
	private long id;
	private long topicid;
	private String content;
	private String img;
	public long getId()
	{
		return id;
	}
	public void setId(long id)
	{
		this.id = id;
	}
	public long getTopicid()
	{
		return topicid;
	}
	public void setTopicid(long topicid)
	{
		this.topicid = topicid;
	}
	public String getContent()
	{
		return content;
	}
	public void setContent(String content)
	{
		this.content = content;
	}
	public String getImg()
	{
		return img;
	}
	public void setImg(String img)
	{
		this.img = img;
	}
	
}
