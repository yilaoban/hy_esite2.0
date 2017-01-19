
package com.huiyee.esite.model;

import java.io.Serializable;
import java.util.Date;

public class UserLevel implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1612633590551625251L;
	private long id;
	private String name;
	private String img;
	private String hydesc;
	private long owner;
	private int dannum;
	private int totalnum;
	private int usednum;
	private double price;
	
	public long getId()
	{
		return id;
	}
	
	public void setId(long id)
	{
		this.id = id;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getImg()
	{
		return img;
	}
	
	public void setImg(String img)
	{
		this.img = img;
	}
	
	public String getHydesc()
	{
		return hydesc;
	}
	
	public void setHydesc(String hydesc)
	{
		this.hydesc = hydesc;
	}
	
	public long getOwner()
	{
		return owner;
	}
	
	public void setOwner(long owner)
	{
		this.owner = owner;
	}
	
	public int getDannum()
	{
		return dannum;
	}
	
	public void setDannum(int dannum)
	{
		this.dannum = dannum;
	}
	
	public int getTotalnum()
	{
		return totalnum;
	}
	
	public void setTotalnum(int totalnum)
	{
		this.totalnum = totalnum;
	}
	
	public int getUsednum()
	{
		return usednum;
	}
	
	public void setUsednum(int usednum)
	{
		this.usednum = usednum;
	}

	
	public double getPrice()
	{
		return price;
	}

	
	public void setPrice(double price)
	{
		this.price = price;
	}

}
