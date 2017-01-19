package com.huiyee.interact.yuyue.model;

import java.util.Date;

import com.huiyee.esite.util.DateUtil;


public class YuYueSSTime
{
	private long id;
	private int dateday;
//	private int weeknum;
	private int weekday;
	private int total;
	private long ssid;
	private long owner; 
	private int shoure;
	private int sminute;
	private int ehoure;
	private int eminute;
	private String startTime;
	private String endTime;
	
	private String sername;//提供者名称
	private String servicename;//服务名称
	private int type;
	private Date starttime;
	private Date endtime;
	
	private String stime;
	private String etime;
	
	private String[] weekdays;
	private String[] startTimes;
	private String[] endTimes;
	private String[] datedays;
	
	public String[] getDatedays()
	{
		return datedays;
	}

	
	public void setDatedays(String[] datedays)
	{
		this.datedays = datedays;
	}

	public String[] getStartTimes()
	{
		return startTimes;
	}
	
	public void setStartTimes(String[] startTimes)
	{
		this.startTimes = startTimes;
	}

	
	public String[] getEndTimes()
	{
		return endTimes;
	}

	public void setEndTimes(String[] endTimes)
	{
		this.endTimes = endTimes;
	}

	public String[] getWeekdays()
	{
		return weekdays;
	}
	
	public void setWeekdays(String[] weekdays)
	{
		this.weekdays = weekdays;
	}

	public String getSername()
	{
		return sername;
	}
	
	public void setSername(String sername)
	{
		this.sername = sername;
	}
	
	public String getServicename()
	{
		return servicename;
	}
	
	public void setServicename(String servicename)
	{
		this.servicename = servicename;
	}

	public int getTotal()
	{
		return total;
	}

	public void setTotal(int total)
	{
		this.total = total;
	}

	public long getId()
	{
		return id;
	}
	
	public void setId(long id)
	{
		this.id = id;
	}
	
	public int getDateday()
	{
		return dateday;
	}
	
	public void setDateday(int dateday)
	{
		this.dateday = dateday;
	}
	
	
	public int getWeekday()
	{
		return weekday;
	}
	
	public void setWeekday(int weekday)
	{
		this.weekday = weekday;
	}
	
	public long getSsid()
	{
		return ssid;
	}
	
	public void setSsid(long ssid)
	{
		this.ssid = ssid;
	}
	
	public long getOwner()
	{
		return owner;
	}
	
	public void setOwner(long owner)
	{
		this.owner = owner;
	}
	
	public int getShoure()
	{
		return shoure;
	}
	
	public void setShoure(int shoure)
	{
		this.shoure = shoure;
	}
	
	public int getSminute()
	{
		return sminute;
	}
	
	public void setSminute(int sminute)
	{
		this.sminute = sminute;
	}
	
	public int getEhoure()
	{
		return ehoure;
	}
	
	public void setEhoure(int ehoure)
	{
		this.ehoure = ehoure;
	}
	
	public int getEminute()
	{
		return eminute;
	}
	
	public void setEminute(int eminute)
	{
		this.eminute = eminute;
	}


	
	public String getStartTime()
	{
		return startTime;
	}


	
	public void setStartTime(String startTime)
	{
		if(startTime != null){
			String[] str = startTime.split(":");
			shoure = Integer.parseInt(str[0]);
			sminute = Integer.parseInt(str[1]);
		}
		this.startTime = startTime;
	}

	
	public String getEndTime()
	{
		return endTime;
	}


	
	public void setEndTime(String endTime)
	{
		if(endTime != null){
			String[] str = endTime.split(":");
			ehoure = Integer.parseInt(str[0]);
			eminute = Integer.parseInt(str[1]);
		}
		this.endTime = endTime;
	}
	
	public Date getStarttime()
	{
		return starttime;
	}
	
	public void setStarttime(Date starttime)
	{
		this.starttime = starttime;
	}
	
	public Date getEndtime()
	{
		return endtime;
	}

	public void setEndtime(Date endtime)
	{
		this.endtime = endtime;
	}

	public int getType()
	{
		return type;
	}
	
	public void setType(int type)
	{
		this.type = type;
	}


	
	public String getStime()
	{
		return stime;
	}


	
	public void setStime(String stime)
	{
		if(stime != null){
			starttime = DateUtil.getDateTime(stime);
		}
		this.stime = stime;
	}


	
	public String getEtime()
	{
		return etime;
	}


	
	public void setEtime(String etime)
	{
		if(etime != null){
			endtime = DateUtil.getDateTime(etime);
		}
		this.etime = etime;
	}
	
	
}
