package com.huiyee.interact.ad.model;

import java.util.Date;

import com.huiyee.esite.util.DateUtil;


public class Adgg
{
	private long id;
	private String title;
	private String hydesc;
	private String content;
	private long owner;
	private String img;
	private String url;
	private String fsurl;
	private Date starttime;
	private Date endtime;
	private String startTime;
	private String endTime;
	private String wurl;
	
	public String getFsurl()
	{
		return fsurl;
	}

	public void setFsurl(String fsurl)
	{
		this.fsurl = fsurl;
	}


	public String getWurl()
	{
		return wurl;
	}

	
	public void setWurl(String wurl)
	{
		this.wurl = wurl;
	}

	public long getId()
	{
		return id;
	}
	
	public void setId(long id)
	{
		this.id = id;
	}
	
	public String getTitle()
	{
		return title;
	}
	
	public void setTitle(String title)
	{
		this.title = title;
	}
	
	public String getHydesc()
	{
		return hydesc;
	}
	
	public void setHydesc(String hydesc)
	{
		this.hydesc = hydesc;
	}
	
	public String getContent()
	{
		return content;
	}
	
	public void setContent(String content)
	{
		this.content = content;
	}
	
	public long getOwner()
	{
		return owner;
	}
	
	public void setOwner(long owner)
	{
		this.owner = owner;
	}
	
	public String getImg()
	{
		return img;
	}
	
	public void setImg(String img)
	{
		this.img = img;
	}
	
	public String getUrl()
	{
		return url;
	}
	
	public void setUrl(String url)
	{
		this.url = url;
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
	
	public String getStartTime()
	{
		return startTime;
	}
	
	public void setStartTime(String startTime)
	{
		if(startTime != null){
			starttime = DateUtil.getDateTime(startTime);
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
			endtime = DateUtil.getDateTime(endTime);
		}
		this.endTime = endTime;
	}
	
	
	
	
	
}
