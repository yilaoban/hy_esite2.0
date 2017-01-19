package com.huiyee.interact.exam.dto;

import java.util.ArrayList;
import java.util.List;

public class OrdData
{
	private String name;
	private List<Integer> data = new ArrayList<Integer>();

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public List<Integer> getData()
	{
		return data;
	}

	public void setData(List<Integer> data)
	{
		this.data = data;
	}

}
