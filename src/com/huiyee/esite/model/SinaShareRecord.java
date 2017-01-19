package com.huiyee.esite.model;

import java.io.Serializable;
import java.sql.Date;

public class SinaShareRecord implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7111773877546674471L;
	private long id;
	private long shareid;
	private long wbuid;
	private long wbid;
	private String content;
	private String imgPath;
	private Date createtime;
	private int repostsCount;
	private int commentsCount;
	private int attitudesCount;
	private String status;
	public long getId()
	{
		return id;
	}
	public void setId(long id)
	{
		this.id = id;
	}
	public long getShareid()
	{
		return shareid;
	}
	public void setShareid(long shareid)
	{
		this.shareid = shareid;
	}
	public long getWbuid()
	{
		return wbuid;
	}
	public void setWbuid(long wbuid)
	{
		this.wbuid = wbuid;
	}
	public long getWbid()
	{
		return wbid;
	}
	public void setWbid(long wbid)
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
	public Date getCreatetime()
	{
		return createtime;
	}
	public void setCreatetime(Date createtime)
	{
		this.createtime = createtime;
	}
	public int getRepostsCount()
	{
		return repostsCount;
	}
	public void setRepostsCount(int repostsCount)
	{
		this.repostsCount = repostsCount;
	}
	public int getCommentsCount()
	{
		return commentsCount;
	}
	public void setCommentsCount(int commentsCount)
	{
		this.commentsCount = commentsCount;
	}
	public int getAttitudesCount()
	{
		return attitudesCount;
	}
	public void setAttitudesCount(int attitudesCount)
	{
		this.attitudesCount = attitudesCount;
	}
	public String getStatus()
	{
		return status;
	}
	public void setStatus(String status)
	{
		this.status = status;
	}
	public String getImgPath()
	{
		return imgPath;
	}
	public void setImgPath(String imgPath)
	{
		this.imgPath = imgPath;
	}
}
