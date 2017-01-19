
package com.huiyee.esite.dto;


public class BalancePageDto
{

	private long sitegroupid;
	private long rmyid;// 我的储值
	private long rmjid;// 我的积分
	private long rmkid;// 我的卡券
	private long rmcid;// 完成信息的页面
	private long rmzid;// 我的账单
	private long rmxid;// 我的会员
	
	public long getRmxid()
	{
		return rmxid;
	}

	
	public void setRmxid(long rmxid)
	{
		this.rmxid = rmxid;
	}

	public long getSitegroupid()
	{
		return sitegroupid;
	}

	public void setSitegroupid(long sitegroupid)
	{
		this.sitegroupid = sitegroupid;
	}

	public long getRmzid()
	{
		return rmzid;
	}

	public void setRmzid(long rmzid)
	{
		this.rmzid = rmzid;
	}

	public long getRmyid()
	{
		return rmyid;
	}

	public long getRmjid()
	{
		return rmjid;
	}

	public long getRmkid()
	{
		return rmkid;
	}

	public long getRmcid()
	{
		return rmcid;
	}

	public void setRmyid(long rmyid)
	{
		this.rmyid = rmyid;
	}

	public void setRmjid(long rmjid)
	{
		this.rmjid = rmjid;
	}

	public void setRmkid(long rmkid)
	{
		this.rmkid = rmkid;
	}

	public void setRmcid(long rmcid)
	{
		this.rmcid = rmcid;
	}
}
