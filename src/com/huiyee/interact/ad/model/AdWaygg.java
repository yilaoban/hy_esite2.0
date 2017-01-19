
package com.huiyee.interact.ad.model;

public class AdWaygg
{

	private long ggid;
	private long wayid;

	public long getGgid()
	{
		return ggid;
	}

	public void setGgid(long ggid)
	{
		this.ggid = ggid;
	}

	public long getWayid()
	{
		return wayid;
	}

	public void setWayid(long wayid)
	{
		this.wayid = wayid;
	}

	private Adgg gg;
	private AdWay way;

	public Adgg getGg()
	{
		return gg;
	}

	public void setGg(Adgg gg)
	{
		this.gg = gg;
	}

	public AdWay getWay()
	{
		return way;
	}

	public void setWay(AdWay way)
	{
		this.way = way;
	}
}
