
package com.huiyee.esite.model;

import java.io.Serializable;
import java.util.Date;

public class ProductLevel implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2770764322222354293L;
	private long id;
	private long productid;
	private long levelid;
	private double price;
	private Date createtime;
	
	public long getId()
	{
		return id;
	}
	
	public void setId(long id)
	{
		this.id = id;
	}
	
	public long getProductid()
	{
		return productid;
	}
	
	public void setProductid(long productid)
	{
		this.productid = productid;
	}
	
	public long getLevelid()
	{
		return levelid;
	}
	
	public void setLevelid(long levelid)
	{
		this.levelid = levelid;
	}
	
	public double getPrice()
	{
		return price;
	}
	
	public void setPrice(double price)
	{
		this.price = price;
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
