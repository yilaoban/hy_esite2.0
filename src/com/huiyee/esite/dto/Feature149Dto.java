
package com.huiyee.esite.dto;

import java.io.Serializable;
import java.util.List;

import com.huiyee.esite.model.ContentCategory;
import com.huiyee.esite.model.Page;
import com.huiyee.interact.bbs.model.BBSContent;

public class Feature149Dto implements IDto, Serializable
{

	private static final long serialVersionUID = 5421773702058061569L;

	private long fid;
	private ContentCategory current;
	private List<ContentCategory> catList;
	private List<BBSContent> list;
	private List<Page> pagelist;
	private long pageid;
	private String json;
	private String json1;
	private String json2;
	private String json3;
	private String json4;

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

	public List<BBSContent> getList()
	{
		return list;
	}

	public void setList(List<BBSContent> list)
	{
		this.list = list;
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

	public String getJson4()
	{
		return json4;
	}

	public void setJson4(String json4)
	{
		this.json4 = json4;
	}

}
