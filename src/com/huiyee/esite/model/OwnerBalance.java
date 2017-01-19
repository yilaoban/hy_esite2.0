package com.huiyee.esite.model;

public class OwnerBalance
{
	private long id;
	private long ownerwbuid;
	private long wbuid;
	private String nickname;
	private int total;
	private int used;
	private int preused;
	private int utype;
	
	public int getPreused()
	{
		return preused;
	}

	public void setPreused(int preused)
	{
		this.preused = preused;
	}

	public int getUtype()
	{
		return utype;
	}

	public void setUtype(int utype)
	{
		this.utype = utype;
	}

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

	public int getTotal()
	{
		return total;
	}

	public void setTotal(int total)
	{
		this.total = total;
	}

	public int getUsed()
	{
		return used;
	}

	public void setUsed(int used)
	{
		this.used = used;
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
