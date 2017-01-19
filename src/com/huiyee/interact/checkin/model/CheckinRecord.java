package com.huiyee.interact.checkin.model;

import java.io.Serializable;
import java.util.Date;

import com.huiyee.esite.util.DateUtil;

public class CheckinRecord implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -885623867557689494L;
	private long id;
	private long wbuid;
	private long pageid;
	private long ownerwbuid;
	private int addbalance;
	private Date createtime;
	private String ip;
	private String terminal;
	private String source;
	private int daynum;

	private String createtimeStr;
	private String nickname;
	private long hyuid;
	
	public long getHyuid()
	{
		return hyuid;
	}


	
	public void setHyuid(long hyuid)
	{
		this.hyuid = hyuid;
	}


	public String getNickname()
	{
		return nickname;
	}

	
	public void setNickname(String nickname)
	{
		this.nickname = nickname;
	}

	public String getCreatetimeStr()
	{
		return createtimeStr;
	}

	public void setCreatetimeStr(String createtimeStr)
	{
		this.createtimeStr = createtimeStr;
	}

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public long getWbuid()
	{
		return wbuid;
	}

	public void setWbuid(long wbuid)
	{
		this.wbuid = wbuid;
	}

	public long getPageid()
	{
		return pageid;
	}

	public void setPageid(long pageid)
	{
		this.pageid = pageid;
	}

	public long getOwnerwbuid()
	{
		return ownerwbuid;
	}

	public void setOwnerwbuid(long ownerwbuid)
	{
		this.ownerwbuid = ownerwbuid;
	}

	public int getAddbalance()
	{
		return addbalance;
	}

	public void setAddbalance(int addbalance)
	{
		this.addbalance = addbalance;
	}

	public Date getCreatetime()
	{
		return createtime;
	}

	public void setCreatetime(Date createtime)
	{
		this.createtime = createtime;
		setCreatetimeStr(DateUtil.getDateString(createtime));
	}

	public String getIp()
	{
		return ip;
	}

	public void setIp(String ip)
	{
		this.ip = ip;
	}

	public String getTerminal()
	{
		return terminal;
	}

	public void setTerminal(String terminal)
	{
		this.terminal = terminal;
	}

	public String getSource()
	{
		return source;
	}

	public void setSource(String source)
	{
		this.source = source;
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
