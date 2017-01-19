package com.huiyee.interact.xc.model;

import java.io.File;
import java.io.Serializable;
import java.util.Date;

import com.huiyee.esite.util.DateUtil;

public class XcCommentRecord implements Serializable
{

	private long id;
	private long pageid;
	private long xcid;
	private long uid;
	private int utype;
	private String content;
	private String img;
	private String status;
	private Date createtime;
	private String createtimeStr;
	private String ip;
	private String terminal;
	private String source;
	
	private String nickname;
	private String headimgurl;
	
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
	public String getContent()
	{
		return content;
	}
	public void setContent(String content)
	{
		this.content = content;
	}
	
	public String getStatus()
	{
		return status;
	}
	public void setStatus(String status)
	{
		this.status = status;
	}
	public Date getCreatetime()
	{
		return createtime;
	}
	public void setCreatetime(Date createtime)
	{
		this.createtime = createtime;
		if(createtime != null){
			setCreatetimeStr(DateUtil.getDateTimeString(createtime));
		}
	}
	public String getImg()
	{
		return img;
	}
	public void setImg(String img)
	{
		this.img = img;
	}
	public String getHeadimgurl() {
		return headimgurl;
	}
	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}
	public String getCreatetimeStr()
	{
		return createtimeStr;
	}
	public void setCreatetimeStr(String createtimeStr)
	{
		this.createtimeStr = createtimeStr;
	}
	
}
