package com.huiyee.esite.model;

import com.huiyee.weixin.model.WxMp;

public class WeiXinPage {
	private long id;
	private long pageid;
	private long ownerid;
	private String infoed="N";
	private int updateseconds;
	private long mpid;
	private WxMp wxMp;
	
	
	public long getMpid()
	{
		return mpid;
	}

	
	public void setMpid(long mpid)
	{
		this.mpid = mpid;
	}

	public WxMp getWxMp()
	{
		return wxMp;
	}
	
	public void setWxMp(WxMp wxMp)
	{
		this.wxMp = wxMp;
	}

	public long getOwnerid()
	{
		return ownerid;
	}
	
	public void setOwnerid(long ownerid)
	{
		this.ownerid = ownerid;
	}

	public String getInfoed() {
		return infoed;
	}

	public void setInfoed(String infoed) {
		this.infoed = infoed;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getPageid() {
		return pageid;
	}

	public void setPageid(long pageid) {
		this.pageid = pageid;
	}

	public int getUpdateseconds() {
		return updateseconds;
	}

	public void setUpdateseconds(int updateseconds) {
		this.updateseconds = updateseconds;
	}

}
