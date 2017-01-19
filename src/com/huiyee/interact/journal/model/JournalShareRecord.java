package com.huiyee.interact.journal.model;

import java.io.Serializable;
import java.util.Date;

public class JournalShareRecord implements Serializable{

	private long id;
	private long wbid;
	private long pageid;
	private long contentid;
	private long wbuid;
	private String sharecontent;  //∑÷œÌƒ⁄»›
	private String sharepic;
	private Date createtime;
	private String ip;
	private String terminal;
	private String source;
	private String nickname;
	
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
	public long getContentid()
	{
		return contentid;
	}
	public void setContentid(long contentid)
	{
		this.contentid = contentid;
	}
	public long getWbuid()
	{
		return wbuid;
	}
	public void setWbuid(long wbuid)
	{
		this.wbuid = wbuid;
	}
	public String getSharecontent()
	{
		return sharecontent;
	}
	public void setSharecontent(String sharecontent)
	{
		this.sharecontent = sharecontent;
	}
	public String getSharepic()
	{
		return sharepic;
	}
	public void setSharepic(String sharepic)
	{
		this.sharepic = sharepic;
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
	public long getWbid()
	{
		return wbid;
	}
	public void setWbid(long wbid)
	{
		this.wbid = wbid;
	}
	
	
	
}
