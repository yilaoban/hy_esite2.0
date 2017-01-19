
package com.huiyee.interact.setting.model;

public class UWay
{

	private long id;
	private long endtime;
	private long chyuid;
	private int rmb;
	private int status;
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getRmb() {
		return rmb;
	}

	public void setRmb(int rmb) {
		this.rmb = rmb;
	}

	public long getChyuid() {
		return chyuid;
	}

	public void setChyuid(long chyuid) {
		this.chyuid = chyuid;
	}

	public long getId()
	{
		return id;
	}

	public long getEndtime()
	{
		return endtime;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public void setEndtime(long endtime)
	{
		this.endtime = endtime;
	}
}
