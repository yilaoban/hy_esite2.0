package com.huiyee.interact.spread.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

public class SpreadRecord implements Serializable{
	
	private static final long serialVersionUID = -6789753548035456542L;
	private long id;
	private long pageid;
	private long spreadid;
	private long wbuid;
	private String nickname;
	private String content;
	private String pic;
	private String fartherwbid;
	private Timestamp createtime;
	private String ip;
	private String terminal;
	private String source;
	private String processstatus;
	
	
	public long getId()
	{
		return id;
	}
	public void setId(long id)
	{
		this.id = id;
	}
	public long getPageid()
	{
		return pageid;
	}
	public void setPageid(long pageid)
	{
		this.pageid = pageid;
	}
	public long getSpreadid()
	{
		return spreadid;
	}
	public void setSpreadid(long spreadid)
	{
		this.spreadid = spreadid;
	}
	public long getWbuid()
	{
		return wbuid;
	}
	public void setWbuid(long wbuid)
	{
		this.wbuid = wbuid;
	}
	public String getNickname()
	{
		return nickname;
	}
	public void setNickname(String nickname)
	{
		this.nickname = nickname;
	}
	public String getContent()
	{
		return content;
	}
	public void setContent(String content)
	{
		this.content = content;
	}
	public String getPic()
	{
		return pic;
	}
	public void setPic(String pic)
	{
		this.pic = pic;
	}
	public String getFartherwbid()
	{
		return fartherwbid;
	}
	public void setFartherwbid(String fartherwbid)
	{
		this.fartherwbid = fartherwbid;
	}

	public Timestamp getCreatetime()
	{
		return createtime;
	}
	public void setCreatetime(Timestamp createtime)
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
	public String getProcessstatus()
	{
		return processstatus;
	}
	public void setProcessstatus(String processstatus)
	{
		this.processstatus = processstatus;
	}
	

	

}
