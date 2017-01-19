package com.huiyee.esite.dto;

import java.io.Serializable;
import java.util.List;

import com.huiyee.esite.model.ContentCategory;
import com.huiyee.esite.model.FeatureNews;
import com.huiyee.esite.model.Page;

public class Feature147Dto implements IDto, Serializable 
{
	private static final long serialVersionUID = 5618129571306445571L;

	private long fid;
	private long catid;
	private List<ContentCategory> catList;
	private List<FeatureNews> newList;
	private List<Page> pagelist;
	private long pageid;//œÍ«È“≥pageid
	
	public long getFid()
	{
		return fid;
	}

	public void setFid(long fid)
	{
		this.fid = fid;
	}

	public List<ContentCategory> getCatList()
	{
		return catList;
	}

	public void setCatList(List<ContentCategory> catList)
	{
		this.catList = catList;
	}

	public long getCatid()
	{
		return catid;
	}

	public void setCatid(long catid)
	{
		this.catid = catid;
	}

	public List<FeatureNews> getNewList()
	{
		return newList;
	}

	public void setNewList(List<FeatureNews> newList)
	{
		this.newList = newList;
	}

	public List<Page> getPagelist() {
		return pagelist;
	}

	public void setPagelist(List<Page> pagelist) {
		this.pagelist = pagelist;
	}

	public long getPageid() {
		return pageid;
	}

	public void setPageid(long pageid) {
		this.pageid = pageid;
	}
	
	
	
}
