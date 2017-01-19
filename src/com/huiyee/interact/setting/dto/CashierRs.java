
package com.huiyee.interact.setting.dto;

public class CashierRs
{

	private int status;//-4：不是收营员；-3：没有注册会员信息；-2:必须粉丝参加；-1：用户不存在；0：失败，1-成功
	private long pageid;
	private long uway;
	private String url;
	private String hydesc;
	private int balance;//变动积分
	
	public long getUway()
	{
		return uway;
	}

	
	public void setUway(long uway)
	{
		this.uway = uway;
	}

	public int getStatus()
	{
		return status;
	}

	public long getPageid()
	{
		return pageid;
	}

	public String getUrl()
	{
		return url;
	}

	public String getHydesc()
	{
		return hydesc;
	}

	public void setStatus(int status)
	{
		this.status = status;
	}

	public void setPageid(long pageid)
	{
		this.pageid = pageid;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}

	public void setHydesc(String hydesc)
	{
		this.hydesc = hydesc;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}
	
}
