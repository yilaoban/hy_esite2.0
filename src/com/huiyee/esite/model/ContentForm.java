package com.huiyee.esite.model;

import java.util.List;

public class ContentForm
{
	private long id;
	private long ownerid;
	private String title;
	private long catid;
	private List<ContentFormMapping> list;// ¹Ì¶¨×Ö¶Î
	private List<ContentFormMapping> flist;// f1-f20
	private List<ContentFormMapping> showList;

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public long getOwnerid()
	{
		return ownerid;
	}

	public void setOwnerid(long ownerid)
	{
		this.ownerid = ownerid;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public long getCatid()
	{
		return catid;
	}

	public void setCatid(long catid)
	{
		this.catid = catid;
	}

	public List<ContentFormMapping> getList()
	{
		return list;
	}

	public void setList(List<ContentFormMapping> list)
	{
		this.list = list;
	}

	public List<ContentFormMapping> getFlist()
	{
		return flist;
	}

	public void setFlist(List<ContentFormMapping> flist)
	{
		this.flist = flist;
	}

	public List<ContentFormMapping> getShowList()
	{
		return showList;
	}

	public void setShowList(List<ContentFormMapping> showList)
	{
		this.showList = showList;
	}

}
