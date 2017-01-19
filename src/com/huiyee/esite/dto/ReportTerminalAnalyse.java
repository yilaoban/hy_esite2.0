package com.huiyee.esite.dto;

import java.util.Date;

public class ReportTerminalAnalyse 
{
    private String terminal;
    private long sgid;
    private int vnum;
    private String vnumstr;
    private int hnum;
    private String hnumstr;
    private Date lasthdtime;
    private Date lastvtime;
    private String percent;
	public String getTerminal()
	{
		return terminal;
	}
	public void setTerminal(String terminal)
	{
		this.terminal = terminal;
	}
	public long getSgid()
	{
		return sgid;
	}
	public void setSgid(long sgid)
	{
		this.sgid = sgid;
	}
	public int getVnum()
	{
		return vnum;
	}
	public void setVnum(int vnum)
	{
		this.vnum = vnum;
	}
	public String getVnumstr()
	{
		return vnumstr;
	}
	public void setVnumstr(String vnumstr)
	{
		this.vnumstr = vnumstr;
	}
	public int getHnum()
	{
		return hnum;
	}
	public void setHnum(int hnum)
	{
		this.hnum = hnum;
	}
	public String getHnumstr()
	{
		return hnumstr;
	}
	public void setHnumstr(String hnumstr)
	{
		this.hnumstr = hnumstr;
	}
	public Date getLasthdtime()
	{
		return lasthdtime;
	}
	public void setLasthdtime(Date lasthdtime)
	{
		this.lasthdtime = lasthdtime;
	}
	public Date getLastvtime()
	{
		return lastvtime;
	}
	public void setLastvtime(Date lastvtime)
	{
		this.lastvtime = lastvtime;
	}
	public String getPercent()
	{
		return percent;
	}
	public void setPercent(String percent)
	{
		this.percent = percent;
	}
}
