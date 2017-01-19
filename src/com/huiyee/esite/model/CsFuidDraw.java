package com.huiyee.esite.model;

import java.io.Serializable;

public class CsFuidDraw implements Serializable{

	private static final long serialVersionUID = 8958223021407966303L;
	private long id;
	private long csid;
	private long fuid;
	private int utype;
	private String jcontent;
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
	public String getJcontent()
	{
		return jcontent;
	}
	public void setJcontent(String jcontent)
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
	
	
}
