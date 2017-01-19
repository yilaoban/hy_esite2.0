package com.huiyee.interact.checkin.model;

import java.io.Serializable;
import java.util.List;

public class CheckinBalance implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1427383938907891372L;
	private long id;
	private long ownerwbuid;
	private long wbuid;
	private int total;
	private int used;
	private int preused;
	private int utype;
	private int checkinBalance;//签到积分
	private int remainBalance;//剩余积分
	private int ischeck; //0:没有签到 1：签到
	
	private List<CheckinRecord> checkinRecord;
	
	public List<CheckinRecord> getCheckinRecord()
	{
		return checkinRecord;
	}
	public void setCheckinRecord(List<CheckinRecord> checkinRecord)
	{
		this.checkinRecord = checkinRecord;
	}
	public int getRemainBalance()
	{
		return remainBalance;
	}
	public void setRemainBalance(int remainBalance)
	{
		this.remainBalance = remainBalance;
	}
	public int getCheckinBalance()
	{
		return checkinBalance;
	}
	public void setCheckinBalance(int checkinBalance)
	{
		this.checkinBalance = checkinBalance;
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
	public int getIscheck()
	{
		return ischeck;
	}
	public void setIscheck(int ischeck)
	{
		this.ischeck = ischeck;
	}

	
}
