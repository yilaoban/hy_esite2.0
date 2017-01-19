
package com.huiyee.esite.dto;

import java.io.Serializable;
import java.util.List;

import com.huiyee.esite.model.ContentCategory;
import com.huiyee.esite.model.Page;

public class Feature150Dto implements IDto, Serializable
{

	private static final long serialVersionUID = 1595381198961485978L;

	private long fid;
	private ContentCategory current;
	private List<ContentCategory> catList;
	private String json;
	private String json1;
	private String json2;
	private String json3;

	private List<Page> pagelist;
	private long pageid;

	public long getFid()
	{
		return fid;
	}

	public void setFid(long fid)
	{
		this.fid = fid;
	}

	public ContentCategory getCurrent()
	{
		return current;
	}

	public void setCurrent(ContentCategory current)
	{
		this.current = current;
	}

	public List<ContentCategory> getCatList()
	{
		return catList;
	}

	public void setCatList(List<ContentCategory> catList)
	{
		this.catList = catList;
	}

	public List<Page> getPagelist()
	{
		return pagelist;
	}

	public void setPagelist(List<Page> pagelist)
	{
		this.pagelist = pagelist;
	}

	public long getPageid()
	{
		return pageid;
	}

	public void setPageid(long pageid)
	{
		this.pageid = pageid;
	}

	public String getJson()
	{
		return json;
	}

	public void setJson(String json)
	{
		this.json = json;
	}

	public String getJson1()
	{
		return json1;
	}

	public void setJson1(String json1)
	{
		this.json1 = json1;
	}

	public String getJson2()
	{
		return json2;
	}

	public void setJson2(String json2)
	{
		this.json2 = json2;
	}

	public String getJson3()
	{
		return json3;
	}

	public void setJson3(String json3)
	{
		this.json3 = json3;
	}

}
