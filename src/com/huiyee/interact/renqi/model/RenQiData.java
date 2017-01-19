package com.huiyee.interact.renqi.model;

import java.util.Date;

public class RenQiData
{
	private String ip;
	private String name;
	private long fuid;// 发起者ID
	private int utype;
	private Date createtime;
	private int addjf;
	private int cnum;
	private long lid;
	private int lrnum;// lottery_record_id;
	private int lrStatus;// 0-未中奖,1-中了积分,2-中了优惠劵,3-中了实物
	private long rqid;

	public String getIp()
	{
		return ip;
	}

	public void setIp(String ip)
	{
		this.ip = ip;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public int getUtype()
	{
		return utype;
	}

	public void setUtype(int utype)
	{
		this.utype = utype;
	}

	public Date getCreatetime()
	{
		return createtime;
	}

	public void setCreatetime(Date createtime)
	{
		this.createtime = createtime;
	}

	public int getAddjf()
	{
		return addjf;
	}

	public void setAddjf(int addjf)
	{
		this.addjf = addjf;
	}

	public int getCnum()
	{
		return cnum;
	}

	public void setCnum(int cnum)
	{
		this.cnum = cnum;
	}

	public int getLrnum()
	{
		return lrnum;
	}

	public void setLrnum(int lrnum)
	{
		this.lrnum = lrnum;
	}

	public int getLrStatus()
	{
		return lrStatus;
	}

	public void setLrStatus(int lrStatus)
	{
		this.lrStatus = lrStatus;
	}

	public long getRqid()
	{
		return rqid;
	}

	public void setRqid(long rqid)
	{
		this.rqid = rqid;
	}

	public long getFuid()
	{
		return fuid;
	}

	public void setFuid(long fuid)
	{
		this.fuid = fuid;
	}

	public long getLid()
	{
		return lid;
	}

	public void setLid(long lid)
	{
		this.lid = lid;
	}
}
