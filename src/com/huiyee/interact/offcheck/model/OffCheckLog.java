
package com.huiyee.interact.offcheck.model;

import java.io.Serializable;
import java.util.Date;

import com.huiyee.esite.model.WxUser;

public class OffCheckLog implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1204698003550737335L;
	private long id;
	private long wxuid;
	private String ip;
	private Date createtime;
	private long sid;
	private WxUser wxUser;
	private OffCheckSource source;
	private String area;
	
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

	public String getIp()
	{
		return ip;
	}

	public void setIp(String ip)
	{
		this.ip = ip;
	}

	public Date getCreatetime()
	{
		return createtime;
	}

	public void setCreatetime(Date createtime)
	{
		this.createtime = createtime;
	}

	public long getSid()
	{
		return sid;
	}

	public void setSid(long sid)
	{
		this.sid = sid;
	}

	public WxUser getWxUser()
	{
		return wxUser;
	}

	public void setWxUser(WxUser wxUser)
	{
		this.wxUser = wxUser;
	}

	public OffCheckSource getSource()
	{
		return source;
	}

	public void setSource(OffCheckSource source)
	{
		this.source = source;
	}

	
	public String getArea()
	{
		return area;
	}

	
	public void setArea(String area)
	{
		this.area = area;
	}

}
