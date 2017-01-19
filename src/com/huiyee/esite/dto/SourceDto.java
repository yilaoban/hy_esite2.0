package com.huiyee.esite.dto;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.huiyee.esite.model.Page;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class SourceDto implements IDto{
	
	private String json;
	private String vjson;
	private List<Page> pages;
	
	public String getTop()
	{
		return top;
	}
	
	public void setTop(String top)
	{
		this.top = top;
	}
	
	public String getLeft()
	{
		return left;
	}

	
	public void setLeft(String left)
	{
		this.left = left;
	}

	private String top;
	private String left;
	private String right;
	private String bottom;
	
	
	public String getRight()
	{
		return right;
	}

	
	public void setRight(String right)
	{
		this.right = right;
	}

	
	public String getBottom()
	{
		return bottom;
	}

	
	public void setBottom(String bottom)
	{
		this.bottom = bottom;
	}

	public List<Page> getPages()
	{
		return pages;
	}
	
	public void setPages(List<Page> pages)
	{
		this.pages = pages;
	}

	public String getJson()
	{
		return json;
	}
	
	public void setJson(String json)
	{
		this.json = json;
	}
	
	public JSONObject getJsonObj(){
		if(!StringUtils.isEmpty(json)){
			return JSONObject.fromObject(json);
		}
		return null;
	}
	
	public JSONObject getVjsonObj(){
		if(!StringUtils.isEmpty(vjson)){
			return JSONObject.fromObject(vjson);
		}
		return null;
	}
	
	public JSONArray getVjsonArray(){
		if(!StringUtils.isEmpty(vjson)){
			return JSONArray.fromObject(vjson);
		}
		return null;
	}
	
	public String getVjson()
	{
		return vjson;
	}
	
	public void setVjson(String vjson)
	{
		this.vjson = vjson;
	}
	
}
