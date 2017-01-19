package com.huiyee.esite.dto;

public class MoveToGroupDto implements IDto
{
	private long id; //互动表的id
	private String type;// * t:投票* d:调研* f:表单* l:砸金蛋 * y:摇一摇 * z:转盘
	private String area;
	private int fensi;
	private long asigngid;// 目标组
	private long xcid;
	
	public long getXcid()
	{
		return xcid;
	}

	
	public void setXcid(long xcid)
	{
		this.xcid = xcid;
	}

	public long getId()
	{
		return id;
	}
	
	public void setId(long id)
	{
		this.id = id;
	}
	
	public String getType()
	{
		return type;
	}
	
	public void setType(String type)
	{
		this.type = type;
	}
	
	public String getArea()
	{
		return area;
	}
	
	public void setArea(String area)
	{
		this.area = area;
	}
	
	public int getFensi()
	{
		return fensi;
	}
	
	public void setFensi(int fensi)
	{
		this.fensi = fensi;
	}
	
	public long getAsigngid()
	{
		return asigngid;
	}
	
	public void setAsigngid(long asigngid)
	{
		this.asigngid = asigngid;
	}
	
	
}
