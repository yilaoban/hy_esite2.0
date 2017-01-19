package com.huiyee.interact.vote.model;

import java.io.Serializable;
import java.util.Date;

public class VoteRecord implements Serializable{
	private long id;
	private Date createtime;
	private String isshare;
	private String content;
	private String nickname;
	private long wbuid;
	private int type;
	private String ip;
	private String wxnickname;
	private String username;
	
    public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getWxnickname() {
		return wxnickname;
	}
	public void setWxnickname(String wxnickname) {
		this.wxnickname = wxnickname;
	}
	public Date getCreatetime() {
        return createtime;
    }
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
    public String getIsshare() {
        return isshare;
    }
    public void setIsshare(String isshare) {
        this.isshare = isshare;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getNickname() {
        return nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public long getWbuid() {
        return wbuid;
    }
    public void setWbuid(long wbuid) {
        this.wbuid = wbuid;
    }
	public long getId()
	{
		return id;
	}
	public void setId(long id)
	{
		this.id = id;
	}
	public int getType()
	{
		return type;
	}
	public void setType(int type)
	{
		this.type = type;
	}
	public String getIp()
	{
		return ip;
	}
	public void setIp(String ip)
	{
		this.ip = ip;
	}
}
