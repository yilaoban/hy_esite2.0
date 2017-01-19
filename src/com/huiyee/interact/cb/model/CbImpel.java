
package com.huiyee.interact.cb.model;

import java.io.Serializable;
import java.util.Date;

import com.huiyee.esite.util.DateUtil;

public class CbImpel implements Serializable
{

	private long id;
	private long wxuid;
	private String name;
	private Date starttime;
	private Date endtime;
	private int zhuanfa;
	private int dianji;
	private int hudong;
	private int guanzhu;
	private int waibuxiaoguo;
	private int hongbao;
	private Date createtime;

	public long getWxuid()
	{
		return wxuid;
	}

	public void setWxuid(long wxuid)
	{
		this.wxuid = wxuid;
	}

	public Date getStarttime()
	{
		return starttime;
	}

	public void setStarttime(Date starttime)
	{
		this.starttime = starttime;
	}

	public void setStarttimeStr(String starttime)
	{
		if (starttime != null && starttime.trim().length() > 0)
			this.starttime = DateUtil.getDateTime(starttime);
	}

	public Date getEndtime()
	{
		return endtime;
	}

	public void setEndtime(Date endtime)
	{
		this.endtime = endtime;
	}

	public void setEndtimeStr(String endtime)
	{
		if (endtime != null && endtime.trim().length() > 0)
			this.endtime = DateUtil.getDateTime(endtime);
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

	public int getHongbao()
	{
		return hongbao;
	}

	public void setHongbao(int hongbao)
	{
		this.hongbao = hongbao;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public Date getCreatetime()
	{
		return createtime;
	}

	public void setCreatetime(Date createtime)
	{
		this.createtime = createtime;
	}

}
