package com.huiyee.interact.xc.model;

import java.io.Serializable;
import java.util.Date;

public class XcInviteRecord implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8566940135059017167L;
	private long id;
	private long pageid;
	private long xcid;
	private long uid;
	private int utype;
	private Date createtime;
	private String ip;
	private String terminal;
	private String source;
	private String nickname;
	private String checked;
	
	public String getNickname()
	{
		return nickname;
	}
	public void setNickname(String nickname)
	{
		this.nickname = nickname;
	}
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
	public long getXcid()
	{
		return xcid;
	}
	public void setXcid(long xcid)
	{
		this.xcid = xcid;
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
	
	public String getChecked()
	{
		return checked;
	}
	
	public void setChecked(String checked)
	{
		this.checked = checked;
	}
	
	
}
