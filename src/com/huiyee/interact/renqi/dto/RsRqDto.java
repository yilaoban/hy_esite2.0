package com.huiyee.interact.renqi.dto;

import java.io.Serializable;

public class RsRqDto implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int status;// 1:参与成功,-1:已经参与过,-2:fuid不存在
	private int jf;// 帮助参与者积攒了多少人气
	private String hydesc;

	public int getStatus()
	{
		return status;
	}

	public void setStatus(int status)
	{
		this.status = status;
	}

	public int getJf()
	{
		return jf;
	}

	public void setJf(int jf)
	{
		this.jf = jf;
	}

	public String getHydesc()
	{
		return hydesc;
	}

	public void setHydesc(String hydesc)
	{
		this.hydesc = hydesc;
	}
}
