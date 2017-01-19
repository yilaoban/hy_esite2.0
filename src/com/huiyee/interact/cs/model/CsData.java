package com.huiyee.interact.cs.model;

import java.util.Date;

import com.huiyee.interact.cs.dto.JContent;

public class CsData
{
	private long id;
	private long csid;
	private long fuid;
	private String nickname;
	private int utype;
	private JContent jcontent;
	private String ip;
	private String terminal;
	private String source;
	private Date createtime;

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public long getCsid()
	{
		return csid;
	}

	public void setCsid(long csid)
	{
		this.csid = csid;
	}

	public long getFuid()
	{
		return fuid;
	}

	public void setFuid(long fuid)
	{
		this.fuid = fuid;
	}

	public int getUtype()
	{
		return utype;
	}

	public void setUtype(int utype)
	{
		this.utype = utype;
	}

	public JContent getJcontent()
	{
		return jcontent;
	}

	public void setJcontent(JContent jcontent)
	{
		this.jcontent = jcontent;
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

	public Date getCreatetime()
	{
		return createtime;
	}

	public void setCreatetime(Date createtime)
	{
		this.createtime = createtime;
	}

	public String getNickname()
	{
		return nickname;
	}

	public void setNickname(String nickname)
	{
		this.nickname = nickname;
	}
}
