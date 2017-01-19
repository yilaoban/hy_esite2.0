package com.huiyee.esite.model;

import java.io.Serializable;
import java.util.Date;

public class OwnerPrivilege implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6502029768509610723L;
	private long id;
	private long ownerid;
	private long pid;
	private String module;
	private int level;
	private Date createtime;
	private Date starttime;
	private Date endtime;
	private String status;
	
	
	public long getPid()
	{
		return pid;
	}


	
	public void setPid(long pid)
	{
		this.pid = pid;
	}


	public int getLevel()
	{
		return level;
	}

	
	public void setLevel(int level)
	{
		this.level = level;
	}

	
	public String getStatus()
	{
		return status;
	}

	
	public void setStatus(String status)
	{
		this.status = status;
	}

	public OwnerPrivilege(){
		
	}
	
	public OwnerPrivilege(long pid,String module,int level,String status,Date endtime){
		this.pid = pid;
		this.module = module;
		this.level = level;
		this.status = status;
		this.endtime = endtime;
	}
	
	public long getOwnerid()
	{
		return ownerid;
	}

	
	public void setOwnerid(long ownerid)
	{
		this.ownerid = ownerid;
	}

	public long getId()
	{
		return id;
	}
	
	public void setId(long id)
	{
		this.id = id;
	}
	
	public String getModule()
	{
		return module;
	}
	
	public void setModule(String module)
	{
		this.module = module;
	}

	public Date getCreatetime()
	{
		return createtime;
	}
	
	public void setCreatetime(Date createtime)
	{
		this.createtime = createtime;
	}
	
	public Date getStarttime()
	{
		return starttime;
	}
	
	public void setStarttime(Date starttime)
	{
		this.starttime = starttime;
	}
	
	public Date getEndtime()
	{
		return endtime;
	}
	
	public void setEndtime(Date endtime)
	{
		this.endtime = endtime;
	}
	
}
