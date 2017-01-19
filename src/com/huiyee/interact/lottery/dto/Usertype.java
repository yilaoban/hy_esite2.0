package com.huiyee.interact.lottery.dto;

import java.io.Serializable;

public class Usertype implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8309334909834396392L;
	private long xcid;//微现场的id
	private int jf;//需要的积分
	
	public long getXcid()
	{
		return xcid;
	}

	public void setXcid(long xcid)
	{
		this.xcid = xcid;
	}

	public int getJf()
	{
		return jf;
	}

	public void setJf(int jf)
	{
		this.jf = jf;
	}
	
}
