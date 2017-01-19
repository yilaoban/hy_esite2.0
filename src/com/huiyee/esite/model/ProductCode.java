
package com.huiyee.esite.model;

import java.io.Serializable;

public class ProductCode implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2770764322222354293L;
	private long id;
	private long pid;
	private String code;
	private String password;
	private String status;
	private int total;
	private int used;

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public long getPid()
	{
		return pid;
	}

	public void setPid(long pid)
	{
		this.pid = pid;
	}

	public String getCode()
	{
		return code;
	}

	public void setCode(String code)
	{
		this.code = code;
	}

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public int getTotal()
	{
		return total;
	}

	public void setTotal(int total)
	{
		this.total = total;
	}

	
	public int getUsed()
	{
		return used;
	}

	
	public void setUsed(int used)
	{
		this.used = used;
	}

}
