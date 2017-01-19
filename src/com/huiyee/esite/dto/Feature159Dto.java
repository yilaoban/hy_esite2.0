package com.huiyee.esite.dto;

import java.io.Serializable;
import java.util.List;

import com.huiyee.esite.model.Page;

import net.sf.json.JSONArray;

public class Feature159Dto implements IDto, Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7888884541757133729L;
	private long fid;
	private long relationid;
	private String type;
	private List<Long> list;
	private long pageid;
	private JSONArray ids;
	private List<Page> pagelist;
	
	private String json;
	private String json1;
	private String json2;
	private String json3;
	private String json4;
	
	
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

	public List<Page> getPagelist()
	{
		return pagelist;
	}
	
	public void setPagelist(List<Page> pagelist)
	{
		this.pagelist = pagelist;
	}
	public long getFid() {
		return fid;
	}
	public void setFid(long fid) {
		this.fid = fid;
	}
	public long getRelationid() {
		return relationid;
	}
	public void setRelationid(long relationid) {
		this.relationid = relationid;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public long getPageid() {
		return pageid;
	}
	public void setPageid(long pageid) {
		this.pageid = pageid;
	}
	
	public List<Long> getList()
	{
		return list;
	}
	
	public void setList(List<Long> list)
	{
		this.list = list;
	}
	
	public JSONArray getIds()
	{
		return ids;
	}
	
	public void setIds(JSONArray ids)
	{
		this.ids = ids;
	}
	
	
}
