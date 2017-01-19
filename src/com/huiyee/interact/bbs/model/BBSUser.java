package com.huiyee.interact.bbs.model;

import java.io.Serializable;
import java.util.Date;

public class BBSUser implements Serializable{

	private static final long serialVersionUID = 2750785126068872345L;
	private long id;
	private String email;
	private long owner;
	private String telphone;
	private long logintime;
	private String nickname;
	private Date createtime;
	private String createip;
	private int loginnum;
	private int PROHIBIT_POST;
	private Date PROHIBIT_TIME;
	private String img;
	private int topicnum;
	private int replynum;
	private int postday;
	private Date lastposttime;
	private long hyuserid;
	private int level_id=1;
	private long logid;//ÈÕÖ¾µÄid
	
	private String level_name="°×¶¡";
	private int jf;
	private int uptotal;
	private int downtotal;
	private int toptotal;
	private String isbalck;
	private String username;
	
	public String getUsername()
	{
		return username;
	}
	
	public void setUsername(String username)
	{
		this.username = username;
	}
	public String getIsbalck()
	{
		return isbalck;
	}
	public void setIsbalck(String isbalck)
	{
		this.isbalck = isbalck;
	}
	public String getLevel_name()
	{
		return level_name;
	}
	public void setLevel_name(String level_name)
	{
		this.level_name = level_name;
	}
	public long getId()
	{
		return id;
	}
	public void setId(long id)
	{
		this.id = id;
	}
	public long getLogintime()
	{
		return logintime;
	}
	public void setLogintime(long logintime)
	{
		this.logintime = logintime;
	}
	public Date getCreatetime()
	{
		return createtime;
	}
	public void setCreatetime(Date createtime)
	{
		this.createtime = createtime;
	}
	public String getCreateip()
	{
		return createip;
	}
	public void setCreateip(String createip)
	{
		this.createip = createip;
	}
	public int getLoginnum()
	{
		return loginnum;
	}
	public void setLoginnum(int loginnum)
	{
		this.loginnum = loginnum;
	}
	public int getPROHIBIT_POST()
	{
		return PROHIBIT_POST;
	}
	public void setPROHIBIT_POST(int prohibit_post)
	{
		PROHIBIT_POST = prohibit_post;
	}
	public Date getPROHIBIT_TIME()
	{
		return PROHIBIT_TIME;
	}
	public void setPROHIBIT_TIME(Date prohibit_time)
	{
		PROHIBIT_TIME = prohibit_time;
	}
	public int getTopicnum()
	{
		return topicnum;
	}
	public void setTopicnum(int topicnum)
	{
		this.topicnum = topicnum;
	}
	public int getReplynum()
	{
		return replynum;
	}
	public void setReplynum(int replynum)
	{
		this.replynum = replynum;
	}
	public int getPostday()
	{
		return postday;
	}
	public void setPostday(int postday)
	{
		this.postday = postday;
	}
	public Date getLastposttime()
	{
		return lastposttime;
	}
	public void setLastposttime(Date lastposttime)
	{
		this.lastposttime = lastposttime;
	}
	public long getHyuserid()
	{
		return hyuserid;
	}
	public void setHyuserid(long hyuserid)
	{
		this.hyuserid = hyuserid;
	}
	public int getLevel_id()
	{
		return level_id;
	}
	public void setLevel_id(int level_id)
	{
		this.level_id = level_id;
	}
	public long getLogid()
	{
		return logid;
	}
	public void setLogid(long logid)
	{
		this.logid = logid;
	}
	public long getOwner()
	{
		return owner;
	}
	public void setOwner(long owner)
	{
		this.owner = owner;
	}
	public int getJf()
	{
		return jf;
	}
	public void setJf(int jf)
	{
		this.jf = jf;
	}
	public int getUptotal()
	{
		return uptotal;
	}
	public void setUptotal(int uptotal)
	{
		this.uptotal = uptotal;
	}
	public int getDowntotal()
	{
		return downtotal;
	}
	public void setDowntotal(int downtotal)
	{
		this.downtotal = downtotal;
	}
	public int getToptotal()
	{
		return toptotal;
	}
	public void setToptotal(int toptotal)
	{
		this.toptotal = toptotal;
	}
	
	public String getNickname()
	{
		return nickname;
	}
	
	public void setNickname(String nickname)
	{
		this.nickname = nickname;
	}
	
	public String getEmail()
	{
		return email;
	}
	
	public void setEmail(String email)
	{
		this.email = email;
	}
	
	public String getTelphone()
	{
		return telphone;
	}
	
	public void setTelphone(String telphone)
	{
		this.telphone = telphone;
	}
	
	public String getImg()
	{
		return img;
	}
	
	public void setImg(String img)
	{
		this.img = img;
	}
	
}
