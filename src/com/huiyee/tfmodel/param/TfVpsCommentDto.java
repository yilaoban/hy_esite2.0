package com.huiyee.tfmodel.param;

import java.util.Date;

public class TfVpsCommentDto implements java.io.Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long wbuid;
	private Date sendtime;
	private String token;
	private String wbid;
	private String cid;
	private String content;
	private String typekey;
	public String getWbid()
	{
		return wbid;
	}
	public void setWbid(String wbid)
	{
		this.wbid = wbid;
	}
	public String getContent()
	{
		return content;
	}
	public void setContent(String content)
	{
		this.content = content;
	}
	public String getTypekey()
	{
		return typekey;
	}
	public void setTypekey(String typekey)
	{
		this.typekey = typekey;
	}
	public long getWbuid()
	{
		return wbuid;
	}
	public void setWbuid(long wbuid)
	{
		this.wbuid = wbuid;
	}
	public Date getSendtime()
	{
		return sendtime;
	}
	public void setSendtime(Date sendtime)
	{
		this.sendtime = sendtime;
	}
	public String getToken()
	{
		return token;
	}
	public void setToken(String token)
	{
		this.token = token;
	}
	public String getCid()
	{
		return cid;
	}
	public void setCid(String cid)
	{
		this.cid = cid;
	}
}
