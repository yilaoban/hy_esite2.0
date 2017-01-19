
package com.huiyee.interact.cb.model;

import java.io.Serializable;
import java.util.Date;

public class SenderRecord implements Serializable
{

	/**
	 * 传播者效果
	 */
	private static final long serialVersionUID = 5675570172333953803L;

	private long wxuid;
	private String name;
	private String gender;
	private int zhuanfa;
	private int dianji;
	private int hudong;
	private int guanzhu;
	private int waibuxiaoguo;
	private Date lasttime;

	public long getWxuid()
	{
		return wxuid;
	}

	public void setWxuid(long wxuid)
	{
		this.wxuid = wxuid;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getGender()
	{
		return gender;
	}

	public void setGender(String gender)
	{
		this.gender = gender;
	}

	public int getZhuanfa()
	{
		return zhuanfa;
	}

	public void setZhuanfa(int zhuanfa)
	{
		this.zhuanfa = zhuanfa;
	}

	public int getDianji()
	{
		return dianji;
	}

	public void setDianji(int dianji)
	{
		this.dianji = dianji;
	}

	public int getHudong()
	{
		return hudong;
	}

	public void setHudong(int hudong)
	{
		this.hudong = hudong;
	}

	public int getGuanzhu()
	{
		return guanzhu;
	}

	public void setGuanzhu(int guanzhu)
	{
		this.guanzhu = guanzhu;
	}

	public int getWaibuxiaoguo()
	{
		return waibuxiaoguo;
	}

	public void setWaibuxiaoguo(int waibuxiaoguo)
	{
		this.waibuxiaoguo = waibuxiaoguo;
	}

	public Date getLasttime()
	{
		return lasttime;
	}

	public void setLasttime(Date lasttime)
	{
		this.lasttime = lasttime;
	}

}
