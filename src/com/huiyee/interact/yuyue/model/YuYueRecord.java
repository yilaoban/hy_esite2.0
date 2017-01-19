package com.huiyee.interact.yuyue.model;

import java.util.Date;
import java.util.List;

import com.huiyee.esite.util.DateUtil;


public class YuYueRecord
{
	private long id;
	private long wxuid;
	private String ip;
	private long stid;
	private Date createtime;
	private Date yytime;
	private String hydesc;
	private String tag1;//服务名称
	private String tag2;//服务元名称
	
	private String nickname; //微信昵称
	private String openid;
	private String servicename;
	private String sername;
	private int shoure;
	private int sminute;
	private int ehoure;
	private int eminute;
	private String status;
	private String yytimeStr;
	private String timeStr;
	private long yzpageid;
	private String catname;
	
	private List<YuYueRecord> recordList;
	
	public List<YuYueRecord> getRecordList()
	{
		return recordList;
	}


	
	public void setRecordList(List<YuYueRecord> recordList)
	{
		this.recordList = recordList;
	}


	public long getYzpageid()
	{
		return yzpageid;
	}

	
	public void setYzpageid(long yzpageid)
	{
		this.yzpageid = yzpageid;
	}

	public String getCatname()
	{
		return catname;
	}
	
	public void setCatname(String catname)
	{
		this.catname = catname;
	}

	public String getOpenid()
	{
		return openid;
	}

	
	public void setOpenid(String openid)
	{
		this.openid = openid;
	}

	public String getYytimeStr()
	{
		return yytimeStr;
	}
	
	public void setYytimeStr(String yytimeStr)
	{
		this.yytimeStr = yytimeStr;
	}


	public Date getYytime()
	{
		return yytime;
	}

	
	public void setYytime(Date yytime)
	{
		if(yytime != null){
			yytimeStr = DateUtil.getDateString(yytime);
			timeStr = DateUtil.gerDateFormat7(yytime);
		}
		this.yytime = yytime;
	}

	
	public String getHydesc()
	{
		return hydesc;
	}

	
	public void setHydesc(String hydesc)
	{
		this.hydesc = hydesc;
	}

	public String getStatus()
	{
		return status;
	}
	
	public void setStatus(String status)
	{
		this.status = status;
	}


	public String getNickname()
	{
		return nickname;
	}

	
	public void setNickname(String nickname)
	{
		this.nickname = nickname;
	}

	
	public String getServicename()
	{
		return servicename;
	}

	
	public void setServicename(String servicename)
	{
		this.servicename = servicename;
	}

	
	public String getSername()
	{
		return sername;
	}

	
	public void setSername(String sername)
	{
		this.sername = sername;
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

	public long getId()
	{
		return id;
	}
	
	public void setId(long id)
	{
		this.id = id;
	}
	
	public long getWxuid()
	{
		return wxuid;
	}
	
	public void setWxuid(long wxuid)
	{
		this.wxuid = wxuid;
	}
	
	public String getIp()
	{
		return ip;
	}
	
	public void setIp(String ip)
	{
		this.ip = ip;
	}
	
	public long getStid()
	{
		return stid;
	}
	
	public void setStid(long stid)
	{
		this.stid = stid;
	}
	
	public Date getCreatetime()
	{
		return createtime;
	}
	
	public void setCreatetime(Date createtime)
	{
		this.createtime = createtime;
	}



	
	public String getTimeStr()
	{
		return timeStr;
	}

	
	public void setTimeStr(String timeStr)
	{
		this.timeStr = timeStr;
	}



	public String getTag1() {
		return tag1;
	}



	public void setTag1(String tag1) {
		this.tag1 = tag1;
	}



	public String getTag2() {
		return tag2;
	}



	public void setTag2(String tag2) {
		this.tag2 = tag2;
	}
	
}
