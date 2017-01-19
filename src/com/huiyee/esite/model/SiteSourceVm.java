package com.huiyee.esite.model;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

import com.google.gson.JsonArray;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class SiteSourceVm implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6143463380433041164L;
	private long id;
	private String title;
	private String path;
	private String json;
	private String type;
	private int level;
	private String img;
	
	public String getImg()
	{
		return img;
	}

	
	public void setImg(String img)
	{
		this.img = img;
	}

	public long getId()
	{
		return id;
	}
	
	public void setId(long id)
	{
		this.id = id;
	}
	
	public String getTitle()
	{
		return title;
	}
	
	public void setTitle(String title)
	{
		this.title = title;
	}
	
	public String getPath()
	{
		return path;
	}
	
	public void setPath(String path)
	{
		this.path = path;
	}
	
	public String getJson()
	{
		return json;
	}
	
	public void setJson(String json)
	{
		this.json = json;
	}
	
	public String getType()
	{
		return type;
	}
	
	public void setType(String type)
	{
		this.type = type;
	}
	
	public int getLevel()
	{
		return level;
	}
	
	public void setLevel(int level)
	{
		this.level = level;
	}
	
	public JSONObject getJsonObject(){
		if(StringUtils.isNotBlank(json)){
			return JSONObject.fromObject(json);
		}
		return new JSONObject();
	}
	
	public JSONArray getJsonArray(){
		if(StringUtils.isNotBlank(json)){
			return JSONArray.fromObject(json);
		}
		return new JSONArray();
	}
}
