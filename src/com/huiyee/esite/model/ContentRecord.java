package com.huiyee.esite.model;

import java.io.Serializable;
import java.util.Date;


public class ContentRecord implements Serializable
{
	private static final long serialVersionUID = -2087785939153551021L;
	private long id;
	private String title;
	private String hydesc;
	private String longdesc;
	private long enid;
	private String type;
	private long uid;
	private int utype;
	private Date createtime;
	private String ip;
	private String terminal;
	private String source;
	private Date updatetime;
	private String status;
	private String remark;
	
	private String nickname;
	
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
	
	public String getTitle()
	{
		return title;
	}
	
	public void setTitle(String title)
	{
		this.title = title;
	}
	
	public String getHydesc()
	{
		return hydesc;
	}
	
	public void setHydesc(String hydesc)
	{
		this.hydesc = hydesc;
	}
	
	public String getLongdesc()
	{
		return longdesc;
	}
	
	public void setLongdesc(String longdesc)
	{
		this.longdesc = longdesc;
	}
	
	public long getEnid()
	{
		return enid;
	}
	
	public void setEnid(long enid)
	{
		this.enid = enid;
	}
	
	public String getType()
	{
		return type;
	}
	
	public void setType(String type)
	{
		this.type = type;
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


	
	public Date getUpdatetime()
	{
		return updatetime;
	}


	
	public void setUpdatetime(Date updatetime)
	{
		this.updatetime = updatetime;
	}


	
	public String getStatus()
	{
		return status;
	}


	
	public void setStatus(String status)
	{
		this.status = status;
	}


	
	public String getRemark()
	{
		return remark;
	}


	
	public void setRemark(String remark)
	{
		this.remark = remark;
	}
	
	
}
