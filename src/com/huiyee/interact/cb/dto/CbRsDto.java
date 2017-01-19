
package com.huiyee.interact.cb.dto;

public class CbRsDto
{

	private int status;//-1 不是推广员，跳转到某个地址，-2 不是微信登录，1-正常显示,-3 提现金额不足
	private String rs;

	public int getStatus()
	{
		return status;
	}

	public void setStatus(int status)
	{
		this.status = status;
	}

	public String getRs()
	{
		return rs;
	}

	public void setRs(String rs)
	{
		this.rs = rs;
	}

}
