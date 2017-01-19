
package com.huiyee.esite.model;

import java.io.Serializable;
import java.util.Date;

public class UserTag implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1612633590551625251L;
	private long id;
	private long owner;
	private String name;
	private Date createtime;
	private String del_tag;

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public long getOwner()
	{
		return owner;
	}

	public void setOwner(long owner)
	{
		this.owner = owner;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public Date getCreatetime()
	{
		return createtime;
	}

	public void setCreatetime(Date createtime)
	{
		this.createtime = createtime;
	}

	public String getDel_tag()
	{
		return del_tag;
	}

	public void setDel_tag(String del_tag)
	{
		this.del_tag = del_tag;
	}

}
