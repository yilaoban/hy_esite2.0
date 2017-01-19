package com.huiyee.interact.lottery.model;

import java.io.Serializable;
import java.util.Date;

public class LotteryUserRecord implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7403567845593621056L;
	private long id;
	private long lid;
	private long wbuid;
	private String nickName;
	private long lpid;
	private String lpName;
	private String createtime;
	private String ip;
	private String terminal;
	private long lpcid;
	private int status;
	private String source;
	private int price;
	private LotteryPrizeCode lpc;
	private String processstatus;
	private Date processtime;
	
	public String getProcessstatus()
	{
		return processstatus;
	}

	
	public void setProcessstatus(String processstatus)
	{
		this.processstatus = processstatus;
	}

	
	public Date getProcesstime()
	{
		return processtime;
	}

	
	public void setProcesstime(Date processtime)
	{
		this.processtime = processtime;
	}

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public long getLid()
	{
		return lid;
	}

	public void setLid(long lid)
	{
		this.lid = lid;
	}

	public long getWbuid()
	{
		return wbuid;
	}

	public void setWbuid(long wbuid)
	{
		this.wbuid = wbuid;
	}

	public String getNickName()
	{
		return nickName;
	}

	public void setNickName(String nickName)
	{
		this.nickName = nickName;
	}

	public long getLpid()
	{
		return lpid;
	}

	public void setLpid(long lpid)
	{
		this.lpid = lpid;
	}

	public String getCreatetime()
	{
		return createtime;
	}

	public void setCreatetime(String createtime)
	{
		this.createtime = createtime;
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

	public long getLpcid()
	{
		return lpcid;
	}

	public void setLpcid(long lpcid)
	{
		this.lpcid = lpcid;
	}

	public String getLpName()
	{
		return lpName;
	}

	public void setLpName(String lpName)
	{
		this.lpName = lpName;
	}

	public int getStatus()
	{
		return status;
	}

	public void setStatus(int status)
	{
		this.status = status;
	}

	public String getSource()
	{
		return source;
	}

	public void setSource(String source)
	{
		this.source = source;
	}

	public LotteryPrizeCode getLpc()
	{
		return lpc;
	}

	public void setLpc(LotteryPrizeCode lpc)
	{
		this.lpc = lpc;
	}
	
	public int getPrice()
	{
		return price;
	}

	
	public void setPrice(int price)
	{
		this.price = price;
	}
	
}
