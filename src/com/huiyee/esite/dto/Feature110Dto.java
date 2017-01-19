package com.huiyee.esite.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.huiyee.esite.model.CategoryTree;
import com.huiyee.esite.model.ContentNew;
import com.huiyee.esite.model.FeatureNews;
import com.huiyee.esite.model.Page;

public class Feature110Dto implements IDto, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4423094235432782460L;
	private List<FeatureNews> list;
	private long fid;

	private List<ContentNew> newList;
	private ArrayList<Long> moduleList;
	private String strList;
	private long relationid;
	
	private List<CategoryTree> categoryTreeList;
	private long ccid;
	private ContentNew contentNews;
	private String type;
	private long pageid;
	
	private List<Page> pagelist;
	
	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public long getPageid()
	{
		return pageid;
	}

	public void setPageid(long pageid)
	{
		this.pageid = pageid;
	}

	public ContentNew getContentNews()
	{
		return contentNews;
	}

	public void setContentNews(ContentNew contentNews)
	{
		this.contentNews = contentNews;
	}

	public long getCcid()
	{
		return ccid;
	}

	public void setCcid(long ccid)
	{
		this.ccid = ccid;
	}

	public List<CategoryTree> getCategoryTreeList()
	{
		return categoryTreeList;
	}

	public void setCategoryTreeList(List<CategoryTree> categoryTreeList)
	{
		this.categoryTreeList = categoryTreeList;
	}

	public long getRelationid()
	{
		return relationid;
	}

	public void setRelationid(long relationid)
	{
		this.relationid = relationid;
	}

	public String getStrList()
	{
		return strList;
	}

	public void setStrList(String strList)
	{
		this.strList = strList;
	}

	public ArrayList<Long> getModuleList()
	{
		return moduleList;
	}

	public void setModuleList(ArrayList<Long> moduleList)
	{
		this.moduleList = moduleList;
	}

	public long getFid() {
		return fid;
	}

	public void setFid(long fid) {
		this.fid = fid;
	}

	public List<FeatureNews> getList() {
		return list;
	}

	public void setList(List<FeatureNews> list) {
		this.list = list;
	}

	public List<ContentNew> getNewList()
	{
		return newList;
	}

	public void setNewList(List<ContentNew> newList)
	{
		this.newList = newList;
	}

	public List<Page> getPagelist() {
		return pagelist;
	}

	public void setPagelist(List<Page> pagelist) {
		this.pagelist = pagelist;
	}

}
