
package com.huiyee.esite.dto;

import java.util.List;

import com.huiyee.esite.model.ContentCategory;
import com.huiyee.esite.model.ContentProduct;
import com.huiyee.esite.model.ProductCode;
import com.huiyee.esite.model.UserLevel;

public class MarketingEbDto implements IDto
{

	private ContentCategory current;
	private String jsonTree;
	private List<ContentProduct> list;
	private ContentProduct cproduct;
	private Pager pager;
	private List<Long> topicList;
	private List<ProductCode> codeList;
	private List<UserLevel> userlevelList;
	private List<UserLevel> userpriceList;

	
	
	public List<UserLevel> getUserpriceList()
	{
		return userpriceList;
	}

	
	public void setUserpriceList(List<UserLevel> userpriceList)
	{
		this.userpriceList = userpriceList;
	}

	public ContentCategory getCurrent()
	{
		return current;
	}

	public void setCurrent(ContentCategory current)
	{
		this.current = current;
	}

	public String getJsonTree()
	{
		return jsonTree;
	}

	public void setJsonTree(String jsonTree)
	{
		this.jsonTree = jsonTree;
	}

	public List<ContentProduct> getList()
	{
		return list;
	}

	public void setList(List<ContentProduct> list)
	{
		this.list = list;
	}

	public Pager getPager()
	{
		return pager;
	}

	public void setPager(Pager pager)
	{
		this.pager = pager;
	}

	public List<Long> getTopicList()
	{
		return topicList;
	}

	public void setTopicList(List<Long> topicList)
	{
		this.topicList = topicList;
	}

	
	public List<ProductCode> getCodeList()
	{
		return codeList;
	}

	
	public void setCodeList(List<ProductCode> codeList)
	{
		this.codeList = codeList;
	}

	
	public List<UserLevel> getUserlevelList()
	{
		return userlevelList;
	}

	
	public void setUserlevelList(List<UserLevel> userlevelList)
	{
		this.userlevelList = userlevelList;
	}

	
	public ContentProduct getCproduct()
	{
		return cproduct;
	}

	
	public void setCproduct(ContentProduct cproduct)
	{
		this.cproduct = cproduct;
	}

}
