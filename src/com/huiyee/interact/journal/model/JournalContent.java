package com.huiyee.interact.journal.model;

import java.io.Serializable;
import java.util.Date;

public class JournalContent implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 9000531224173288566L;
	private long id;
	private long jid;
	private String title;
	private String content;
	private String bimg; 
	private String simg;
	private String url;
	private String tag;
	private String sharecontent;
	private String sharepic;
	private Date createtime;
	private Date updatetime;
	private String status;
	private int count;
	private Date lastsharetime;
	
	public long getId()
	{
		return id;
	}
	public void setId(long id)
	{
		this.id = id;
	}
	public long getJid()
	{
		return jid;
	}
	public void setJid(long jid)
	{
		this.jid = jid;
	}
	public String getTitle()
	{
		return title;
	}
	public void setTitle(String title)
	{
		this.title = title;
	}
	public String getContent()
	{
		return content;
	}
	public void setContent(String content)
	{
		this.content = content;
	}
	public String getBimg()
	{
		return bimg;
	}
	public void setBimg(String bimg)
	{
		this.bimg = bimg;
	}
	public String getSimg()
	{
		return simg;
	}
	public void setSimg(String simg)
	{
		this.simg = simg;
	}
	public String getUrl()
	{
		return url;
	}
	public void setUrl(String url)
	{
		this.url = url;
	}
	public String getTag()
	{
		return tag;
	}
	public void setTag(String tag)
	{
		this.tag = tag;
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
	public int getCount()
	{
		return count;
	}
	public void setCount(int count)
	{
		this.count = count;
	}
	public Date getLastsharetime()
	{
		return lastsharetime;
	}
	public void setLastsharetime(Date lastsharetime)
	{
		this.lastsharetime = lastsharetime;
	}
	
}
