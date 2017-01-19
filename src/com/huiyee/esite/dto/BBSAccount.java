package com.huiyee.esite.dto;

public class BBSAccount
{
	private long id;
	private long owner;
	private long accid;
	private long forumid;
	private String forumname;
	private String accname;
	private int control;// 1:ÐÂÔö -1:É¾³ý

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public long getOwner()
	{
		return owner;
	}

	public void setOwner(long owner)
	{
		this.owner = owner;
	}

	public long getAccid()
	{
		return accid;
	}

	public void setAccid(long accid)
	{
		this.accid = accid;
	}

	public long getForumid()
	{
		return forumid;
	}

	public void setForumid(long forumid)
	{
		this.forumid = forumid;
	}

	public String getForumname()
	{
		return forumname;
	}

	public void setForumname(String forumname)
	{
		this.forumname = forumname;
	}

	public String getAccname()
	{
		return accname;
	}

	public void setAccname(String accname)
	{
		this.accname = accname;
	}

	public int getControl()
	{
		return control;
	}

	public void setControl(int control)
	{
		this.control = control;
	}
}
