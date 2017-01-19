
package com.huiyee.interact.offcheck.model;

import java.io.Serializable;
import java.util.Date;

import com.huiyee.esite.model.WxUser;

public class OffCheckRecord implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2446348554459591755L;
	private long id;
	private long wxuid;
	private String lastip;
	private Date lastvisttime;
	private long sid;
	private OffCheckSource source;
	private int vistnum;
	private WxUser wxUser;
	private String lastarea;
	
	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public long getWxuid()
	{
		return wxuid;
	}

	public void setWxuid(long wxuid)
	{
		this.wxuid = wxuid;
	}

	public String getLastip()
	{
		return lastip;
	}

	public void setLastip(String lastip)
	{
		this.lastip = lastip;
	}

	public long getSid()
	{
		return sid;
	}

	public void setSid(long sid)
	{
		this.sid = sid;
	}

	public OffCheckSource getSource()
	{
		return source;
	}

	public void setSource(OffCheckSource source)
	{
		this.source = source;
	}

	public int getVistnum()
	{
		return vistnum;
	}

	public void setVistnum(int vistnum)
	{
		this.vistnum = vistnum;
	}

	public WxUser getWxUser()
	{
		return wxUser;
	}

	public void setWxUser(WxUser wxUser)
	{
		this.wxUser = wxUser;
	}

	public Date getLastvisttime()
	{
		return lastvisttime;
	}

	public void setLastvisttime(Date lastvisttime)
	{
		this.lastvisttime = lastvisttime;
	}

	
	public String getLastarea()
	{
		return lastarea;
	}

	
	public void setLastarea(String lastarea)
	{
		this.lastarea = lastarea;
	}
	
}
