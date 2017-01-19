
package com.huiyee.interact.lottery.model;

import java.io.Serializable;

/**
 * 抽奖展示页面需要的
 * 
 * @author ldw
 * 
 */
public class LotteryUserShow implements Serializable
{

	private static final long serialVersionUID = -6605587702970961716L;
	private long uid;// wbuid wxuid 等等
	private int utype;
	private String name;
	private String img;
	private long lpid;
	private int price;// 几等奖

	public long getUid()
	{
		return uid;
	}

	public void setUid(long uid)
	{
		this.uid = uid;
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

	public int getUtype()
	{
		return utype;
	}

	public void setUtype(int utype)
	{
		this.utype = utype;
	}

	public long getLpid()
	{
		return lpid;
	}

	public void setLpid(long lpid)
	{
		this.lpid = lpid;
	}

	public int getPrice()
	{
		return price;
	}

	public void setPrice(int price)
	{
		this.price = price;
	}

}
