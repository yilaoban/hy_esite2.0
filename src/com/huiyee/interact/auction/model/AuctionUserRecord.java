package com.huiyee.interact.auction.model;

import java.io.Serializable;
import java.util.Date;

public class AuctionUserRecord implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -885623867557689494L;
	private long id;
	private long wbuid;
	private long pageid;
	private long auid;
	private int bidnum;
	private Date createtime;
	private String ip;
	private String terminal;
	private String source;

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public long getWbuid()
	{
		return wbuid;
	}

	public void setWbuid(long wbuid)
	{
		this.wbuid = wbuid;
	}

	public long getPageid()
	{
		return pageid;
	}

	public void setPageid(long pageid)
	{
		this.pageid = pageid;
	}

	public long getAuid()
	{
		return auid;
	}

	public void setAuid(long auid)
	{
		this.auid = auid;
	}

	public int getBidnum()
	{
		return bidnum;
	}

	public void setBidnum(int bidnum)
	{
		this.bidnum = bidnum;
	}

	public Date getCreatetime()
	{
		return createtime;
	}

	public void setCreatetime(Date createtime)
	{
		this.createtime = createtime;
	}

	public String getIp()
	{
		return ip;
	}

	public void setIp(String ip)
	{
		this.ip = ip;
	}

	public String getTerminal()
	{
		return terminal;
	}

	public void setTerminal(String terminal)
	{
		this.terminal = terminal;
	}

	public String getSource()
	{
		return source;
	}

	public void setSource(String source)
	{
		this.source = source;
	}

}
