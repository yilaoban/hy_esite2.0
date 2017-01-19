package com.huiyee.interact.bbs.model;

import java.io.Serializable;

public class BBSPostType implements Serializable
{
	private static final long serialVersionUID = 922768045389010890L;
	private int id;
	private String type_name;
	private int priority;
	private int forum_id;
	private int parent_id;
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public String getType_name()
	{
		return type_name;
	}
	public void setType_name(String type_name)
	{
		this.type_name = type_name;
	}
	public int getPriority()
	{
		return priority;
	}
	public void setPriority(int priority)
	{
		this.priority = priority;
	}
	public int getForum_id()
	{
		return forum_id;
	}
	public void setForum_id(int forum_id)
	{
		this.forum_id = forum_id;
	}
	public int getParent_id()
	{
		return parent_id;
	}
	public void setParent_id(int parent_id)
	{
		this.parent_id = parent_id;
	}
	
	
}
