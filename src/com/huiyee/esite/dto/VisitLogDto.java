package com.huiyee.esite.dto;

public class VisitLogDto
{
	private String cookie;
	private int mtype;// 媒体类型,0-独立站点,1-微博,2-微信,3-微信关键字触发
	private long wbuid;
	private String openid;
	private String ip;
	private long createtime;
	private long pageid;//着陆的页面id
	private String source;
	private String skey;
	private String svalue;
	private String url;
	private String uagent;
	private String atype="c";//事件类型，c-clike,s-share,h-互动，g-关注,w-外部事件
	private String adesc="click";//事件的描述
	private long aoid;//action object id
	private long hyuid;
	private long wxuid;
	private int gz;//1-关注，0-不关注
	
	public long getHyuid()
	{
		return hyuid;
	}
	
	public void setHyuid(long hyuid)
	{
		this.hyuid = hyuid;
	}
	
	public long getWxuid()
	{
		return wxuid;
	}
	
	public void setWxuid(long wxuid)
	{
		this.wxuid = wxuid;
	}
	
	public int getGz()
	{
		return gz;
	}

	
	public void setGz(int gz)
	{
		this.gz = gz;
	}

	public long getAoid()
	{
		return aoid;
	}
	
	public void setAoid(long aoid)
	{
		this.aoid = aoid;
	}
	
	public String getSkey()
	{
		return skey;
	}

	
	public void setSkey(String skey)
	{
		this.skey = skey;
	}

	
	public String getSvalue()
	{
		return svalue;
	}

	
	public void setSvalue(String svalue)
	{
		this.svalue = svalue;
	}

	public String getAtype()
	{
		return atype;
	}

	
	public void setAtype(String atype)
	{
		this.atype = atype;
	}

	
	public String getAdesc()
	{
		return adesc;
	}

	
	public void setAdesc(String adesc)
	{
		this.adesc = adesc;
	}

	public String getCookie()
	{
		return cookie;
	}

	public void setCookie(String cookie)
	{
		this.cookie = cookie;
	}

	public int getMtype()
	{
		return mtype;
	}

	public void setMtype(int mtype)
	{
		this.mtype = mtype;
	}

	public long getWbuid()
	{
		return wbuid;
	}

	public void setWbuid(long wbuid)
	{
		this.wbuid = wbuid;
	}

	public String getIp()
	{
		return ip;
	}

	public void setIp(String ip)
	{
		this.ip = ip;
	}

	public long getCreatetime()
	{
		return createtime;
	}

	public void setCreatetime(long createtime)
	{
		this.createtime = createtime;
	}

	public long getPageid()
	{
		return pageid;
	}

	public void setPageid(long pageid)
	{
		this.pageid = pageid;
	}

	public String getSource()
	{
		return source;
	}

	public void setSource(String source)
	{
		this.source = source;
	}

	public String getUrl()
	{
		return url;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}

	public String getUagent()
	{
		return uagent;
	}

	public void setUagent(String uagent)
	{
		this.uagent = uagent;
	}

	public String getOpenid()
	{
		return openid;
	}

	public void setOpenid(String openid)
	{
		this.openid = openid;
	}
}
