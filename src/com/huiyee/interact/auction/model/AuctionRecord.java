package com.huiyee.interact.auction.model;

import java.util.Date;

public class AuctionRecord
{
	private long id;
	private long ownerwbuid;
	private long wbuid;
	private String nickName;
	private long pageid;
	private int bidnum;
	private Date createtime;
	private String ip;
	private String terminal;
	private String source;
	private int total;
	private int type;

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

	public String getNickName()
	{
		return nickName;
	}

	public void setNickName(String nickName)
	{
		this.nickName = nickName;
	}

	public int getTotal()
	{
		return total;
	}

	public void setTotal(int total)
	{
		this.total = total;
	}

	public int getType()
	{
		return type;
	}

	public void setType(int type)
	{
		this.type = type;
	}

	public long getOwnerwbuid()
	{
		return ownerwbuid;
	}

	public void setOwnerwbuid(long ownerwbuid)
	{
		this.ownerwbuid = ownerwbuid;
	}
}
