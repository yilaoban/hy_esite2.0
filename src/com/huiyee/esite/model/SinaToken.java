package com.huiyee.esite.model;

import java.io.Serializable;
import java.util.Date;

public class SinaToken implements Comparable<SinaToken>, Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 929164206355219077L;
	private String token;
	private Date tokenendtime;
	private long appid;
	private long cid;
	private long siteid;
	private String nickname;
	private Date createtime;
	private Date updatetime;
	private long outOfEndTime;
	private long pageid;

	public String getToken()
	{
		return token;
	}

	public void setToken(String token)
	{
		this.token = token;
	}

	public Date getTokenendtime()
	{
		return tokenendtime;
	}

	public void setTokenendtime(Date tokenendtime)
	{
		this.tokenendtime = tokenendtime;
		if (tokenendtime != null)
		{
			outOfEndTime = tokenendtime.getTime() - System.currentTimeMillis();
		}
	}

	public long getAppid()
	{
		return appid;
	}

	public void setAppid(long appid)
	{
		this.appid = appid;
	}

	public long getCid()
	{
		return cid;
	}

	public void setCid(long cid)
	{
		this.cid = cid;
	}

	public long getSiteid()
	{
		return siteid;
	}

	public void setSiteid(long siteid)
	{
		this.siteid = siteid;
	}

	public String getNickname()
	{
		return nickname;
	}

	public void setNickname(String nickname)
	{
		this.nickname = nickname;
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

	public long getOutOfEndTime()
	{
		return outOfEndTime;
	}

	public long getPageid()
	{
		return pageid;
	}

	public void setPageid(long pageid)
	{
		this.pageid = pageid;
	}

	public void setOutOfEndTime(long outOfEndTime)
	{
		this.outOfEndTime = outOfEndTime;
	}

	@Override
	public int compareTo(SinaToken o)
	{
		return (int) (this.cid - o.getCid());
	}
}
