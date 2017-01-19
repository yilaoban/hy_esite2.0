package com.huiyee.esite.model;

import java.util.Arrays;
import java.util.List;

public class ContentFormMapping
{
	private long id;
	private long formid;
	private String name;
	private String mapping;
	private String defaultvalue;
	private String coltype;
	private String stype;
	private int row;
	private int datashow;
	private String record;

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public long getFormid()
	{
		return formid;
	}

	public void setFormid(long formid)
	{
		this.formid = formid;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getMapping()
	{
		return mapping;
	}

	public void setMapping(String mapping)
	{
		this.mapping = mapping;
	}

	public String getColtype()
	{
		return coltype;
	}

	public void setColtype(String coltype)
	{
		this.coltype = coltype;
	}

	public String getStype()
	{
		return stype;
	}

	public void setStype(String stype)
	{
		this.stype = stype;
	}

	public int getRow()
	{
		return row;
	}

	public void setRow(int row)
	{
		this.row = row;
	}

	public int getDatashow()
	{
		return datashow;
	}

	public void setDatashow(int datashow)
	{
		this.datashow = datashow;
	}

	public String getDefaultvalue()
	{
		return defaultvalue;
	}

	public void setDefaultvalue(String defaultvalue)
	{
		this.defaultvalue = defaultvalue;
	}

	public List<String> getDefaultList()
	{
		if (this.defaultvalue != null && this.defaultvalue.trim().length() > 0)
		{
			return Arrays.asList(this.defaultvalue.split(","));
		}
		else
		{
			return null;
		}
	}

	public String getRecord()
	{
		return record;
	}

	public void setRecord(String record)
	{
		this.record = record;
	}
}
