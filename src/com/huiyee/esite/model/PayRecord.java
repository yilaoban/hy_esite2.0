
package com.huiyee.esite.model;

import java.io.Serializable;
import java.util.Date;

public class PayRecord implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3287495660257714188L;
	private long id;
	private String mediaorder;
	private Date createtime;
	private String ip;
	private int price;
	private int status;
	private long hyuid;
	private long orderid;
	
	public int getStatus()
	{
		return status;
	}


	
	public void setStatus(int status)
	{
		this.status = status;
	}


	
	public long getHyuid()
	{
		return hyuid;
	}


	
	public void setHyuid(long hyuid)
	{
		this.hyuid = hyuid;
	}


	
	public long getOrderid()
	{
		return orderid;
	}


	
	public void setOrderid(long orderid)
	{
		this.orderid = orderid;
	}


	public int getPrice()
	{
		return price;
	}

	
	public void setPrice(int price)
	{
		this.price = price;
	}

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public String getMediaorder()
	{
		return mediaorder;
	}

	public void setMediaorder(String mediaorder)
	{
		this.mediaorder = mediaorder;
	}

	public Date getCreatetime()
	{
		return createtime;
	}

	public void setCreatetime(Date createtime)
	{
		this.createtime = createtime;
	}

	public String getIp()
	{
		return ip;
	}

	public void setIp(String ip)
	{
		this.ip = ip;
	}
}
