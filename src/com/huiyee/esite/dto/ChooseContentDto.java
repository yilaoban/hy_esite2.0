
package com.huiyee.esite.dto;

import java.io.Serializable;
import java.util.List;

import com.huiyee.esite.model.ContentCategory;
import com.huiyee.esite.model.Page;
import com.huiyee.interact.bbs.model.BBSContent;

public class ChooseContentDto implements IDto, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4924776457895857654L;
	private String json;
	private String json1;
	private String json2;
	private String json3;
	private String json4;
	private String json5;//微商城目录
	private String json6;//微商城目录
	
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

	public String getJson5()
	{
		return json5;
	}

	
	public void setJson5(String json5)
	{
		this.json5 = json5;
	}

	
	public String getJson6()
	{
		return json6;
	}

	
	public void setJson6(String json6)
	{
		this.json6 = json6;
	}

}
