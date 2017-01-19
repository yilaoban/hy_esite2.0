package com.huiyee.interact.research.model;

import java.io.Serializable;
import java.util.Date;

public class ResearchRecord implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6750609432880284902L;
	private long id;
	private long wbuid;
	private long searchid;
	private String nickname;
	private String isshare;
	private String content;
	private Date createtime;
	private long wxuid;
	private String ip;
	private String source;
	private String openid;
	private String username;
	private String wxnickname;
	
	public String getWxnickname() {
		return wxnickname;
	}
	public void setWxnickname(String wxnickname) {
		this.wxnickname = wxnickname;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getOpenid()
	{
		return openid;
	}
	public void setOpenid(String openid)
	{
		this.openid = openid;
	}
	public String getIp()
	{
		return ip;
	}
	public void setIp(String ip)
	{
		this.ip = ip;
	}
	public String getSource()
	{
		return source;
	}
	public void setSource(String source)
	{
		this.source = source;
	}
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
	public String getNickname()
	{
		return nickname;
	}
	public void setNickname(String nickname)
	{
		this.nickname = nickname;
	}
	public String getIsshare()
	{
		return isshare;
	}
	public void setIsshare(String isshare)
	{
		this.isshare = isshare;
	}
	public String getContent()
	{
		return content;
	}
	public void setContent(String content)
	{
		this.content = content;
	}
	public Date getCreatetime()
	{
		return createtime;
	}
	public void setCreatetime(Date createtime)
	{
		this.createtime = createtime;
	}
	public long getSearchid()
	{
		return searchid;
	}
	public void setSearchid(long searchid)
	{
		this.searchid = searchid;
	}
	public long getWxuid()
	{
		return wxuid;
	}
	public void setWxuid(long wxuid)
	{
		this.wxuid = wxuid;
	}
	
}
