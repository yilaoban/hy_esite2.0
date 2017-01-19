package project.caidan.model;

import java.util.Date;


public class CDChannel
{
	private long id;
	private String name;
	private String hydesc;
	private Date createtime;
	private long owner;
	private long caid;
	private long acid;
	
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
	
	public String getHydesc()
	{
		return hydesc;
	}
	
	public void setHydesc(String hydesc)
	{
		this.hydesc = hydesc;
	}
	
	public Date getCreatetime()
	{
		return createtime;
	}
	
	public void setCreatetime(Date createtime)
	{
		this.createtime = createtime;
	}
	
	public long getOwner()
	{
		return owner;
	}
	
	public void setOwner(long owner)
	{
		this.owner = owner;
	}
	
	public long getCaid()
	{
		return caid;
	}
	
	public void setCaid(long caid)
	{
		this.caid = caid;
	}
	
	public long getAcid()
	{
		return acid;
	}
	
	public void setAcid(long acid)
	{
		this.acid = acid;
	}
	
	
}
