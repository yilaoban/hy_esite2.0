
package com.huiyee.esite.model;

import java.io.Serializable;

public class UserPkvTag implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 791688257517514119L;
	private long id;
	private long page;
	private String k_s;
	private String v_s;
	private long tg1;
	private long tg2;
	private long tg3;
	private long tg4;
	private long tg5;
	private UserTag tag1;
	private UserTag tag2;
	private UserTag tag3;
	private UserTag tag4;
	private UserTag tag5;

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public long getPage()
	{
		return page;
	}

	public void setPage(long page)
	{
		this.page = page;
	}

	public String getK_s()
	{
		return k_s;
	}

	public void setK_s(String k_s)
	{
		this.k_s = k_s;
	}

	public String getV_s()
	{
		return v_s;
	}

	public void setV_s(String v_s)
	{
		this.v_s = v_s;
	}

	public long getTg1()
	{
		return tg1;
	}

	public void setTg1(long tg1)
	{
		this.tg1 = tg1;
	}

	public long getTg2()
	{
		return tg2;
	}

	public void setTg2(long tg2)
	{
		this.tg2 = tg2;
	}

	public long getTg3()
	{
		return tg3;
	}

	public void setTg3(long tg3)
	{
		this.tg3 = tg3;
	}

	public long getTg4()
	{
		return tg4;
	}

	public void setTg4(long tg4)
	{
		this.tg4 = tg4;
	}

	public long getTg5()
	{
		return tg5;
	}

	public void setTg5(long tg5)
	{
		this.tg5 = tg5;
	}

	public UserTag getTag1()
	{
		return tag1;
	}

	public void setTag1(UserTag tag1)
	{
		this.tag1 = tag1;
	}

	public UserTag getTag2()
	{
		return tag2;
	}

	public void setTag2(UserTag tag2)
	{
		this.tag2 = tag2;
	}

	public UserTag getTag3()
	{
		return tag3;
	}

	public void setTag3(UserTag tag3)
	{
		this.tag3 = tag3;
	}

	public UserTag getTag4()
	{
		return tag4;
	}

	public void setTag4(UserTag tag4)
	{
		this.tag4 = tag4;
	}

	public UserTag getTag5()
	{
		return tag5;
	}

	public void setTag5(UserTag tag5)
	{
		this.tag5 = tag5;
	}

}
