
package com.huiyee.interact.appointment.model;

import java.io.Serializable;

public class AptCode implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -457561183803263819L;
	private long aptid;
	private String code;
	private int status;

	public long getAptid()
	{
		return aptid;
	}

	public void setAptid(long aptid)
	{
		this.aptid = aptid;
	}

	public String getCode()
	{
		return code;
	}

	public void setCode(String code)
	{
		this.code = code;
	}

	public int getStatus()
	{
		return status;
	}

	public void setStatus(int status)
	{
		this.status = status;
	}

}
