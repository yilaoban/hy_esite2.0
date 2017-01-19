package com.huiyee.esite.model;

import java.io.Serializable;


public class PayApt implements Serializable
{
	private static final long serialVersionUID = 4331046570711139922L;
	private long id;
	private long hyuid;
	private long wxuid;
	private String img;
	
	public long getId()
	{
		return id;
	}
	
	public void setId(long id)
	{
		this.id = id;
	}
	
	public long getHyuid()
	{
		return hyuid;
	}
	
	public void setHyuid(long hyuid)
	{
		this.hyuid = hyuid;
	}
	
	public long getWxuid()
	{
		return wxuid;
	}
	
	public void setWxuid(long wxuid)
	{
		this.wxuid = wxuid;
	}
	
	public String getImg()
	{
		return img;
	}
	
	public void setImg(String img)
	{
		this.img = img;
	}
	
}
