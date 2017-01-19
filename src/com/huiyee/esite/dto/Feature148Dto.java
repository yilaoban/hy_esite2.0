package com.huiyee.esite.dto;

import java.io.Serializable;
import java.util.List;

import com.huiyee.esite.model.ContentCategory;
import com.huiyee.esite.model.Picture;

public class Feature148Dto implements IDto, Serializable 
{

	private static final long serialVersionUID = 5421773702058061569L;

	private long fid;
	private long catid;
	private List<ContentCategory> catList;
	private List<Picture> picList;
	
	public long getFid()
	{
		return fid;
	}
	public void setFid(long fid)
	{
		this.fid = fid;
	}
	public long getCatid()
	{
		return catid;
	}
	public void setCatid(long catid)
	{
		this.catid = catid;
	}
	public List<ContentCategory> getCatList()
	{
		return catList;
	}
	public void setCatList(List<ContentCategory> catList)
	{
		this.catList = catList;
	}
	public List<Picture> getPicList()
	{
		return picList;
	}
	public void setPicList(List<Picture> picList)
	{
		this.picList = picList;
	}
	
	
}
