package com.huiyee.esite.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.google.gson.JsonObject;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class OwnerSource implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6143463380433041164L;
	private long id;
	private long ownerid;
	private String title;
	private Date createtime;
	private String json;
	private String html;
	private int level;
	private SiteSourceVm vm;
	private int pageUsed;
	private String style;
	
	public String getHtml()
	{
		return html;
	}

	
	public void setHtml(String html)
	{
		this.html = html;
	}

	public String getJson()
	{
		return json;
	}
	
	public void setJson(String json)
	{
		this.json = json;
	}
	
	public int getLevel()
	{
		return level;
	}
	
	public void setLevel(int level)
	{
		this.level = level;
	}

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
	
	public Date getCreatetime()
	{
		return createtime;
	}
	
	public void setCreatetime(Date createtime)
	{
		this.createtime = createtime;
	}
	
	public SiteSourceVm getVm()
	{
		return vm;
	}
	
	public void setVm(SiteSourceVm vm)
	{
		this.vm = vm;
	}
	
	public JSONObject getJsonObject(){
		if(StringUtils.isNotBlank(json)){
			return JSONObject.fromObject(json);
		}
		return vm.getJsonObject();
	}
	
	public JSONArray getJsonArray(){
		if(StringUtils.isNotBlank(json)){
			return JSONArray.fromObject(json);
		}
		return vm.getJsonArray();
	}

	
	public int getPageUsed()
	{
		return pageUsed;
	}

	
	public void setPageUsed(int pageUsed)
	{
		this.pageUsed = pageUsed;
	}

	
	public String getStyle()
	{
		return style;
	}

	
	public void setStyle(String style)
	{
		this.style = style;
	}
	
	public JSONObject getStyleJson(){
		if(StringUtils.isNotEmpty(style)){
			return JSONObject.fromObject(style);
		}
		return null;
	}
}
