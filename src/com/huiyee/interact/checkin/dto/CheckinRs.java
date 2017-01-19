package com.huiyee.interact.checkin.dto;

public class CheckinRs
{
	private int status;// 0-竞拍失败,1-竞拍成功
	private String hydesc;
	private long jf; //签到积分
	private int daynum;//连续签到天数
	
	public int getStatus()
	{
		return status;
	}

	public void setStatus(int status)
	{
		this.status = status;
	}

	public String getHydesc()
	{
		return hydesc;
	}

	public void setHydesc(String hydesc)
	{
		this.hydesc = hydesc;
	}

	
	public long getJf()
	{
		return jf;
	}

	
	public void setJf(long jf)
	{
		this.jf = jf;
	}

	
	public int getDaynum()
	{
		return daynum;
	}

	
	public void setDaynum(int daynum)
	{
		this.daynum = daynum;
	}
	
	
}
