package com.huiyee.interact.cb.model;

import java.util.Date;

import com.huiyee.esite.util.DateUtil;


public class CbActivityJlRecord
{
	private long id;
	private long cbid;
	private long aid;
	private long pageid;
	private long sender;
	private int total;
	private Date createtime;
	private Date dataday;
	private int zhuanfa;
	private int zhuanfanum;
	private int guanzhu;
	private int guanzhunum;
	private int hudong;
	private int hudongnum;
	private int waibu;
	private int waibunum;
	private int click;
	private int clicknum;
	private int status;
	
	private int zhuanfatotal;
	private int clicktotal;
	private int guanzhutotal;
	private int hudongtotal;
	private String datadayStr;
	
	private int zhuanfanumtotal;
	private int clicknumtotal;
	private int guanzhunumtotal;
	private int hudongnumtotal;
	
	public int getZhuanfatotal()
	{
		return zhuanfatotal;
	}

	
	public void setZhuanfatotal(int zhuanfatotal)
	{
		this.zhuanfatotal = zhuanfatotal;
	}

	
	public int getClicktotal()
	{
		return clicktotal;
	}

	
	public void setClicktotal(int clicktotal)
	{
		this.clicktotal = clicktotal;
	}

	
	public int getGuanzhutotal()
	{
		return guanzhutotal;
	}

	
	public void setGuanzhutotal(int guanzhutotal)
	{
		this.guanzhutotal = guanzhutotal;
	}

	
	public int getHudongtotal()
	{
		return hudongtotal;
	}

	
	public void setHudongtotal(int hudongtotal)
	{
		this.hudongtotal = hudongtotal;
	}

	public long getId()
	{
		return id;
	}
	
	public void setId(long id)
	{
		this.id = id;
	}
	
	public long getCbid()
	{
		return cbid;
	}
	
	public void setCbid(long cbid)
	{
		this.cbid = cbid;
	}
	
	public long getAid()
	{
		return aid;
	}
	
	public void setAid(long aid)
	{
		this.aid = aid;
	}
	
	public long getPageid()
	{
		return pageid;
	}
	
	public void setPageid(long pageid)
	{
		this.pageid = pageid;
	}
	
	public long getSender()
	{
		return sender;
	}
	
	public void setSender(long sender)
	{
		this.sender = sender;
	}
	
	public int getTotal()
	{
		return total;
	}
	
	public void setTotal(int total)
	{
		this.total = total;
	}
	
	public Date getCreatetime()
	{
		return createtime;
	}
	
	public void setCreatetime(Date createtime)
	{
		this.createtime = createtime;
	}
	
	public Date getDataday()
	{
		return dataday;
	}
	
	public void setDataday(Date dataday)
	{
		if(dataday != null){
			setDatadayStr(DateUtil.getDateString(dataday));
		}
		this.dataday = dataday;
	}
	
	public int getZhuanfa()
	{
		return zhuanfa;
	}
	
	public void setZhuanfa(int zhuanfa)
	{
		this.zhuanfa = zhuanfa;
	}
	
	public int getZhuanfanum()
	{
		return zhuanfanum;
	}

	public void setZhuanfanum(int zhuanfanum)
	{
		this.zhuanfanum = zhuanfanum;
	}


	public int getGuanzhu()
	{
		return guanzhu;
	}
	
	public void setGuanzhu(int guanzhu)
	{
		this.guanzhu = guanzhu;
	}
	
	public int getGuanzhunum()
	{
		return guanzhunum;
	}
	
	public void setGuanzhunum(int guanzhunum)
	{
		this.guanzhunum = guanzhunum;
	}
	
	public int getHudong()
	{
		return hudong;
	}
	
	public void setHudong(int hudong)
	{
		this.hudong = hudong;
	}
	
	public int getHudongnum()
	{
		return hudongnum;
	}
	
	public void setHudongnum(int hudongnum)
	{
		this.hudongnum = hudongnum;
	}
	
	public int getWaibu()
	{
		return waibu;
	}
	
	public void setWaibu(int waibu)
	{
		this.waibu = waibu;
	}
	
	public int getWaibunum()
	{
		return waibunum;
	}
	
	public void setWaibunum(int waibunum)
	{
		this.waibunum = waibunum;
	}
	
	public int getClick()
	{
		return click;
	}
	
	public void setClick(int click)
	{
		this.click = click;
	}
	
	public int getClicknum()
	{
		return clicknum;
	}
	
	public void setClicknum(int clicknum)
	{
		this.clicknum = clicknum;
	}
	
	public int getStatus()
	{
		return status;
	}
	
	public void setStatus(int status)
	{
		this.status = status;
	}


	
	public String getDatadayStr()
	{
		return datadayStr;
	}
	
	public void setDatadayStr(String datadayStr)
	{
		this.datadayStr = datadayStr;
	}


	
	public int getZhuanfanumtotal()
	{
		return zhuanfanumtotal;
	}


	
	public void setZhuanfanumtotal(int zhuanfanumtotal)
	{
		this.zhuanfanumtotal = zhuanfanumtotal;
	}


	
	public int getClicknumtotal()
	{
		return clicknumtotal;
	}


	
	public void setClicknumtotal(int clicknumtotal)
	{
		this.clicknumtotal = clicknumtotal;
	}


	
	public int getGuanzhunumtotal()
	{
		return guanzhunumtotal;
	}


	
	public void setGuanzhunumtotal(int guanzhunumtotal)
	{
		this.guanzhunumtotal = guanzhunumtotal;
	}


	
	public int getHudongnumtotal()
	{
		return hudongnumtotal;
	}


	
	public void setHudongnumtotal(int hudongnumtotal)
	{
		this.hudongnumtotal = hudongnumtotal;
	}
	
}
