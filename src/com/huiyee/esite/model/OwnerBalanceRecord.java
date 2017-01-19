package com.huiyee.esite.model;

import java.util.Date;

public class OwnerBalanceRecord
{
	private long id;
	private long ownerwbuid;
	private long wbuid;
	private String nickname;
	private int actionnum;
	private String hydesc;
	private Date createtime;

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public long getOwnerwbuid()
	{
		return ownerwbuid;
	}

	public void setOwnerwbuid(long ownerwbuid)
	{
		this.ownerwbuid = ownerwbuid;
	}

	public long getWbuid()
	{
		return wbuid;
	}

	public void setWbuid(long wbuid)
	{
		this.wbuid = wbuid;
	}

	public int getActionnum()
	{
		return actionnum;
	}

	public void setActionnum(int actionnum)
	{
		this.actionnum = actionnum;
	}

	public String getHydesc()
	{
		return hydesc;
	}

	public void setHydesc(String hydesc)
	{
		this.hydesc = hydesc;
	}

	public Date getCreatetime()
	{
		return createtime;
	}

	public void setCreatetime(Date createtime)
	{
		this.createtime = createtime;
	}

	public String getNickname()
	{
		return nickname;
	}

	public void setNickname(String nickname)
	{
		this.nickname = nickname;
	}

}
