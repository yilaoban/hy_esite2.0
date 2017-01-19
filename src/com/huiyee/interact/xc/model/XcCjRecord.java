package com.huiyee.interact.xc.model;

import java.io.Serializable;
import java.util.Date;

public class XcCjRecord implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3161237164470163359L;
	private long id;
	private long xcid;
	private int startnum;
	private long uid;
	private int utype;
	private int joinnum;
	private String top;
	private Date createtime;
	private String ip;
	private String terminal;
	private String source;
	private String nickname;
	private long pageid;
	public long getId()
	{
		return id;
	}
	public void setId(long id)
	{
		this.id = id;
	}
	public long getXcid()
	{
		return xcid;
	}
	public void setXcid(long xcid)
	{
		this.xcid = xcid;
	}
	public int getStartnum()
	{
		return startnum;
	}
	public void setStartnum(int startnum)
	{
		this.startnum = startnum;
	}
	public long getUid()
	{
		return uid;
	}
	public void setUid(long uid)
	{
		this.uid = uid;
	}
	public int getUtype()
	{
		return utype;
	}
	public void setUtype(int utype)
	{
		this.utype = utype;
	}
	public int getJoinnum()
	{
		return joinnum;
	}
	public void setJoinnum(int joinnum)
	{
		this.joinnum = joinnum;
	}
	public String getTop()
	{
		return top;
	}
	public void setTop(String top)
	{
		this.top = top;
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
	public String getNickname()
	{
		return nickname;
	}
	public void setNickname(String nickname)
	{
		this.nickname = nickname;
	}
	public long getPageid()
	{
		return pageid;
	}
	public void setPageid(long pageid)
	{
		this.pageid = pageid;
	}
	
}
