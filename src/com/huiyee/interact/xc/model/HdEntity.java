package com.huiyee.interact.xc.model;

public class HdEntity
{
	private long id;
	private long entityid;
	private int featureid;
	private String title;
	private String votetype;

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public int getFeatureid()
	{
		return featureid;
	}

	public void setFeatureid(int featureid)
	{
		this.featureid = featureid;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public long getEntityid()
	{
		return entityid;
	}

	public void setEntityid(long entityid)
	{
		this.entityid = entityid;
	}

	public String getVotetype()
	{
		return votetype;
	}

	public void setVotetype(String votetype)
	{
		this.votetype = votetype;
	}
}
