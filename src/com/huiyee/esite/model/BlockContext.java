package com.huiyee.esite.model;

import java.io.Serializable;

public class BlockContext implements Serializable {

	private static final long serialVersionUID = 0L;
	private long id;
	private long blockid;
	private String context;
	private String type;
	private long cardid;
	
	public long getCardid()
	{
		return cardid;
	}
	public void setCardid(long cardid)
	{
		this.cardid = cardid;
	}
	public long getId()
	{
		return id;
	}
	public void setId(long id)
	{
		this.id = id;
	}
	public long getBlockid()
	{
		return blockid;
	}
	public void setBlockid(long blockid)
	{
		this.blockid = blockid;
	}
	public String getContext()
	{
		return context;
	}
	public void setContext(String context)
	{
		this.context = context;
	}
	public String getType()
	{
		return type;
	}
	public void setType(String type)
	{
		this.type = type;
	}
	
}
