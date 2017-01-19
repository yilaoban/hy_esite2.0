package com.huiyee.esite.dto;

import java.util.List;

import com.huiyee.esite.model.CategoryTree;
import com.huiyee.esite.model.ContentCategory;
import com.huiyee.esite.model.ContentNew;
import com.huiyee.esite.model.ContentPicture;
import com.huiyee.esite.model.ContentProduct;
import com.huiyee.esite.model.OwnerContentType;
import com.huiyee.esite.model.ContentVideo;

public class ContentDto implements IDto
{
	private List<ContentCategory> contentcategory;
	private List<ContentProduct> product;
	private List<ContentPicture> pictureList;
	private ContentProduct cp;
	private ContentPicture picture;
	private List<ContentNew> newList;
	private ContentNew cn;
	private List<ContentVideo> videoList;
	private ContentVideo video;
	private List<List<ContentCategory>> ccList;
	private String tree;
	private Pager pager;
	private List<OwnerContentType> typeList;
	private ContentCategory current;
	private List<Long> topicList;//以发帖的内容ID

	public List<ContentCategory> getContentcategory()
	{
		return contentcategory;
	}

	public void setContentcategory(List<ContentCategory> contentcategory)
	{
		this.contentcategory = contentcategory;
	}

	public List<ContentProduct> getProduct()
	{
		return product;
	}

	public void setProduct(List<ContentProduct> product)
	{
		this.product = product;
	}

	public ContentProduct getCp()
	{
		return cp;
	}

	public void setCp(ContentProduct cp)
	{
		this.cp = cp;
	}

	public List<ContentPicture> getPictureList()
	{
		return pictureList;
	}

	public void setPictureList(List<ContentPicture> pictureList)
	{
		this.pictureList = pictureList;
	}

	public ContentPicture getPicture()
	{
		return picture;
	}

	public void setPicture(ContentPicture picture)
	{
		this.picture = picture;
	}

	public List<ContentNew> getNewList()
	{
		return newList;
	}

	public void setNewList(List<ContentNew> newList)
	{
		this.newList = newList;
	}

	public ContentNew getCn()
	{
		return cn;
	}

	public void setCn(ContentNew cn)
	{
		this.cn = cn;
	}

	public List<ContentVideo> getVideoList()
	{
		return videoList;
	}

	public void setVideoList(List<ContentVideo> videoList)
	{
		this.videoList = videoList;
	}

	public ContentVideo getVideo()
	{
		return video;
	}

	public void setVideo(ContentVideo video)
	{
		this.video = video;
	}

	public List<List<ContentCategory>> getCcList()
	{
		return ccList;
	}

	public void setCcList(List<List<ContentCategory>> ccList)
	{
		this.ccList = ccList;
	}

	public Pager getPager()
	{
		return pager;
	}

	public void setPager(Pager pager)
	{
		this.pager = pager;
	}

	public String getTree()
	{
		return tree;
	}

	public void setTree(String tree)
	{
		this.tree = tree;
	}

	public List<OwnerContentType> getTypeList()
	{
		return typeList;
	}

	public void setTypeList(List<OwnerContentType> typeList)
	{
		this.typeList = typeList;
	}

	public ContentCategory getCurrent()
	{
		return current;
	}

	public void setCurrent(ContentCategory current)
	{
		this.current = current;
	}

	public List<Long> getTopicList() {
		return topicList;
	}

	public void setTopicList(List<Long> topicList) {
		this.topicList = topicList;
	}

}
