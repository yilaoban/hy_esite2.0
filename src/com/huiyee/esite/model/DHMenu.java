package com.huiyee.esite.model;

import java.util.List;

public class DHMenu{
	private String name;
	private String link;
	private String ico;
	private List<DHMenu> list;
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getLink()
	{
		return link;
	}
	
	public void setLink(String link)
	{
		this.link = link;
	}
	
	public String getIco()
	{
		return ico;
	}
	
	public void setIco(String ico)
	{
		this.ico = ico;
	}
	
	public List<DHMenu> getList()
	{
		return list;
	}
	
	public void setList(List<DHMenu> list)
	{
		this.list = list;
	}
}
